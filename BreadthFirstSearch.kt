package com.example.academy

import org.junit.Test
import java.util.*

class BreadthFirstSearch {
    private var vertex = 0
    private lateinit var linkedLists: Array<LinkedList<Int>?>

    @Test
    fun test() {
        val g = BreadthFirstSearch()
        g.initialiseVertex(5)
        g.addEdge(0, 1)
        g.addEdge(0, 2)
        g.addEdge(1, 2)
        g.addEdge(2, 0)
        g.addEdge(2, 3)
        g.addEdge(3, 3)
        println("starting from 2")
        g.BFS(2)


        /*   Output
        starting from 1
        1 2 0 3*/
    }

    private fun initialiseVertex(vertex: Int) {
        this.vertex = vertex
        linkedLists = arrayOfNulls(vertex)

        for (i in 0 until vertex)
            linkedLists[i] = LinkedList<Int>()
    }

    fun addEdge(v: Int, w: Int) {
        linkedLists[v]!!.add(w)
    }

    private fun BFS(_node: Int) {
        var node = _node
        val visited = BooleanArray(vertex)
        val queue: LinkedList<Int> = LinkedList<Int>()
        visited[node] = true
        queue.add(node)
        while (queue.size != 0) {
            node = queue.poll()!!
            print("$node ")
            val i: Iterator<Int> = linkedLists[node]!!.listIterator()
            while (i.hasNext()) {
                val n = i.next()
                if (!visited[n]) {
                    visited[n] = true
                    queue.add(n)
                }
            }
        }
    }
}