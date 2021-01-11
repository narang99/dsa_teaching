# Questions

## Insertion sort: 

* best case: array is already sorted, complexity is `O(n)` in this case
* worst case: array sorted in reverse order

## Merge sort:

* complexity of merging
* how `logn` comes
    * for size `n`, the array is repeatedly divided
    * each division makes it half
    * makes a tree of sorts
    * we return when we have leaf
    * so the height of tree `k`:
    * `2^k = n`
    * `n = log k`
* Recurrence relation:
    * `T(n) = O(1)` if `n = 1`
    * `T(n) = 2T(n/2) + O(n)` otherwise

## Binary search:

* recurrence relation: `T(n) = T(n/2) + O(1)`
* solving: `[n] + [n/2] + [n/4] + ...`
* `2^k = n` -> `k = log n` terms in the expression
* constant work for each
* Therefore complexity: `log n`


## Exercise questions

* Q 2-1: Insertion sort inside merge sort Pg 40
* Q 2-4: counting inversions

## Methods of finding complexity bounds for recursive equations
* use all these to find complexity of 
  * `T(n) = 2T(n/2) + cn`
  * `T(n) = 2T(n/2) + c`

### Substitution
* make guess
* prove it using induction

### Recursion tree

* make a tree of the recursive invocation
* each node has a cost, is a point somewhere in the recursion
* the above cost is the work done in that node. example
  * `T(n) = 2T(n/2) + cn`
  * here for node with `T(n)` the cost is `cn`
  * for node `T(n/2)` the cost is `cn/2`
* sum up all costs of nodes in one level
* then sum up the total costs of all levels
* [Recursion Tree Example](./../../assets/rec_tree_complexity.png)


### Masters theorem
 
* wont cover now
