public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 4, 1, 5};
        for (int i = 0; i < arr.length - 1; i++) {
            int smallest = arr[i];
            int smallestIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < smallest) {
                    smallest = arr[j];
                    smallestIndex = j;
                }
            }
            swap(arr, i, smallestIndex);
            for (int k : arr) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
    }

    public static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
