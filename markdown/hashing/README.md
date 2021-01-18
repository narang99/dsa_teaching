# Hash


* Hashing is practically the optimal solution for constant(`O(1)`) lookup times
* You have keys which are used for indexing your values
* Generally your keys will be unique
* Basically, a hash table is just a fancy array

# Hashing

* Assume you have keys in range `[0..10]`
* Each key has an associated value
* A way to store this combination is to just all the key-value pairs in an array
* You index the array using the **key** and the value in the array is the **value** associated with the key

## Example

* As an example, we have say a list of ages and names for people, the list is:
	* 21: Hariom
	* 29: Shivani
	* 30: Tapan
	* 56: Sunil
	* 54: Sangita
* Your task is:
	* Given an age, find which person has that age
	* So if `21` is given, it should output `Hariom`

## Solution

* There are multiple ways to do this

### Store in Array, Iterate to search

* Create an array of pairs, given an `age` for the task, iterate through the array to find the correspoding value
	* Time complexities:
		* Insertion: `O(n)`
		* Search   : `O(n)`
		* Remove : `O(n)`
	* Space complexity = `O(n)`
	* Here `n` is the number of people.

```java
class Solution	{
	class Person {
		int age;
		String name;
		
		Person(int age, int name)	{
			this.age = age;
			this.name = name;
		}
	}
	static List<Person> personList = new List<Person>();

	// returns empty string in case of some age that is not in  the list
	static String getNameArray(int age)	{
		for(int i=0; i < personList.size(); ++i)	{
			if(age == personList.get(i).age)	{
				return personList.get(i).name;
			}
		}
		return "";
		
	}

	static void insertArray(int age, String name)	{
		for(int i=0; i < personList.size(); ++i)	{
			// duplicates not allowed, change the name here and return
			if(personList.get(i).age == age)	{
				personList.get(i).name = name;
				return;
			}
		}
		// did not find key in the list, add it
		personList.add(new Person(age, name));	
	}

	static void removeArray(int age)	{
		int i=0;
		for(i = 0; i < personList.size(); ++i)	{
			if(personList[i].age == age)	{
				break;
			}
		}
		// found index in age
		if(i != personList.size())
			personList.remove(i);
	}
	
	public static void main(String[] args)	{
		// testing
		insertArray(29, "Shivani");
		insertArray(30, "Tapan");
		insertArray(56, "Sunil");
		insertArray(54, "Sangita");
		insertArray(21, "Hariom");

		System.out.println(getNameArray(21));   // Hariom
		System.out.println(getNameArray(54));   // Sangita
		System.out.println(getNameArray(34));   // Does not exist, returns empty string ""

		removeArray(21);
	}

}

```
### Using BST
* Using Sorting or BST
	* You store all your `(key,value)` pairs in a **BST**, or you can put it in an array and sort it
	* If you sort the array, then you can binary search for the key in the sorted array
	* In BST, you can directly use the `search` operation
	* Time complexity:
		* Insert: `O(log n)`
		* Remove: `O(log n)`
		* Search: `O(log n)`
	* Space Complexity: `O(n)`
	* In Java, the `TreeMap` class implements a balanced binary search tree

```java
import java.util.*;

public class Solution  {
	static Map<Integer, String> treeMap = new TreeMap<Integer, String>();
	static String getNameTree(int age)	{
		if(treeMap.containsKey(age))
			return treeMap.get(age);
		else
			return "";

	}

	public static insertTree(int key, String value)	{
		treeMap.put(key, value);
	}

	public static removeTree(int key)	{
		treeMap.remove(key);
	}

	public static void main(String[] args)	{
		insertTree(21, "Hariom");
		insertTree(29, "Shivani");
		insertTree(30, "Tapan");
		insertTree(56, "Sunil");
		insertTree(54, "Sangita);
		
		// testing
		System.out.println(getNameTree(21));   // Hariom
		System.out.println(getNameTree(54));   // Sangita
		System.out.println(getNameTree(34));   // Does not exist, returns empty string ""

		removeTree(21);
	}

}
```

### Using Hashing

* We will implement the most basic form of hashing that will only work specifically for our example
* We are not gonna use `HashMap`

#### Approach

* Our maximum age is `56`
* We create an array of size `57` to hold all values (in this case, the `name` of the person)
* **NOTE:** Our array size is one more than maximum age
* In this case, `age` is the **key** of our data, `name` is the **value**
* We call the array `hashTable` (this is convention)
* For every age we get, we check if it is greater than size of our array, if it is then we return empty string, else return the string

```java
class Solution {
	static List<String> hashTable = new ArrayList(57);

	static void insertHashTable(int age, String name)	{
		hashTable.set(age, name);
	}

	static void removeHashTable(int age)	{
		if(age < hashTable.size())
			hashTable.set(age, "");
		// cant set
		throw Exception("cant set for index greater than size of array");
	}

	static String searchHashTable(int age)	{
		if(age < hashTable.size())
			hashTable.get(age);
		
		// cant set
		throw Exception("cant get for index greater than size of array");
	}
	public static void main()	{
		// testing
		insertHashTable(21, "Hariom");	
		insertHashTable(29, "Shivani");	
		insertHashTable(30, "Tapan");	
		insertHashTable(56, "Sunil");	
		insertHashTable(54, "Sangita");	

			
		System.out.println(searchHashTable(21));   // Hariom
		System.out.println(searchHashTable(54));   // Sangita
		System.out.println(searchHashTable(34));   // Does not exist, returns empty string ""
	}
}
```

# Codes

* [Java](../codes/Hashing.md)
* [Generic HashMap using Casting](../codes/HashMapUsingCast.md)
* [Generic HashMap using templates](./GenericHashMap.md)