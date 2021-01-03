package io.killb.algorithms.doublylinkedlist

class DoublyLinkedList<T> {
    var head: Node<T>? = null

    fun print() {
        var last = head
        var node = head

        println("\n>>> forward direction")
        while(node != null) {
            print("${node.data},")
            last = node
            node = node.next
        }

        println("\n<<< reverse direction")
        while(last != null) {
            print("${last.data},")
            last = last.prev
        }
        println()
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

    fun insertBefore(afterNode: Node<T>, item: T): DoublyLinkedList<T> {
        val node = Node(item)
        node.next = afterNode
        node.prev = afterNode.prev
        afterNode.prev?.next = afterNode
        afterNode.prev = node
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