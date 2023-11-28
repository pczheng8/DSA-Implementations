public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 2, 3, 4};
        System.out.println(binarySearch(arr, 1));
    }

    public static int binarySearch(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length-1;
        int m;
        while(lo<=hi) {                        //note <=
            m = (lo+hi)/2;
            if(arr[m] == target) {
                return m;
            }
            if(arr[m] < target) {
                lo = m+1;
            }
            else {
                hi = m-1;
            }
        }
        return -1;
    }
}
