# Generics

* Generics (angular bracket stuff) provide a better syntax and compiler support by enhancing a particular stype of coding
* Normally generics are using everywhere you have code that is casting between `Object` etc to provide abstration
* The syntax and usage will be evident with examples

# Example and Code walk through

* our `HashMap` is called `GenericHashMap`
* The important paramters for it are `key` and `value`
* The types of `key` and `value` will change
* we need access to the `hashCode` function of `key` in the class to get the hash value
* The types of `key` and `value` are polymorphic
* We can use generic types to represent them
* For generic types, it is convention to use single letter capital letters
* Type of `key` is `K` and `value` is `V`

```java
public class GenericHashMap<K, V> {

}
```
* According to the above definition
  * When you make a new object of type `GenericHashMap`, you specify two type parameters, which will be copied to `K` and `V`
  * example instantiation: `GenericHashMap<String, Integer> = new GenericHashMap<>(30);`
* Therefore when I pass `String` and `Integer` as arguments inside the angular brackets, you can imagine that the compiler will replace `K` with `String` and `V` with `Integer` in the implementation code of `GenericHashMap`

* `get` method signature
```java
public V get(K key);
```
* that is, it takes a `key` of type `K` and its return type is `V`. If we pass `String` and `Integer` as above, you can imagine the compiler copies `String` to `K` and `Integer` to `V`
* That is new method name would be (internally in the compiler)
```java
public Integer get(String key);
```

* `put` method signature
```java
public void put(K key, V value);
```

* Now we need the `key` attribute to have a hash function. A general hash function is defined in the `Object` class. Therefore, our `key` variable should inherit from `Object` class
* Therefore type `K` (the type of `key`) should inherit from `Object`
* This forces the compiler to check everytime that the parameter `K` is a descendent of `Object`. the syntax for that is:
```java
public class GenericHashMap<K extends Object, V>  {

}
```
* Thats it. You specify that `K` should inherit frmo `Object` by doing `K extends Object` in the class definition
* The `hashTable` is a list of lists which holds elements of types `Node`. The `Node` element stores the `key` whose type is `K` and value whose type is `V`. Therefore defnition of `Node`:

```java
class Node {
  K key;
  V value;
}
```

* **NOTE:** if `Node` class was outside the `GenericHashMap` class (ie not nested inside it)  then its definition would be

```java
class Node<K, V> {
  K key;
  V value;
}
```

* Now constructor would be;
```java
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
```

* `get` method

```java
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
```

* `put` method

```java
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
```

# Codes

* [Java](../codes/GenericHashMap.md)