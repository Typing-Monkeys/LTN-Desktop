package com.example.ui

import javafx.beans.binding.Bindings
import javafx.collections.ListChangeListener
import javafx.collections.ObservableList
import javafx.scene.Node
import javafx.scene.layout.VBox

class MagicVBox: VBox() {

    init {
        // when a new elem is added, choose the right bg
        this.children.addListener { c: ListChangeListener.Change<out Node> ->
            while (c.next()) {
                if (c.wasAdded()) {
                    // set initial node bg
                    val index = c.from
                    val node = c.list[index]

                    initBg(index, node)
                }
            }
        }
    }

    fun bindContent(bindList: ObservableList<Node>) {
        Bindings.bindContentBidirectional(bindList, this.children)
    }

    private fun initBg(index: Int, node: Node) {
        val elem = node as BarredText

        if (index%2 == 0)
            elem.bg = "lightgray"
        else
            elem.bg = "white"
    }

    fun updateBgs() {
        val nodes = this.children

        nodes.forEachIndexed { i, node ->
            initBg(i, node)
        }
    }
}