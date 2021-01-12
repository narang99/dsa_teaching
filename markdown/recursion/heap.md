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

<details>
  <summary>
    <strong>Basic parent child access code</strong>
  </summary>

```java
public class Heap {
  private List<Integer> heap;

  public Heap(List<Integer> arr) {
    // this.heap stores the array of level order traversal
    // heapify generates the correct level order traversal
    this.heap = arr;
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
  // other methods shown above skipped
  public void maxheapify(int root) {
    // base case
    if(root >= this.heap.size())
      return;

    int left = leftChild(root);
    int right = rightChild(root);

    // base case, root is leaf
    if(left >= this.heap.size() && right >= this.heap.size())
      return;

    // largest holds the index of the element
    // which holds the largest value among
    // root, left and right
    int largest;
    if(left < this.heap.size() &&
      this.heap.get(left) > this.heap.get(root) &&
      this.heap.get(left) >= this.heap.get(right)) {
      largest = left;
    }
    else if(right < this.heap.size() &&
      this.heap.get(right) > this.heap.get(root) &&
      this.heap.get(right) >= this.heap.get(left)) {
        largest = right;
    }
    else
      largest = root;

    if(largest != root) {
      // if largest value is at root, then the subtree rooted at root is already a valid maxheap
      // else we call recursively after swapping

      // swap
      int temp = this.heap.get(largest);

      this.heap.set(largest, this.heap.get(root));
      this.heap.set(root, temp);

      maxheapify(largest);
    }
  }
}
```

</details>

# Heap build

* We want to build the heap
* `heapify` can be somehow used to achieve this
* NOTE: `heapify` assumes that children are a valid heap when it is called on a root
* Therefore, when we call `heapify` on a node, we should have already called `heapify` on its children
* This suggests that we can start from children, call `heapify` on them and then call the procedure above
* remember that the array is already in a level order traversal
* Therefore last node in the array is the last leaf of the tree
* So we start from the end of the array and then call heapify on each preceding element

<details>
  <summary>
    <strong>Build Heap code</strong>
  </summary>

  ```java
  public class Heap { 
    public void buildMaxHeap()  {
      // start heapify from children
      // call on all
      for(int i=this.heap.size()-1; i >= 0; --i)  {
        maxheapify(i);
      }
    }
  }
  ```

</details>



# Codes
  * [Java](../codes/Heap.md)