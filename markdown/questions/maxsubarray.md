# Maximum Subarray

* given array, find contiguous subarray with maximum sum

## Brute force

* for all elements at index `i`, find the max subarray starting from `i` using linear scan
* do for all `i`
* Complexity: `O(n^2)`


## optimized

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

# Codes

* [C code](../codes/maxsubarray.md)