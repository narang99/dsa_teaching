```java
package codes;

import java.util.List;
import java.util.Arrays;

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

  public void buildMaxHeap()  {
    // start heapify from children
    // call on all
    for(int i=this.heap_size-1; i >= 0; --i)  {
      maxheapify(i);
    }
  }

  public String toString()  {
    String s = "";
    for(int i : this.heap)  {
      s += Integer.toString(i) + " ";
    }
    return s;
  }

  /**
   * maxheapify only works on elements inside the heap array 
   * the heap array is part of list this.heap[0..heap_size] 
   * After buildMaxHeap, the first element in the array will
   * be largest and the heap_size is this.heap.size(). 
   * So we swap it to the end of the array
   * now the first element(this.heap[0]) is not satisfying the
   * heap property. So we need to call heapify on it
   * However, heapify should not change the position of the
   * last element, or the element we just swapped
   * So we decrease heap_size by 1
   */
  public void heapSort()  {
    buildMaxHeap();
    for(int i=heap_size-1; i > 0; --i)  {
      swap(i, 0);
      heap_size--;
      maxheapify(0);
    }
  }

  public static void main(String[] args) {
    List<Integer> arr = Arrays.asList(new Integer[]{
      13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7
    });
    Heap heap = new Heap(arr);
    System.out.println("unsorted array: " +
            heap.toString());

    heap.heapSort();

    System.out.println("sorted array: " + 
            heap.toString());
  }
}

```