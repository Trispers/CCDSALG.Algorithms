public class CombinationSort {
   
    public void combinationSort(int[] arr) {
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
                int j = i + gap;
                if (arr[i] > arr[j]) {
                    // Swap elements if they are out of order
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    swapped = true;
                }
            }
        } while (gap > 1 || swapped);
    }
}
