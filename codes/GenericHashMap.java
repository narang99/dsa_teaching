package codes;
import java.util.*;

public class GenericHashMap<K extends Object, V> {
  class Node {
    K key;
    V value;
    public Node(K k, V v) {
      key = k;
      value = v;
    }
  }
  List<List<Node>> hashTable;
  public GenericHashMap(int size) {
    hashTable = new ArrayList<>(size);
    for(int i=0; i < size; ++i)
      hashTable.add(new ArrayList<>());
  }

  public V get(K key) {
    int index = key.hashCode() % hashTable.size();
    if(index < 0)
      index = -index;
    List<Node> bucket = hashTable.get(index);
    for(int i=0; i < bucket.size(); ++i)  {
      Node current = bucket.get(i);
      if(current.key == key)
        return current.value;
    }
    return null;
  }

  public void put(K key, V value) {
    int index = key.hashCode() % hashTable.size();
    if(index < 0)
      index = -index;
    List<Node> bucket = hashTable.get(index);
    for(int i=0; i < bucket.size(); ++i)  {
      Node current = bucket.get(i);
      if(current.key == key)  {
        bucket.set(i, new Node(key, value));
        return;
      }
    }
    bucket.add(new Node(key, value));
  }

  public static void main(String[] args) {
    GenericHashMap<String, Integer> map = 
      new GenericHashMap<>(30);
    
    map.put("Hariom", 21);
    map.put("shivani", 29);

    System.out.println(Integer.toString(map.get("Hariom")));

    GenericHashMap<String, String> nameMap = 
      new GenericHashMap<>(30);
    nameMap.put("hariom", "narang");
    nameMap.put("shivani", "narang sr.");

    System.out.println(nameMap.get("shivani"));
  }
}
