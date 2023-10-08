import java.util.Scanner;

public class CombinationSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        
        int[] arr = new int[n];
        
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        
        combinationSort(arr);
        
        System.out.println("Sorted Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        
        scanner.close();
    }

    public static void combinationSort(int[] arr) {
        int n = arr.length;
        int gap = n;
        boolean swapped;

        do {
            // Calculate the new gap
            gap = (gap * 10) / 13;
            if (gap < 1) {
                gap = 1;
            }

            swapped = false;

            // Compare elements with a specific gap
            for (int i = 0; i < n - gap; i++) {
                if (arr[i] > arr[i + gap]) {
                    // Swap elements if they are out of order
                    int temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;
                    swapped = true;
                }
            }
        } while (gap > 1 || swapped);
    }
}
