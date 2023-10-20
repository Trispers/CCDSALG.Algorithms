public class Main {
    public static void main(String[] args) {
        // TODO: Use this method to run your experiments.
        int size = 100000; // adjust the size based on the record size in the text files

        Record[] idRecords;
        FileReader fileReader = new FileReader();
        idRecords = fileReader.readFile("C:/Users/markjavier/IdeaProjects/CCDSALG/src/data/totallyreversed.txt");

        long startTime = System.currentTimeMillis();

        // 4 sorting algorithms (remove the '//' to run the algorithm, 1 algorithm per runtime only!)
        SortingAlgorithms sortingAlgorithms = new SortingAlgorithms();
        // sortingAlgorithms.insertionSort(idRecords, size);
        // sortingAlgorithms.selectionSort(idRecords, size);
        // sortingAlgorithms.mergeSort(idRecords, 0, size - 1);
        // sortingAlgorithms.combSort(idRecords, size);

        // print the sorted array
        sortingAlgorithms.printData(idRecords, size);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution Time: " + executionTime);

        long count = sortingAlgorithms.getCount();
        System.out.println("Count: " + count);
    }
}
