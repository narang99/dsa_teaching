# Maximum Subarray

* given array, find contiguous subarray with maximum sum

## Brute force

* for all elements at index `i`, find the max subarray starting from `i` using linear scan
* do for all `i`
* Complexity: `O(n^2)`


## Optimized

* uses divide and conquer
* find max subarray of the two half arrays using recursion (**divide**)
* find the cross subarray between the two arrays (the subarray with maximum sum going through mid)
    * complexity of finding cross sum: `O(n)`
    * start from mid, go left, return left for which `a[left...mid-1]` has greatest sum
    * start from mid, go right, return right for which `a[mid...right]` has greatest sum
    * cross sum is the sum of these two sums
* return max of the three sums (**combine**)
* Complexity: `T(n) = 2T(n/2) + O(n)`
    * n logn

# Iterative fast solution

* best solution in efficiency
* This is not divide and conquer

## Steps

* The basic idea is to maintain the maximum subarray ending with index `i` when you are processing index `i`
* Would be clearer with an example
* Array: `[13, -3, -25, 20, -3, -16, -23, 18]`
* Max subarrays for all elements:
  * `13` : `[13]`
  * `-3` : `[13, -3]`
  * `-25`: `[13, -3, -25]`
  * `20` : `[20]`
  * `-3` : `[20, -3]`
  * `-16`: `[20, -3, -16]`
  * `-23`: `[20, -3, -16]`
  * `18` : `[18]`
* when we are checking for index `i`, we check if the sum of the subarray for index `i-1` is greater than 0 or not
* to find the maximum subarray **ending** at index `i` when we are given the maximum subarray **ending** at index `i-1`, we have **two** options:
  1. we can add this element to the maximum subarray ending at element `i-1`
  2. we can ignore the previous maximum subarray sequence and just use this element as the maximum subarray
* **NOTE** there is nothing else we can do
* so of the two steps above we need to get the subarray with the maximum sum
  * the new sum of subarray if we add `a[i]` is `prev_sum + a[i]`
  * if we keep the subarray as `a[i]`, the sum is `a[i]`
  * Therefore if `prev_sum + a[i]` > `a[i]`, then we add the element, else we just keep the same element

# Codes

* [Java](../codes/MaxSubarray.md)