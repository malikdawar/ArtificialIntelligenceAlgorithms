package com.example.academy

import org.w3c.dom.Node

abstract class AbstractDepthFirstSearch(var startNode: Node, var goalNode: Node) {
    abstract fun execute(): Boolean
}