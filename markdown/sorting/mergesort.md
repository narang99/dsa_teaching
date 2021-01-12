# Merge Sort

* Sorting algorithm for `O(nlogn)`
* Example of divide and conquer strategy

# Steps

* Split the array in two halves
* recursively sort the left and the right arrays (**divide**)
* now we have two sorted arrays
* merge the sorted arrays into a new sorted array (**combine**)
* return the sorted array

## Merge

* Merge is the main routine of the algorithm
* The problem is:
  * You have two sorted arrays
  * merge the two arrays into one and it should be sorted
* The procedure of merging
  * compare the elements at the head of both arrays
  * if left array has smaller element, push it in output
  * else push the element in right array
  * Increment the head of the array from which you got the result

# Complexity

* The arrays is split in half each time
* Merge is an `O(n)` operation
* recurrence relation: `T(n) = 2T(n/2) + O(n)`
* The complexity of this relation is `O(nlogn)`

# Codes
* [Java](../codes/MergeSort.md)