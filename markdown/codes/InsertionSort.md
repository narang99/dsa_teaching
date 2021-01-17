```java
package codes;

public class InsertionSort {
  public static void insSort(int[] arr) {
    int i = 1;
    for(i=1; i < arr.length; ++i) {
      // check all backs
      for(int j=i; j > 0; --j)  {
        if(arr[j] < arr[j-1]) {
          // swap
          int temp = arr[j];
          arr[j] = arr[j-1];
          arr[j-1] = temp;
        }
      }
    }
  }
  public static void main(String[] args) {
    int[] arr = {1,2,1,5,2,1};
    insSort(arr);
    for(int i : arr)  {
      System.out.print(Integer.toString(i) + " ");
    }
    System.out.println("");
  } 
}

```