import java.util.Comparator;

public class Quicksort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 4, 1, 5, 9};
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        quickSort(arr, 0, arr.length, Comparator.comparingInt(o -> o));

        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void quickSort(int[] arr, int begin, int end, Comparator<Integer> cmp) {
        if(end - begin <= 1) {
            return;
        }

        int pivot = partition(arr, begin, end, cmp);
        quickSort(arr, begin, pivot, cmp);
        quickSort(arr, pivot+1, end, cmp);
    }

    public static int partition(int[] arr, int begin, int end, Comparator<Integer> cmp) {
        int mid = (begin+end)/2;
        int pivot = med3(begin, mid, end-1);
        System.out.println(pivot + " pivot position");

        swap(arr, begin, pivot);

        int i = begin;
        int j = end-1;
        while(i < j) {
            if(cmp.compare(arr[i], arr[i+1]) > 0) {
                swap(arr, i, i+1);
                i++;
            }
            else {
                swap(arr, i+1, j);
                j--;
            }
        }
        for (int k : arr) {
            System.out.print(k + " ");
        }
        System.out.println("partition");
        return i;
    }

    private static int med3(int num1, int num2, int num3) {
        return Math.max(Math.min(num1, num2), Math.min(Math.max(num1, num2), num3));
    }

    public static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
