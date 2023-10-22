/* This file contains implementations of sorting algorithms.
 * You are NOT allowed to modify any of the given method headers.
 */

public class SortingAlgorithms {
    private long count;

    /*
     * You may define additional helper functions here if you need to.
     * Make sure the helper functions have the private access modifier, as
     * they will only be used in this class.
     */

    /**
     * Getter method to access count from outside the class
     *
     * @return count
     */
    public long getCount(){
        return count;
    }
    public void printData(Record[] arr, int n){
        for(int i = 0; i < n; i++){
            System.out.println(arr[i].getName() + " " + arr[i].getIdNumber());
        }
    }


    public void verifyList(Record[] original, Record[] toBeVerified, int n) {
        for (int i = 0; i < n - 1; i++) {
            if (toBeVerified[i].getIdNumber() > toBeVerified[i + 1].getIdNumber()) {
                System.out.println("List is not sorted in ascending order.");
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (toBeVerified[i].getIdNumber() == original[j].getIdNumber()) {
                    if (!toBeVerified[i].getName().equals(original[j].getName())) {
                        System.out.println("ID and name do not match.");
                    }
                }
            }
        }
    }


    /*
    public void verifyList(Record[] original, Record[] verify, int n){
        for(int i = 0; i < n - 1; i++){
            if(verify[i].getIdNumber() > verify[i + 1].getIdNumber()){
                System.out.println("List is not sorted in ascending order.");
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(verify[i].getIdNumber() == original[j].getIdNumber()){
                    if(verify[i].getName().equals(original[j].getName())){
                        System.out.println("Name and ID doesn't match");
                    }
                }
            }
        }
    }*/
    
    public void insertionSort(Record[] arr, int n) {
        // TODO: Implement this sorting algorithm here.
        count = 0;
        int i, j;

        for(i = 1, count++; i < n; i++, count++){
            Record key = arr[i]; count++;
            j = i - 1; count++;

            while(j >= 0 && arr[j].getIdNumber() > key.getIdNumber()){
                arr[j + 1] = arr[j]; count++;
                j--; count++;
                count++;
            }

            arr[j + 1] = key; count++;
        }
    }

    public void selectionSort(Record[] arr, int n) {
        // TODO: Implement this sorting algorithm here.
        count = 0;
        int i, j;

        for(i = 0, count++; i < n - 1; i++, count++){
            int minIndex = i; count++;

            // Find the index of the minimum element in the remaining unsorted part
            for(j = i + 1, count++; j < n; j++, count++){
                if(arr[j].getIdNumber() < arr[minIndex].getIdNumber()){
                    count++;
                    minIndex = j; count++;
                }
            }

            // Swap the found minimum element with the current element
            Record temp = arr[i]; count++;
            arr[i] = arr[minIndex]; count++;
            arr[minIndex] = temp; count++;
        }
    }

    public void mergeSort(Record[] arr, int p, int r) {
        // TODO: Implement this sorting algorithm here.
        count = 0;
        // p = left, r = right

        if(p < r){
            count++; // 1 + statement/s ?
            // Find the middle array
            int mid = (p + r) / 2; count++;

            // Sort the first and second halves
            mergeSort(arr, p, mid); count++;
            mergeSort(arr, mid + 1, r); count++;

            // Merge the sorted halves
            merge(arr, p, mid, r); count++;
        }
    }

    public void merge(Record[] arr, int p, int mid, int r){
        // p = left, r = right

        int n1 = mid - p + 1; count++;
        int n2 = r - mid; count++;

        // Create temporary arrays

        Record[] leftArray = new Record[n1]; count++;
        Record[] rightArray = new Record[n2]; count++;

        // Copy data to temporary arrays leftArray[] and rightArray[]
        int i;
        for(i = 0, count++; i < n1; i++, count++){
            leftArray[i] = arr[p + i]; count++;
        }
        int j;
        for(j = 0, count++; j < n2; j++, count++){
            rightArray[j] = arr[mid + 1 + j]; count++;
        }

        // Merge the temporary arrays
        int k;
        i = 0; count++;
        j = 0; count++;
        k = p; count++;
        while(i < n1 && j < n2){
            if(leftArray[i].getIdNumber() <= rightArray[j].getIdNumber()){
                count++;
                arr[k] = leftArray[i]; count++;
                i++; count++;
            } else{
                count++;
                arr[k] = rightArray[j]; count++;
                j++; count++;
            }
            k++; count++;
        }

        // Copy remaining elements of leftArray[] and rightArray[]
        while(i < n1){
            arr[k] = leftArray[i]; count++;
            i++; count++;
            k++; count++;
        }

        while(j < n2){
            arr[k] = rightArray[j]; count++;
            j++; count++;
            k++; count++;
        }
    }

    /*
     * Define AT LEAST ONE more sorting algorithm here, apart from the
     * ones given above. Make sure that the method accepts an array of
     * records
     */
    public void combSort(Record[] arr, int n){
        count = 0;

        int gap = n; count++;
        boolean swapped;

        do{
            count++;
            // Calculate the new gap
            gap = (gap * 10) / 13;  count++;// shrink factor
            if(gap < 1){
                count++;
                gap = 1; count++;
            }

            swapped = false; count++;

            // Compare elements with a specific gap
            int i;
            for(i = 0, count++; i < n - gap; i++, count++){
                int j = i + gap; count++;
                if(arr[i].getIdNumber() > arr[j].getIdNumber()){
                    count++;
                    // Swap elements if they are out of order
                    Record temp = arr[i]; count++;
                    arr[i] = arr[j]; count++;
                    arr[j] = temp; count++;
                    swapped = true; count++;
                }
            }
        }while(gap > 1 || swapped);
    }
}
