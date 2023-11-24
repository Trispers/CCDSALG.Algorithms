import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SocialGraph {
    private HashMap<Integer, Set<Integer>> graph;

    public SocialGraph() {
        graph = new HashMap<>();
    }

    private boolean loadGraph(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            String[] values = line.split(" ");
            int e = Integer.parseInt(values[1]); // Number of friendships

            graph = new HashMap<>();

            for (int i = 0; i < e; i++) {
                line = reader.readLine();
                values = line.split(" ");
                int a = Integer.parseInt(values[0]);
                int b = Integer.parseInt(values[1]);

                // Add edges to the graph (assuming friendships are bidirectional)
                graph.computeIfAbsent(a, k -> new HashSet<>()).add(b);
                graph.computeIfAbsent(b, k -> new HashSet<>()).add(a);
            }

            return true;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return false;
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

        socialGraph.loadGraph(filePath);
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
