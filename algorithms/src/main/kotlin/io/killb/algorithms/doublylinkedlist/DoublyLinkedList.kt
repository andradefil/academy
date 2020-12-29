package io.killb.algorithms.doublylinkedlist

class DoublyLinkedList<T> {
    var head: Node<T>? = null

    fun insertAfter(prevNode: Node<T>, data: T): DoublyLinkedList<T> {
        val newNode = Node(data)
        // points to previous node
        newNode.prev = prevNode
        // points to next node
        newNode.next = prevNode.next
        // points next node back to new node
        newNode.next?.prev = newNode
        // points next from previous node to new node
        prevNode.next = newNode
        return this
    }

    fun push(data: T): DoublyLinkedList<T> {
        val node = Node(data)
        node.prev = this.head
        head?.apply { next = node }
        head = node
        return this
    }
}