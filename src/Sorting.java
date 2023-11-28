//Professor Curran Muhlberger's implementations for CS 2110




import java.util.Comparator;

class Sorting {
    /**
     * Swap elements at indices `i` and `j` in array `a`.
     */
    static void swap(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /**
     * Sort elements of `a` in ascending order (as determined by `cmp`) using the selection sort
     * algorithm.  Relative ordering of elements comparing as equal is not preserved.
     */
    static <T> void selectionSort(T[] a, Comparator<T> cmp) {
        // Invariant: a[0..i) is sorted, a[i..] >= a[0..i)
        int i = 0;
        while (i < a.length - 1) {
            // Find index of smallest element in a[i..]
            int jSmallest = i;
            for (int j = i + 1; j < a.length; ++j) {
                if (cmp.compare(a[j], a[jSmallest]) < 0) {
                    jSmallest = j;
                }
            }

            // Swap smallest element to extend sorted portion
            swap(a, i, jSmallest);
            i += 1;
        }
    }

    /**
     * Sort elements of `a` in ascending order (as determined by `cmp`) using the insertion sort
     * algorithm.  Relative ordering of elements comparing as equal is preserved.
     */
    static <T> void insertionSort(T[] a, Comparator<T> cmp) {
        // Invariant: a[0..i) is sorted, a[i..] is unchanged
        int i = 0;
        while (i < a.length) {
            // Slide a[i] to its sorted position in a[0..i]
            // Invariant: a[j] < a[j+1..i]
            int j = i;
            // Stability requires `>` and not `>=`
            while (j > 0 && cmp.compare(a[j - 1], a[j]) > 0) {
                swap(a, j - 1, j);
                j -= 1;
            }
            i += 1;
        }
    }

    /**
     * Sort elements of `a` in ascending order (as determined by `cmp`) using the merge sort
     * algorithm.  Relative ordering of elements comparing as equal is preserved.
     */
    static <T> void mergeSort(T[] a, Comparator<T> cmp) {
        // Allocate work array
        @SuppressWarnings("unchecked")
        T[] work = (T[]) new Object[a.length];

        // Sort entire array
        mergeSort(a, 0, a.length, cmp, work);
    }

    /**
     * Sort `a[begin..end)` in ascending order (as determined by `cmp`).  Will overwrite values in
     * scratch array `work`.  Scratch array must be at least as long as the view being sorted.
     */
    static <T> void mergeSort(T[] a, int begin, int end, Comparator<T> cmp, T[] work) {
        // Base case: an array of 0 or 1 element is already sorted
        if (end - begin <= 1) {
            return;
        }

        int mid = begin + (end - begin) / 2;
        mergeSort(a, begin, mid, cmp, work);
        mergeSort(a, mid, end, cmp, work);
        merge(a, begin, mid, end, cmp, work);
    }

    /**
     * Merge sorted subarrays `a[begin..mid)` and `a[mid..end)` into a sorted view `a[begin..end)`.
     * Will overwrite values in scratch array `work`.  Scratch array must be at least as long as
     * `end - begin`.  "Sorted" means in ascending order, as determined by `cmp`.
     */
    static <T> void merge(T[] a, int begin, int mid, int end, Comparator<T> cmp, T[] work) {
        // Ensure enough space in scratch array for a[begin..end)
        assert work.length >= end - begin;

        // Invariant: work[0..k) is sorted and contains a[begin..i) and a[mid..j),
        // a[i..mid) >= work[0..k), a[j..end) >= work[0..k)
        int i = begin;
        int j = mid;
        int k = 0;
        while (i < mid && j < end) {
            // Stability requires `<=` and not `<`
            if (cmp.compare(a[i], a[j]) <= 0) {
                work[k] = a[i];
                i += 1;
            } else {
                work[k] = a[j];
                j += 1;
            }
            k += 1;
        }

        // Copy anything remaining in left half
        System.arraycopy(a, i, work, k, mid - i);
        // Copy anything remaining in right half
        System.arraycopy(a, j, work, k, end - j);
        // Copy scratch array back to original
        System.arraycopy(work, 0, a, begin, end - begin);

//        // Copy anything remaining in left half
//        while (i < mid) {
//            work[k] = a[i];
//            i += 1;
//            k += 1;
//        }
//
//        // Copy anything remaining in right half
//        while (j < end) {
//            work[k] = a[j];
//            j += 1;
//            k += 1;
//        }
//
//        // Check that we copied all elements
//        assert k == end - begin;
//
//        // Copy scratch array back to original
//        for (k = 0; k < end - begin; ++k) {
//            a[begin + k] = work[k];
//        }
    }

    /**
     * Sort elements of `a` in ascending order (as determined by `cmp`) using the quicksort
     * algorithm.  Relative ordering of elements comparing as equal is not preserved.
     */
    static <T> void quickSort(T[] a, Comparator<T> cmp) {
        quickSort(a, 0, a.length, cmp);
    }

    /**
     * Sort `a[begin..end)` in ascending order (as determined by `cmp`).
     */
    static <T> void quickSort(T[] a, int begin, int end, Comparator<T> cmp) {
        // Base case: an array of 0 or 1 element is already sorted
        if (end - begin <= 1) {
            return;
        }

        int iPivot = partition(a, begin, end, cmp);
        quickSort(a, begin, iPivot, cmp);
        quickSort(a, iPivot + 1, end, cmp);
    }

    /**
     * Partition `a[begin..end)` about a selected pivot `a[i]` such that `a[begin..i) <= a[i]` and
     * `a[i+1..end) >= a[i]` (as determined by `cmp`), returning `i`.
     */
    static <T> int partition(T[] a, int begin, int end, Comparator<T> cmp) {
        // Choose pivot and swap to beginning of array view
        int iPivot = begin;  // FIXME: bad choice - leads to worst-case behavior for sorted input
        swap(a, begin, iPivot);

        // Invariant: a[begin..i) <= a[i], a(j..end) >= a[i]
        int i = begin;
        int j = end - 1;
        while (i < j) {
            // FIXME: pivot will be first among duplicates - leads to worst-base behavior for
            // duplicated input.
            if (cmp.compare(a[i], a[i + 1]) > 0) {
                swap(a, i, i + 1);
                i += 1;
            } else {
                swap(a, i + 1, j);
                j -= 1;
            }
        }
        return i;
    }
}