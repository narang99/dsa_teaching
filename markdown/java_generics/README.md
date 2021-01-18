# Generics

* This is a coding style used in Java, C++, TypeScript etc

# Need for Generic Code

* Consider the example of the `ArrayList` container in Java
* This container stores a list of elements efficiently as an array
* We can ourselves make an `ArrayList` implementation using a simple array. But how would we specify the type of the array?
* How would we make it so that you can store any type of element in the list?
* Normally if we use an array, we have to specify a type, eg `int[]`, `String[]` etc
* This is where generic code comes in
  * When you specify `ArrayList<Integer>`, the library creates an array of type `Integer` internally
  * This would not be possible without generics
* Go to [ArrayList Implementation](./ArrayList.md) before going to next section

# Generic HashMap

* [HashMap using Object Casting](../codes/HashMapUsingCast.md)
* [Generic HashMap example](../hashing/GenericHashMap.md)
