```java
package codes;
public class MergeSort {

  /**
   * Merge function
   * Assumption: The two partitions arr[lo..m] and arr[m..hi] are sorted
   * On success, the arr[lo..m] and arr[m..hi] are merged
   * that is arr[lo..hi] is sorted
   * it doesnt use two different explicit arrays for merging
   * instead uses indices in the same array and merges the partitions 
   * example:
   * arr = [1,1,2,2,5,5,7]
   * lo = 0, hi = 7, m = 3
   * the function treats arr[lo..m] as array 1 (m excluded)
   * and arr[m..hi] as array 2 (hi excluded)
   * so here array1 is essentially [1,1,2] (lo=0, m=3)
   * and array2 [2,5,5,7]
   */
  static void merge(int[] arr, int lo, int m, int hi)  {
    int i=lo, j=m;
    // create new array to hold the merged part
    int[] output = new int[hi-lo];
    int outputIndex = 0;
    while(i < m && j < hi)  {
      if(arr[i] < arr[j]) {
        output[outputIndex] = arr[i];
        ++i;
        ++outputIndex;
      }
      else {
        output[outputIndex] = arr[j];
        ++j;
        ++outputIndex;
      }
    }

    while(i < m)  {
      output[outputIndex] = arr[i];
      ++i;
      ++outputIndex;
    }

    while(j < hi)  {
      output[outputIndex] = arr[j];
      ++j;
      ++outputIndex;
    }

    // copy the output array in our original array
    i = lo;
    while(i < hi) {
      arr[i] = output[i - lo];
      ++i;
    }
  }

  static void mergeSort(int[] arr, int lo, int hi)  {
    // base case
    if(lo == hi-1)
      return;

    // divide
    int m = lo + (hi-lo)/2;
    mergeSort(arr, lo, m);
    mergeSort(arr, m, hi);

    // combine/conquer
    merge(arr, lo, m, hi);
  }

  public static void main(String[] args) {
    int[] arr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
    System.out.print("unsorted array: ");
    for(int a : arr)  {
      System.out.print(Integer.toString(a) + " ");
    }
    System.out.println("");
    mergeSort(arr, 0, 16);

    System.out.print("sorted array: ");
    for(int a : arr)  {
      System.out.print(Integer.toString(a) + " ");
    }
    System.out.println("");
  }
}

```