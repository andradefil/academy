# Insertion Sort

## Analysis

The worst case time complexity of insertion sort is O(n2). The worst case happens when the array is reverse sorted. The best case time complexity of insertion sort is O(n). The best case happens when the array is already sorted.

The auxiliary space used is O(1) by the iterative version and O(n) by the recursive version for the call stack.

## Description

insertion sort is stable, in-place sorting algorithm that builds the final sorted array one item at a time. It is not very best in terms of performance but it is more efficient in practice than most other simple O(n2) algorithms such as selection sort or bubble sort. Insertion sort is also used in Hybrid sort which combines different sorting algorithms to improve performance.

It is also a well known online algorithm as it can sort a list as it receives it. In all other algorithms we need all elements to be provided to the sorting algorithm before applying it. But an insertion sort allows we to start with partial set of elements, sorts it (called as partially sorted set), and if we want, we can insert more elements (these are the new set of elements that were not in memory when the sorting started) and sorts these elements too. In real world, data to be sorted is usually not static, rather dynamic. If even one additional element is inserted during the sort process, other algorithms don’t respond easily. But only this algorithm is not interrupted and can respond well with the additional element.

How it works?
The idea is to divide the array into two subsets – sorted subset and unsorted subset. Initally sorted subset consists of only one first element at index 0. Then for each iteration, insertion sort removes next element from the unsorted subset, finds the location it belongs within the sorted subset, and inserts it there. It repeats until no input elements remain. Below example explains it all –

```
i = 1    [ 3  8  5  4  1  9  -2 ]
i = 2    [ 3  8  5  4  1  9  -2 ]           
i = 3    [ 3  5  8  4  1  9  -2 ]              
i = 4    [ 3  4  5  8  1  9  -2 ]
i = 5    [ 1  3  4  5  8  9  -2 ]
i = 6    [ 1  3  4  5  8  9  -2 ]
         [ -2  1  3  4  5  8  9 ]
``` 

> reference: https://www.techiedelight.com/insertion-sort-iterative-recursive/ 