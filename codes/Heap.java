package codes;

import java.util.List;

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

      this.maxheapify(largest);
    }
  }

  public void buildMaxHeap()  {
    // start heapify from children
    // call on all
    for(int i=this.heap.size()-1; i >= 0; --i)  {
      maxheapify(i);
    }
  }
}
