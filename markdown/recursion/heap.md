# Heaps

- binary tree
- Filled fully other than at the last level
- Satisfy the **heap property**

# Heap property

- max heaps, min heaps
- for max heap, every children of a node is smaller than the node, vice versa for min heap
- that is, in a max heap, the current node will be greatest element in the subtree starting at node

# Representation

- Heaps are normally stored as an array of nodes in the binary tree, stored in level order traversal
- **NOTE** Array is _0-indexed_ here
- The parent of node `i` is the node at `floor((i+1)/2)`
- The left child of `i` is `2i+1` and right node is `2i+2`
- We also maintain a `heap_size` property, whose use will be evident later in `HeapSort`
  - Basically in the array, we might want some elements in the end to be excluded from the heap. This property helps for that

<details>
  <summary>
    <strong>Basic parent child access code</strong>
  </summary>

```java
public class Heap {
  private List<Integer> heap;
  private int heap_size;

  public Heap(List<Integer> arr) {
    // this.heap stores the array of level order traversal
    // heapify generates the correct level order traversal
    this.heap = arr;
    this.heap_size = this.heap.size();
  }
  private static int parent(int i)  {
    return (i+1)/2;
  }
  private static int leftChild(int i)  {
    return 2*i + 1;
  }
  private static int rightChild(int i) {
    return 2*i + 2;
  }

  private void swap(int index1, int index2) {
    int temp = this.heap.get(index1);

    this.heap.set(index1, this.heap.get(index2));
    this.heap.set(index2, temp);
  }
}
```

</details>

## Question

- Is this a max heap `[23, 17, 14, 6, 13, 10, 1, 5, 7, 12]`

# Main operations

## Heapify

- Most imp operation.
- it maintains the heap property
- For a max heap:
  - Calling heapify on root assumes that the left subtrees and right subtrees are valid heaps
  - Now it needs to make the root a valid heap
  - So in max heap root value should be largest
  - So it checks it with the children
  - if it is larger than both children, then this root is a valid heap
  - else it swaps it with the largest child
    - now say for example right was the largest element
    - Then root and right values will be swapped
    - Now however, the right subtree might not be a valid heap
    - so we recursively call the `heapify` procedure on the right subtree
- [Example Max Heapify](../../assets/heapify_example.png)
  - In this example heapify starts at the shaded `4`
  - finds `14` to be maximum (_left child_).
  - swaps them
  - calls recursively on `4` in left child in `figure b`

<details>
  <summary>
    <strong>Max heapify algorithm code</strong>
  </summary>

```java
public class Heap {
  public void maxheapify(int root) {
    // base case
    if(root >= this.heap_size)
      return;

    int left = leftChild(root);
    int right = rightChild(root);

    // base case, root is leaf
    if(left >= this.heap_size && right >= this.heap_size)
      return;

    // largest holds the index of the element 
    // which holds the largest value among
    // root, left and right
    int largest;
    if(left < this.heap_size && 
      this.heap.get(left) > this.heap.get(root) && 
      this.heap.get(left) >= this.heap.get(right)) {
      largest = left;
    }
    else if(right < this.heap_size && 
      this.heap.get(right) > this.heap.get(root) && 
      this.heap.get(right) >= this.heap.get(left)) {
        largest = right;
    }
    else 
      largest = root;
    
    if(largest != root) {
      // if largest value is at root, then the subtree rooted at root is already a valid maxheap
      // else we call recursively after swapping
      
      swap(largest, root);
      this.maxheapify(largest);
    }
  }
}
```

</details>

- Complexity of heapify: `O(h)`, where `h` is the height of the tree, the maximum height is essentially `log n`
- therefore complexity is `O(log n)`

# Heap build

- We want to build the heap
- `heapify` can be somehow used to achieve this
- NOTE: `heapify` assumes that children are a valid heap when it is called on a root
- Therefore, when we call `heapify` on a node, we should have already called `heapify` on its children
- This suggests that we can start from children, call `heapify` on them and then call the procedure above
- remember that the array is already in a level order traversal
- Therefore last node in the array is the last leaf of the tree
- So we start from the end of the array and then call heapify on each preceding element
- Complexity: `O(n)`. **Prove This**

<details>
  <summary>
    <strong>Build Heap code</strong>
  </summary>

```java
public class Heap {
  public void buildMaxHeap()  {
    // start heapify from children
    // call on all
    for(int i=this.heap_size-1; i >= 0; --i)  {
      maxheapify(i);
    }
  }
}
```

</details>

## HeapSort

* We have to sort the array using heaps

## Using extra space

* We can create a separate heap (or a priority queue)
* We push all elements in the heap
* then we pop the elements and put them in the array
* For minheaps, the array will be sorted
* Complexity: `O(nlogn)`
* Space complexity: `O(n)`

## In constant space

* We can do the sorting in-place
1. First we build the array in a max heap
2. The element `this.heap[0]` is the maximum element in the heap now
3. So we swap it to the end, ie `swap(0, last_index)`
4. Now this array is no longer a valid heap at index `0` where we swapped.
5. So we call `maxheapify(0)` to make it a valid heap at index `0`
6. This would generally be it, a valid heap will form **but** the last element we just swapped in **step 3**
  * To prevent that we decrease the size of the heap by 1.
  * now the last element wont be a part of the heap so `maxheapify` will leave it be
  * On repeating the steps, we ll have the second largest element in the array at the top of the heap, so now we repeat the whole procedure of swapping and heapifying
7. We repeat the steps for all `i` in reverse order.

<details>
  <summary>
    <strong>Heap Sort Code</strong>
  </summary>

```java
  public void heapSort()  {
    buildMaxHeap();
    for(int i=heap_size-1; i > 0; --i)  {
      swap(i, 0);
      heap_size--;
      maxheapify(0);
    }
  }
```
</details>

# Codes

- [Java](../codes/Heap.md)
