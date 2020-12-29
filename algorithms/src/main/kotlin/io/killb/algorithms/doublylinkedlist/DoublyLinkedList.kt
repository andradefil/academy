package io.killb.algorithms.doublylinkedlist

class DoublyLinkedList<T> {
    var head: Node<T>? = null

    fun push(data: T): DoublyLinkedList<T> {
        val node = Node(data)
        node.prev = this.head
        head?.apply { next = node }
        head = node
        return this
    }
}