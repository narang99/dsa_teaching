class MaxSubarray {
  /**
   * go through all elements 
   * for each element find the maximum subarray starting with it
   * return the max one of all 
   */
  static int brute(int[] arr, int lo, int hi)  {
    int maxsum = Integer.MIN_VALUE;
    for(int i=lo; i < hi; ++i)  {
      int rsum = 0;
      for(int j=i; j < hi; ++j) {
        rsum += arr[j];
        if(rsum > maxsum)
          maxsum = rsum;
      }
    }
    return maxsum;
  }

  /** 
   * helper function for divide and conquer
   * it finds the subarray passing through mid with maximum sum
   * it first goes to left, does a simple linear scan to find the
   * left subarray that gives maximum
   * does same for right side
   * returns [left_max_subarray, right_max_subarray]
   */
  static int cross_sum(int[] arr, int lo, int hi, int mid) {
    int ls=Integer.MIN_VALUE, rs=Integer.MIN_VALUE;
    int sum = 0;
    for(int i=mid-1; i >= lo; --i)  {
      sum += arr[i];
      if(sum > ls)
        ls = sum;
    }
    sum = 0;
    for(int i=mid; i < hi; ++i) {
      sum += arr[i];
      if(sum > rs)
        rs = sum;
    }
    return (ls + rs);
  }

  /* O(n logn) */
  /**
   * recursuve function 
   * get max subarray in left part and right part recursively
   * find the cross maximum subarray (going through mid)
   * return the max of all three
   */
  static int dvdconq(int[] arr, int lo, int hi) {
    if(lo == hi-1)  {
      return arr[lo];
    }
    int mid = lo + (hi-lo)/2;
    int lsum = dvdconq(arr, lo, mid);
    int rsum = dvdconq(arr, mid, hi);
    int csum = cross_sum(arr, lo, hi, mid);
    if(lsum > rsum && lsum > csum) 
      return lsum;
    else if(csum < rsum)
      return rsum;
    else 
      return csum;
  }

  /* O(n) */
  static int optimized_iterative(int[] arr, int lo, int hi)  {
    /**
     * for each element arr[j] , maintain the maximum subarrray
     * if the sum of our element with that subarray > element
     * then this subarray is gud
     * else element itself is the subarray
     */ 
    int sum = arr[lo];
    int maxsum = sum;
    for(int i=lo+1; i < hi; ++i)  {
      if(sum + arr[i] > arr[i])
        sum += arr[i];
      else
        sum = arr[i];
      if(maxsum < sum)
        maxsum = sum;
    }
    return maxsum;
  }

  public static void main(String[] args) {
    int[] arr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
    System.out.println("brute: " +
        Integer.toString(brute(arr, 0, 16)));
    System.out.println("divide and conquer: " +
        Integer.toString(dvdconq(arr, 0, 16)));
    System.out.println("optimised iterative: " +
        Integer.toString(optimized_iterative(arr, 0, 16)));
  }
}