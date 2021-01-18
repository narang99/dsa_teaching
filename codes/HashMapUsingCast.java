package codes;

import java.util.*;

public class HashMapUsingCast {
  class Node {
    Object key;
    Object value;
    public Node(Object k, Object v) {
      key = k;
      value = v;
    }
  }
  List<List<Node>> hashTable; 

  public HashMapUsingCast(int size)   {
    hashTable = new ArrayList<>();

    // initialize with size `size`
    for(int i=0; i < size; i++)
      hashTable.add(new ArrayList<>());
  }

  public Object get(Object key) {
    int index = key.hashCode() % hashTable.size();
    if(index < 0)
      index = -index;
    // System.out.println(Integer.toString(key.hashCode() % hashTable.size()));
    List<Node> bucket = hashTable.get(index);
    for(int i=0; i < bucket.size(); i++)  {
      Node current = bucket.get(i);
      if(current.key == key)
        return current.value;
    }
    return null;
  }

  public void put(Object key, Object value) {
    // System.out.println(Integer.toString(key.hashCode() % hashTable.size()));
    int index = key.hashCode() % hashTable.size();
    if(index < 0)
      index = -index;
    List<Node> bucket = hashTable.get(index);
    for(int i=0; i < bucket.size(); i++)  {
      Node current = bucket.get(i);
      if(current.key == key)  {
        bucket.set(i, new Node(key, value));
        return;
      }
    }
    bucket.add(new Node(key, value));
  }


  public static void main(String[] args) {
    HashMapUsingCast map = new HashMapUsingCast(30);

    map.put((Object) "Hariom", (Object) 21);
    map.put((Object) "Shivani", (Object) 29);
    map.put((Object) "Tapan", (Object) 30);
    map.put((Object) "Sangita", (Object) 54);

    int age1 = (int) map.get((Object) "Hariom");
    int age2 = (int) map.get((Object) "Shivani");
    int age3 = (int) map.get((Object) "Sangita");
    int age4 = (int) map.get((Object) "Tapan");
    System.out.println(Integer.toString(age1));
    System.out.println(Integer.toString(age2));
    System.out.println(Integer.toString(age3));
    System.out.println(Integer.toString(age4));
  }
}
