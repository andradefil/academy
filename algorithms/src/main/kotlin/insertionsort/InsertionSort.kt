package insertionsort

@Suppress("DuplicatedCode")
private fun insertionSort(arr: IntArray) {
    for (i in 1 until arr.size) {
        val v = arr[i]
        var j = i

        // to discover the less so the previous is greater than current
        while (j > 0 && arr[j - 1] > v) {
            arr[j] = arr[j - 1]
            j--
        }
        arr[j] = v
    }
}

fun main(args: Array<String>) {
    val arr = intArrayOf(3, 8, 5, 4, 1, 9, -2)
    insertionSort(arr)
    println(arr.contentToString())
}