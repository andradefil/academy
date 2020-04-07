package insertionsort

@Suppress("DuplicatedCode")
private fun insertionSort(arr: IntArray, i: Int) {
    val v = arr[i]
    var j = i

    while (j > 0 && arr[j - 1] > v) {
        arr[j] = arr[j - 1]
        j--
    }
    arr[j] = v
    if (i + 1 < arr.size) {
        insertionSort(arr, i + 1)
    }
}

fun main(args: Array<String>) {
    val arr = intArrayOf(3, 8, 5, 4, 1, 9, -2)
    insertionSort(arr, 0)
}