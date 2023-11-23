import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SocialGraph {
    private HashMap<Integer, Set<Integer>> graph;

    public SocialGraph() {
        graph = new HashMap<>();
    }

    public void graphFileReader(String filePath) {
        Scanner scanner = null;
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                System.err.println("File not found.");
                return;
            }

            scanner = new Scanner(file);

            String firstLine = scanner.nextLine();
            String[] nums = firstLine.split(" ");

            int numAccounts = Integer.parseInt(nums[0]);
            int numFriendships = Integer.parseInt(nums[1]);

            for (int i = 0; i < numAccounts; i++) {
                graph.put(i, new HashSet<>());
            }

            for (int i = 0; i < numFriendships; i++) {
                String lines = scanner.nextLine();
                nums = lines.split(" ");

                int id1 = Integer.parseInt(nums[0]);
                int id2 = Integer.parseInt(nums[1]);

                // friendship is bidirectional
                graph.get(id1).add(id2);
                graph.get(id2).add(id1);
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error parsing integers.");
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public void displayFriendList(int personID) {
        if (graph.containsKey(personID)) {
            ArrayList<Integer> friendList = new ArrayList<>(graph.get(personID));
            Collections.sort(friendList);

            System.out.println("Person " + personID + " has " + friendList.size() + " friends!");
            System.out.print("List of friends: ");
            for (int i = 0; i < friendList.size(); i++) {
                System.out.print(friendList.get(i));
                if (i < friendList.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        } else {
            System.out.println("Person " + personID + " does not exist.");
        }
    }

    public void displayConnection(int firstPerson, int secondPerson){
        if (!graph.containsKey(firstPerson) || !graph.containsKey(secondPerson)) {
            System.out.println("One or both persons do not exist in the dataset.");
            return;
        }

        boolean foundConnection = findConnection(firstPerson, secondPerson, new HashSet<>());
        if (foundConnection) {
            System.out.println("There is a connection from " + firstPerson + " to " + secondPerson + "!");
        } else {
            System.out.println("Cannot find a connection between " + firstPerson + " and " + secondPerson);
        }
    }

    private boolean findConnection(int current, int target, HashSet<Integer> visited) {
        if (current == target) {
            return true;
        }

        visited.add(current);

        if (graph.containsKey(current)) {
            for (int friend : graph.get(current)) {
                if (!visited.contains(friend)) {
                    if (findConnection(friend, target, visited)) {
                        System.out.println(current + " is friends with " + friend);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        SocialGraph socialGraph = new SocialGraph();

        System.out.print("Input file path: ");
        String filePath = scanner.nextLine();

        socialGraph.graphFileReader(filePath);
        System.out.println("Graph file loaded!");

        int choice;
        do{
            System.out.println("MAIN MENU");
            System.out.println("[1] Get Friend List");
            System.out.println("[2] Get Connection");
            System.out.println("[3] Exit\n");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter ID of person: ");
                    int personID = scanner.nextInt();
                    scanner.nextLine();
                    socialGraph.displayFriendList(personID);
                }
                case 2 -> {
                    System.out.print("Enter ID of first person: ");
                    int firstPersonID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter ID of second person: ");
                    int secondPersonID = scanner.nextInt();
                    scanner.nextLine();
                    socialGraph.displayConnection(firstPersonID, secondPersonID);
                }
                case 3 -> {
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please enter valid option.");
            }
        } while(true);
    }
}
