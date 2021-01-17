package codes;
import java.util.*;

class  Hashing {
  class Node {
    String key;
    String value;

    Node(String k, String v)  {
      key = k;
      value = v;
    }
  }
  public List<List<Node>> hashTable;
  Hashing() {
    hashTable = new ArrayList<>();
    for(int i=0; i < 30; ++i)
      hashTable.add(new ArrayList<Node>());
  }

  int hash(String key) {
    int sum = 0;
    for(int i=0; i < key.length(); ++i) {
      sum += key.charAt(i);
    }
    return sum;
  }

  public String get(String key)  {
    int index = hash(key) % hashTable.size();
    List<Node> bucket = hashTable.get(index);

    for(int i=0; i < bucket.size(); ++i)  {
      Node current = bucket.get(i);
      if(key == current.key)  {
        return current.value;
      }
    }
    return "";
  }

  public void put(String key, String value) {
    int index = hash(key) % hashTable.size();
    List<Node> bucket = hashTable.get(index);
    for(int i=0; i < bucket.size(); ++i)  {
      if(bucket.get(i).key == key)  {
        bucket.set(i, new Node(key, value));
        return;
      }
    }

    bucket.add(new Node(key, value));
  }

  public static void main(String[] args) {
    Hashing sol = new Hashing();

    sol.put("21", "Hariom");
    sol.put("29", "shivani");
    
    System.out.println((sol.get("21")));

    sol.put("21", "not harionm");
    System.out.println((sol.get("21")));

    sol.put("59", "snli");
    System.out.println((sol.get("29")));
    System.out.println((sol.get("59")));
  }
}
