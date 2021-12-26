package com.example.ui

import javafx.application.Platform
import javafx.beans.value.ChangeListener
import javafx.scene.control.ScrollPane

/**
 * A Magic ScrollPane.
 *
 * This ScrollPane has an adjustable scroll speed and is able to auto-scrolldown the to bottom of the content
 */
class MagicScrollPanel: ScrollPane() {
    private var scrollToBottom = false  // if true, triggers the scroll donw function in the listener
    private val SPEED_MULT = 6          // multiplier for fast scrollspeed

    init {
        // when vvalue is changed check if scroll down
        this.vvalueProperty().addListener(ChangeListener { _, _, _ ->
            if (scrollToBottom) {
                this.vvalue = this.vmax
                scrollToBottom = false
            }

        })

        // scrollpane vertical scroll speed
        Platform.runLater {     // runLater is kind a magic, it solves the NullPointer error !!
            this.content.setOnScroll {
                // doing some scroll math
                val deltaY: Double = it.deltaY * SPEED_MULT
                val width: Double = this.content.boundsInLocal.width
                val vvalue: Double = this.vvalue

                this.vvalue = vvalue + -deltaY / width
            }
        }
    }

    /**
     * Scroll down to the bottom of the ScrollPane content.
     */
    fun scrollDown() {
        this.vvalue = this.vmax     // scroll down to the last element
        scrollToBottom = true       // trigger the autoscroll in the vvalue listener
    }
}