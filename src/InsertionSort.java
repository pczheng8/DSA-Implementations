public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 4, 1, 5};
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
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