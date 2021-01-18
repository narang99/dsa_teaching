# ArrayList Implementation

* Here we will implement a generic `ArrayList` (by generic we mean that it can create a list of any type of element)
* For simplicity, we will not implement the growing part of the `ArrayList` container, ie. it would be a fixed capacity container

# Implementation using Casting

## Object Class

* Every object in Java always has a parent class called `Object`
* You can think of it as the super parent of sorts, an ancestor of all elements
* The methods `toString`, `hashCode` etc are defined in the `Object` class.
* **NOTE:** `hashCode` method defined for `Object` is literally a `hash` function, exactly the one we using in [Hashing](../hashing/README.md).
  * Instead of calling `hash(element)`, you call `element.hashCode()`

## Inheritance

* If your class doesn't inherit from any other class, then `Object` is by default its parent.
* If your class inherits from some other class, then its parent is the class. However, the root of the parent chain is always `Object`

### Example

```java
class Parent {

}

class Child extends Parent {

}

class Orphan {

}
```

* In this example, the parent of `Child` class is `Parent` class.
* The parent of `Parent` class is nothing. So by default `Object` is made its parent
* Same for `Orphan` (ie. its parent will be `Object`)
* `Object` class doesn't have any parent

## Casting

### Casting in primitive types 

* Type casting examples
* You can cast a `long` type to `int` type

```java
public static void main(String[] args) {
  long a = 1;
  int b;
  b = (int) a;
  System.out.println(Integer.toString(b)); 
}
```

* Here we casted the `long` type variable `a` to `int` and stored it in `b`

### Casting between objects

* You can only cast an object to another if the other object is either its ancestor or descendent

```java
class Parent {

}
class Child extends Parent {

}
class GrandChild extends Child {

}

class Test {
  public static void main(String[] args)  {
    Parent parent = new Parent();
    GrandChild gchild = (GrandChild) parent;

    Parent otherParent;
    GrandChild othergchild = new GrandChild();
    otherParent = (Parent) othergchild;

    Object ancestorOfAll;
    ancestorOfAll = (Object) gchild;
  }
}
```

* Since `Object` is an ancestor for every element in Java, you can cast any object to this class.

* **NOTE** when a child is casted to a parent class, its called **upcasting**
* **NOTE** when a parent is casted to a child class, its called **downcasting**

## Final Implementation

* We will use casting and the fact that `Object` is parent of all classes to create a generic `ArrayListUsingCast` container
* Internally we make an array of `Object`. The array looks like
```java
Object[] arr;
arr = new Object[capacity];
```
* **NOTE:** We initialize it to a static capacity. 
* Since this is an array of `Object`, we can only add elements of type `Object` to it. Therefore `add` will take an `Object` as input. `get` will return an `Object` as output.

* The container will have `add`, `get` methods.
* Type signature of `add` and `get` methods are:

```java
void add(Object element)
Object get(int index)
```

* As you can see, `add` will take an element of type `Object`
* To add something to your container, you have to first cast it to `Object` type.

```java
public static void main(String[] args)  {
  int e1 = 10;
  int e2 = 20;

  list.add((Object) e1);
  list.add((Object) e2);

  int ele1 = (int) list.get(e1);
  int ele2 = (int) list.get(e2);

  System.out.println(Integer.toString(ele1) + " " +
      Integer.toString(ele2));
}
```

* As you can see this is very clumsy notation.
* We keep casting back from `Object` to `int`
* Generics or Templates (the angular brackets thingy makes this better)

# Codes

* [Java Implementation](../codes/ArrayListUsingCast.md)

# Next

* Another example of a container which will use `Object` casting is [hash containers using cast](./HashMapUsingCast.md).
* After doing the above example, go to [Generic Hash Map](../hashing/GenericHashMap.md)