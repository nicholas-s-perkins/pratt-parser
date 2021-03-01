package com.nsperkins.parser


import java.util.*

/**
 * Console tree printer from stack overflow https://stackoverflow.com/a/4973083
 * Could probably clean this up a bit more
 */
object BTreePrinter {
    fun printNode(root: AstNode?): String {
        val maxLevel = maxLevel(root)
        val collector = StringBuilder()

        printNodeRec(collector, listOf(root), 1, maxLevel)

        return collector.toString()
    }

    private fun printNodeRec(collector: StringBuilder, nodes: List<AstNode?>, level: Int, maxLevel: Int) {
        if (nodes.isEmpty() || isAllElementsNull(nodes)) return

        val floor = maxLevel - level
        val endgeLines = Math.pow(2.0, Math.max(floor - 1, 0).toDouble()).toInt()
        val firstSpaces = Math.pow(2.0, floor.toDouble()).toInt() - 1
        val betweenSpaces = Math.pow(2.0, (floor + 1).toDouble()).toInt() - 1
        printWhitespaces(collector, firstSpaces)

        val newNodes: MutableList<AstNode?> = ArrayList()

        for (node in nodes) {
            if (node != null) {
                collector.append(node.token)
                newNodes.add(node.left)
                newNodes.add(node.right)
            } else {
                newNodes.add(null)
                newNodes.add(null)
                collector.append(" ");
            }
            printWhitespaces(collector, betweenSpaces)
        }

        collector.append("\n")

        for (i in 1..endgeLines) {
            for (j in nodes.indices) {
                printWhitespaces(collector, firstSpaces - i)
                if (nodes[j] == null) {
                    printWhitespaces(collector, endgeLines + endgeLines + i + 1)
                    continue
                }
                if (nodes[j]!!.left != null) collector.append("/") else printWhitespaces(collector, 1)
                printWhitespaces(collector, i + i - 1)
                if (nodes[j]!!.right != null) collector.append("\\") else printWhitespaces(collector,1)
                printWhitespaces(collector, endgeLines + endgeLines - i)
            }
            collector.append("\n")
        }

        printNodeRec(collector, newNodes, level + 1, maxLevel)
    }

    private fun printWhitespaces(collector: StringBuilder, count: Int) {
        for (i in 0 until count) collector.append(" ")
    }

    private fun maxLevel(node: AstNode?): Int {
        return if (node == null) 0 else Math.max(
            maxLevel(node.left),
            maxLevel(node.right)
        ) + 1
    }

    private fun <T> isAllElementsNull(list: List<T>): Boolean {
        for (`object` in list) {
            if (`object` != null) return false
        }
        return true
    }
}
