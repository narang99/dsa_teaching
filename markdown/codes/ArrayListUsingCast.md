```java
package codes;

/**
 * this class uses simple Object casting to create the array 
 */
public class ArrayListUsingCast {
  private Object[] arr;
  private int capacity;
  private int size;
  public ArrayListUsingCast(int cap)  {
    size = 0;
    capacity = cap;
    arr = new Object[capacity];
  }

  public void add(Object element) {
    arr[size] = element;
    size++;
  }

  public Object get(int index) {
    return arr[index];
  }

  public int size()  {
    return size;
  }

  public int capacity()  {
    return capacity;
  }

  public static void main(String[] args) {
    ArrayListUsingCast list = new ArrayListUsingCast(30);

    /**
     * since `ArrayListUsingCast` takes an `Object` class
     * as parameter, you need to cast the element
     * you are pushing to `Object` 
     * While using `get`, you need to cast the result to
     * your value
     */

    list.add((Object) 23);
    list.add((Object) 43);
    list.add((Object) "Hariom");

    int ele1 = (int) list.get(0);
    int ele2 = (int) list.get(1);
    String ele3 = (String) list.get(2);

    /**
     * NOTE: You need to rememeber the type of element 
     * to cast back to in case of get
     * 
     * For example if you casted the element 2, ie result
     * of list.get(2) you will get an error, since you 
     * previously had pushed a string inside
     * 
     * Eg
     * 
     * // THIS GIVES ERROR AT RUNTIME
     * int ele3 = (int) list.get(2);
     */

    // this will print ele1 and ele2
    System.out.println(Integer.toString(ele1) + " " +
        Integer.toString(ele2) + " " +
        ele3);
  }
}

```