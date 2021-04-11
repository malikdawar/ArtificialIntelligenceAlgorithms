package com.example.academy

import org.junit.Test
import java.util.*

class IterativeDeepeningSearch {

    var vertex = 0
    lateinit var linkedLists: Array<LinkedList<Int>?>

    @Test
    fun depthFirst() {
        val iterativeDeepeningAlgorithm = IterativeDeepeningSearch()

        iterativeDeepeningAlgorithm.apply {
            initialiseVertex(10)
            addEdge(1, 2)
            addEdge(5, 2)
            addEdge(6, 6)
            addEdge(0, 8)
            addEdge(8, 9)
            println("Following is the Depth First Traversal")
            iterativeDeepeningSearch(0)
        }

        /* My OutPut: 0 8 9 */
    }

    private fun initialiseVertex(vertex: Int) {
        this.vertex = vertex
        linkedLists = arrayOfNulls(vertex)
        for (i in linkedLists.indices) linkedLists[i] = LinkedList()
    }

    private fun addEdge(v: Int, w: Int) {
        linkedLists[v]!!.add(w)
    }

    private fun iterativeDeepeningSearch(_node: Int) {
        var node = _node
        val visited = Vector<Boolean>(vertex)
        for (i in 0 until vertex) visited.add(false)
        val stack = Stack<Int>()
        stack.push(node)
        while (stack.empty().not()) {
            node = stack.peek()
            stack.pop()
            if (visited[node] == false) {
                print("$node ")
                visited[node] = true
            }
            val itr: Iterator<Int> = linkedLists[node]!!.iterator()
            while (itr.hasNext()) {
                val v = itr.next()
                if (!visited[v]) stack.push(v)
            }
        }
    }
}