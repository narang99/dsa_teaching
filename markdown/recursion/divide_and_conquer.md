# Theory

* normally works well when we have a problem which in turn depends directly on smaller subparts which are the same as the original problem

* So you first
  1. **divide** the problem in smaller subparts
  2. process them
  3. get their result
  4. **combine** them

# Max Subarray Problem

* Given an array, find the subarray with maximum sum
* [Problem Description and Solution](../questions/maxsubarray.md)

# Merge Sort

* Given an array, sort it using merge sort
* Classic example of divide and conquer
* [Problem Description and Solution](../sorting/mergesort.md)

# Heap Sort

* This uses an important concept called [heaps](./heap.md)
* There are multiple ways to sort using heaps.
* Way 1: Create separate heap. Time Complexity `O(nlogn)`, space complexity `O(n)`
  1. we can create a separate heap, and put all elements in the array in the heap.
  2. Extract the elements one by one from heap
  3. this will give sorted order
Way 2: In place `heapsort`. We use the concepts of heap, Space Complexity `O(1)`, time complexity `O(nlogn)`. Go to [heaps](./heap.md) for more details
  1. use `BuildHeap` on the array itself. now the array is a maxheap
  2. take the first element out. put it in the end
  3. decrease the heap size
  4. Heap property is now not satisfied at first element
  5. So call heapify on it. 
  6. repeat till the end

