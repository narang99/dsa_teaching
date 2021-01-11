```c
#include <stdio.h>
#include <limits.h>

/* O(n^2) */
int brute(int arr[], int lo, int hi) {
  int maxsum = INT_MIN;
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

int cross_sum(int arr[], int lo, int hi, int mid) {
  int ls=INT_MIN, rs=INT_MIN;
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
int dvdconq(int arr[], int lo, int hi) {
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
int optimized_iterative(int arr[], int lo, int hi)  {
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

int main() {
  int arr[] = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
  printf("brute: %d\n", brute(arr, 0, 16));
  printf("divide and conquer: %d\n", dvdconq(arr, 0, 16));
  printf("iterative: %d\n", optimized_iterative(arr,0,16));
  return 0;
}
```