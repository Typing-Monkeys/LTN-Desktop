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

    /**
     * Binds the [MagicVBox.children] list with the given [bindList] list.
     *
     * @param bindList List with the children nodes.
     */
    fun bindContent(bindList: ObservableList<Node>) {
        Bindings.bindContentBidirectional(bindList, this.children)
    }

    /**
     * Choose the right bg for the node.
     * Used to set the bg for new added node.
     * For now set the bg for the even and odd children:
     *  - if children is in an Odd position: bg = white
     *  - else (means children in Even position): bg = lightgray
     *
     * @param index Index of the node in the list
     * @param node The new node
     */
    private fun initBg(index: Int, node: Node) {
        val elem = node as BarredText

        if (index%2 == 0)
            elem.bg = "lightgray"
        else
            elem.bg = "white"
    }

    /**
     * Update all the children bgs.
     * Used when a child is removed and bgs needs to be updated
     */
    fun updateBgs() {
        val nodes = this.children

        nodes.forEachIndexed { i, node ->
            initBg(i, node)
        }
    }
}