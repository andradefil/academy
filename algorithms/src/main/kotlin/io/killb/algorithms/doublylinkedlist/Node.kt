package io.killb.algorithms.doublylinkedlist

data class Node<T>(
    val data: T,
    var next: Node<T>? = null,
    var prev: Node<T>? = null
)