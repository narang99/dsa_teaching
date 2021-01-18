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

# Height of binary tree

* Given a tree, find its height

## Steps
* height of a node is `1 + max(height(left), height(right))`
* use recursion
* height depends on left and right child, recursilvely call. **divide** phase
* take max of both and add `1`. **Combine** phase

<details>
<summary> Code for Tree height </summary>

```java
int height(Node root) {
  if(root == null)
    return 0;
  int left_height = height(root.left);
  int right_height = height(root.right);

  // max + 1
  if(left_height > right_height)
    return left_height + 1;
  else
    return right_height + 1; 
}
```

</details>

# Tower of hanoi

* you have three rods, and `n` rings in the first rod. Each ring is of different size
* Initially the rings are placed on the first rod in an increasing radius order, with the smallest ring at the top
* You can only put a smaller ring above a bigger ring
* It is not allowed to put a bigger ring over a smaller ring
* Find number of steps required to put all `n` rings on the third rod such that you never put a bigger ring over a smaller ring

## Solution

* To put `n` rings on the third rod from the first rod, you first put `n-1` rings on the second rod
* Then you put the remaining last ring on the third rod
* Now you place the `n-1` rings on the second rod to the third rod
* If `T(x)` gives the number of steps required to put `x` rings from one tower to another, our relation is:
  * `T(n) = T(n-1) + 1 + T(n-1)`
  * `T(n) = 2T(n-1) + 1`

<details>
<summary>Recurrence relation solve</summary>

* To solve: `T(n) = 2T(n-1) + 1`
* By manually counting: `T(1) = 1`, `T(2) = 3`
* the solution can be guessed to be `2^n - 1`
* Substituting the guess we have:
  * LHS: `2^n - 1`
  * RHS: `2 * (2^(n-1)-1) + 1 = 2^n - 2 + 1` = `2^n - 1`
  * LHS = RHS.
  * Hence, `2^n - 1` is the answer.

</details>



<details>
<summary>Code for Tower of Hanoi</summary>

```java
int hanoi_tower(int n) {
  if(n == 1)
    return 1;
  return 2*hanoi_tower(n-1) + 1;
}
```

</details>

# x to the power y

* an efficient way to find powers of numbers
* powers have to be `int`. `x` can be float

## Standard way

* easy way is to multiply `x` with itself `y` times. The complexity if `O(y)`. This is inefficient 

```java
double product = 1.0;
for(int i=0; i < y; ++i)  {
  product *= x;
}
return product;
```

# Efficient using recursion

* `x^y` is equal to:
  * `x^(y/2) * x^(y/2)`, if `y` is even
  * `x * x^(y-1)`, if `y` is odd
* The complexity of the solution would be `O(log y)`, exponentially smaller than standard.
* A very fast solution

<details>
<summary>Code for x raised to y</summary>

```java
float power(float x, int y) {
  if(y == 0)
    return 1;

  if(y % 2 == 0)  {
    float half_pow = power(x, y/2);
    return half_pow * half_pow;
  }
  else 
    return x * power(x, y-1);
}
```

</details>
