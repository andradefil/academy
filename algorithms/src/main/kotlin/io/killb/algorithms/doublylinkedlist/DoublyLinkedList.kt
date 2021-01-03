package io.killb.algorithms.doublylinkedlist

class DoublyLinkedList<T> {
    var head: Node<T>? = null

    fun print() {
        var last = head
        while(last != null) {
            println("Item: ${last.data}")
            last = last.next
        }
    }

    fun put(item: T): DoublyLinkedList<T> {
        val node = Node(item)
        var last = head
        if (head === null) {
            head = node
            return this
        }
        while (last!!.next != null) {
           last = last.next
        }
        last.next = node
        node.prev = last
        return this
    }

    fun insertAfter(prevNode: Node<T>, data: T): DoublyLinkedList<T> {
        val newNode = Node(data)
        newNode.prev = prevNode
        newNode.next = prevNode.next
        newNode.next?.prev = newNode
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