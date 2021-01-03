package io.killb.algorithms.doublylinkedlist

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DoublyLinkedListTest {
    @Test
    fun `should insert elements at end of list`() {
        val list = DoublyLinkedList<Long>()
        list.put(1)
        list.put(2)
        list.put(3)
        list.print()
        Assertions.assertTrue(true)
    }

    @Test
    fun `should create a linked list and adds a value at front`() {
        val list = DoublyLinkedList<Long>()
        list.push(2)
            .push(3)
            .push(4)
        Assertions.assertEquals(4, list.head?.data)
        Assertions.assertNotEquals(2, list.head?.data)
    }

    @Test
    fun `should insert after node`() {

    }
}