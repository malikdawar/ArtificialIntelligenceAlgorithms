package com.example.academy

import org.junit.Test
import java.util.*

class UniformCostSearch(private val numberOfNodes: Int) {

    private val priorityQueue: PriorityQueue<Node>
    private val settled: MutableSet<Int>
    private val distances: IntArray
    private val adjacencyMatrix: Array<IntArray>
    private val path: LinkedList<Int>
    private val superParent: IntArray
    private var source = 0
    private var destination = 0

    private fun uniformCostSearch(adjacencyMatrix: Array<IntArray>?, source: Int, destination: Int): Int {
        var evaluationNode: Int
        this.source = source
        this.destination = destination
        for (i in 1..numberOfNodes) {
            distances[i] = MAX_VALUE
        }

        priorityQueue.add(Node(source, 0))
        distances[source] = 0
        while (!priorityQueue.isEmpty()) {
            evaluationNode = nodeWithMinDistanceFromPriorityQueue
            println("The eval Node is $evaluationNode")
            if (evaluationNode == destination) {
                return distances[destination]
            }
            settled.add(evaluationNode)
            addFrontiersToQueue(evaluationNode)
        }
        return distances[destination]
    }

    private fun addFrontiersToQueue(evaluationNode: Int) {
        for (destination in 1..numberOfNodes) {
            if (!settled.contains(destination)) {
                if (adjacencyMatrix[evaluationNode][destination] != MAX_VALUE) {
                    if (distances[destination] > adjacencyMatrix[evaluationNode][destination]
                        + distances[evaluationNode]
                    ) {
                        distances[destination] = (adjacencyMatrix[evaluationNode][destination]
                                + distances[evaluationNode])
                        superParent[destination] = evaluationNode
                    }
                    val node: Node = Node(destination, distances[destination])
                    if (priorityQueue.contains(node)) {
                        priorityQueue.remove(node)
                    }
                    priorityQueue.add(node)
                }
            }
        }
    }

    private val nodeWithMinDistanceFromPriorityQueue: Int
        private get() {
            val node = priorityQueue.remove()
            return node.node
        }

    fun printPath() {
        path.add(destination)
        var found = false
        var vertex = destination
        while (!found) {
            if (vertex == source) {
                found = true
                continue
            }
            path.add(superParent[vertex])
            vertex = superParent[vertex]
        }
        println("The Path between $source and $destination is ")
        val iterator = path.descendingIterator()
        while (iterator.hasNext()) {
            print(iterator.next().toString() + "\t")
        }
    }

    @Test
    fun test() {
        val adjacency_matrix: Array<IntArray>
        val number_of_vertices: Int
        var source = 0
        var destination = 0
        val distance: Int
        try {
            println("Enter the number of vertices")
            number_of_vertices = 7
            adjacency_matrix = Array(number_of_vertices + 1) { IntArray(number_of_vertices + 1) }
            println("Enter the Weighted Matrix for the graph")
            for (i in 1..number_of_vertices) {
                for (j in 1..number_of_vertices) {
                    adjacency_matrix[i][j] = 6
                    if (i == j) {
                        adjacency_matrix[i][j] = 0
                        continue
                    }
                    if (adjacency_matrix[i][j] == 0) {
                        adjacency_matrix[i][j] = MAX_VALUE
                    }
                }
            }
            println("Enter the source ")
            source = 1
            println("Enter the destination")
            destination = 7
            val uniformCostSearch = UniformCostSearch(number_of_vertices)
            distance = uniformCostSearch.uniformCostSearch(adjacency_matrix, source, destination)
            uniformCostSearch.printPath()
            println(
                """
The Distance between source $source and destination $destination is $distance"""
            )
        } catch (inputMismatch: InputMismatchException) {
            println("Wrong Input Format")
        }


        /*outPUT
        *
        *   0 5 0 3 0 0 0
            0 0 1 0 0 0 0
            0 0 0 0 6 0 8
            0 0 0 0 2 2 0
            0 4 0 0 0 0 0
            0 0 0 0 0 0 3
            0 0 0 0 4 0 0
        *
        *   The Path between 1 and 7 is
            1	4	6	7
            The Distance between source 1 and destination 7 is 8
        *
        * */
    }

    internal inner class Node : Comparator<Node> {
        var node = 0
        var cost = 0

        constructor() {}
        constructor(node: Int, cost: Int) {
            this.node = node
            this.cost = cost
        }

        override fun compare(node1: Node, node2: Node): Int {
            if (node1.cost < node2.cost) return -1
            if (node1.cost > node2.cost) return 1
            return if (node1.node < node2.node) -1 else 0
        }

        override fun equals(obj: Any?): Boolean {
            if (obj is Node) {
                if (this.node == obj.node) {
                    return true
                }
            }
            return false
        }
    }

    companion object {
        const val MAX_VALUE = 999
    }

    init {
        settled = HashSet()
        priorityQueue = PriorityQueue(
            numberOfNodes, Node()
        )
        distances = IntArray(numberOfNodes + 1)
        path = LinkedList()
        adjacencyMatrix = Array(numberOfNodes + 1) { IntArray(numberOfNodes + 1) }
        superParent = IntArray(numberOfNodes + 1)
    }
}