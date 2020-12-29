package io.killb.algorithms.selectionsort

fun swap(arr: IntArray, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}

fun selectionSort(arr: IntArray) {
    for (i in 0 until arr.size - 1) {
        var min = i
        for (j in i + 1 until arr.size) {
            if (arr[j] < arr[min]) {
                min = j
            }
        }
        swap(arr, min, i)
    }
}

fun main(args: Array<String>) {
    val arr = intArrayOf(3, 5, 8, 4, 1, 9, -2)
    selectionSort(arr)
    println(arr.contentToString())
}