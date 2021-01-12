# Kth largest/smallest element

* this problem is also called **order statistics** (fancy word)
* Problem:
  * given an array find the `kth` smallest element
  * **NOTE:** in case you are asked for `kth` largest element, just find the `array size - k`th smallest element

# Basic approach

* sort the array 
* return the `kth` element
* Complexity: `O(nlogn)`

# Optimization

* The algorithm is called **QuickSelect** and works similar to **QuickSort**