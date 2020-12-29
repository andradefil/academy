package io.killb.algorithms.doublylinkedlist

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NodeAtFrontTest {
    @Test
    fun `should adds a node at front of the list`() {
        var head = Node(data = 0L)
        val node = Node(data = 1L)
        head.prev = node
        node.next = head
        head = node

        Assertions.assertNull(head.prev)
        Assertions.assertNotNull(head.next)
        Assertions.assertEquals(node, head)
    }
}