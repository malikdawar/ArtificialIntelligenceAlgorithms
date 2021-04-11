package com.example.academy

import org.junit.Test
import java.util.*

class DepthFirstSearch {
    private var vertex = 0
    private lateinit var linkedLists: Array<LinkedList<Int>?>

    @Test
    fun depthFirst() {
        val depthFirstSearch = DepthFirstSearch()
        depthFirstSearch.apply {
            initialiseVertex(10)
            addEdge(0, 1)
            addEdge(1, 2)
            addEdge(5, 5)
            addEdge(8, 4)
            addEdge(5, 7)
            addEdge(2, 4)

            depthFirstSearch()
        }

        /* My OutPut: 0 1 2 4 3 5 7 6 8 9 */
    }

    private fun initialiseVertex(vertex: Int) {
        this.vertex = vertex
        linkedLists = arrayOfNulls(vertex)
        for (i in 0 until vertex)
            linkedLists[i] = LinkedList<Int>()
    }

    private fun addEdge(v: Int, w: Int) {
        linkedLists[v]!!.add(w) // Add w to v's list.
    }

    private fun depthFirstUtil(v: Int, visited: BooleanArray) {
        visited[v] = true
        print("$v ")
        val i: Iterator<Int> = linkedLists[v]!!.listIterator()
        while (i.hasNext()) {
            val n = i.next()
            if (!visited[n]) depthFirstUtil(n, visited)
        }
    }

    private fun depthFirstSearch() {
        val visited = BooleanArray(vertex)
        for (i in 0 until vertex) if (!visited[i]) depthFirstUtil(i, visited)
    }
}