package com.example.academy

import org.junit.Test
import java.util.*

class Bidirectional {

    @Test
    fun testBidirectionalSearch() {
        val bidirectional = Bidirectional()
        bidirectional.minMutation("hello", "hi", arrayOf("h", "b"))
    }

    fun minMutation(start: String, end: String, bank: Array<String?>): Int {
        val graph: Array<MutableList<Int>?> = arrayOfNulls(bank.size)
        for (i in bank.indices) {
            graph[i] = ArrayList()
            for (j in bank.indices) if (canMutate(bank[i]!!, bank[j]!!)) graph[i]!!.add(j)
        }
        var targetIdx = -1
        for (i in bank.indices) {
            if (bank[i] == end) {
                targetIdx = i
                break
            }
        }
        if (targetIdx == -1) return -1

        // endXXX means something calculated from end to start
        val endDist = IntArray(bank.size)
        Arrays.fill(endDist, -1)
        endDist[targetIdx] = 0
        val endQ: Queue<Int> = ArrayDeque()
        for (idx in graph[targetIdx]!!) {
            endQ.add(idx)
            endDist[idx] = 1
        }

        // startXXX means something calculated from start to end
        val startDist = IntArray(bank.size)
        Arrays.fill(startDist, -1)
        val startQ: Queue<Int> = ArrayDeque()
        for (i in bank.indices) {
            if (canMutate(start, bank[i]!!)) {
                if (bank[i] == end) return 1
                startQ.add(i)
                startDist[i] = 1
            }
        }
        while (!startQ.isEmpty() && !endQ.isEmpty()) {
            val startIdx = startQ.poll()
            for (nextIdx in graph[startIdx!!]!!) {
                if (startDist[nextIdx] != -1) continue else if (endDist[nextIdx] >= 0) return startDist[startIdx] + endDist[nextIdx] + 1 else {
                    startDist[nextIdx] = startDist[startIdx] + 1
                    startQ.add(nextIdx)
                }
            }
            val endIdx = endQ.poll()
            for (nextIdx in graph[endIdx!!]!!) {
                if (endDist[nextIdx] != -1) continue else if (startDist[nextIdx] > 0) return startDist[nextIdx] + endDist[endIdx] + 1 else {
                    endDist[nextIdx] = endDist[endIdx] + 1
                    endQ.add(nextIdx)
                }
            }
        }
        return -1
    }

    private fun canMutate(gen1: String, gen2: String): Boolean {
        var diff = 0
        for (i in gen1.indices) diff += if (gen1[i] == gen2[i]) 0 else 1
        return diff == 1
    }
}