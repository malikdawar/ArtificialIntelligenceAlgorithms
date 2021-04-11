package com.example.academy

import org.w3c.dom.Node
import java.util.*

class DepthLimitedDepthFirstSearch(var initialNode: Node, var destinationNode: Node) :
    AbstractDepthFirstSearch(
        initialNode, destinationNode
    ) {
    var depth = 0
    var limit = 2
    override fun execute(): Boolean {
        val nodeStack = Stack<Node>()
        val visitedNodes = ArrayList<Node>()
        nodeStack.add(initialNode)
        depth = 0
        while (!nodeStack.isEmpty()) {
            if (depth <= limit) {
                val current = nodeStack.pop()
                if (current == destinationNode) {
                    print(visitedNodes)
                    println("Goal node found")
                    return true
                } else {
                    visitedNodes.add(current)
                    nodeStack.addAll((current.childNodes as Collection<Node>))
                    depth++
                }
            } else {
                println("Goal Node not found within depth limit")
                return false
            }
        }
        return false
    }
}