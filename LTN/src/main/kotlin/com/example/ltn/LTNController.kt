package com.example.ltn

import javafx.beans.binding.Bindings
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.VBox

class LTNController {
    @FXML private lateinit var box:VBox
    @FXML private lateinit var textField: TextField
    @FXML private lateinit var scroll: MagicScrollPanel

    private val boxContent: ObservableList<Node> = FXCollections.observableArrayList<Node>()

    @FXML
    fun initialize() {
        Bindings.bindContentBidirectional(boxContent, box.children)     // binds the list to VBox

    }

    @FXML
    private fun onTextTyped(event: KeyEvent) {
        if (event.code != null) // check for safe type
            if (event.code == KeyCode.ENTER) {  // check for ENTER pressed
                if (textField.text.isNotEmpty()) {  // check if textbox is not empty
                    // creating new elem
                    val tmp = BarredText(textField.text).apply {
                        setOnMouseClicked { onMousePressed(it) }
                    }

                    textField.text = ""     // removing current text from textbox

                    boxContent.add(tmp)     // add element to VBox
                    scroll.scrollDown()     // scroll down to the bottom of the scrollpane

                    println("Added ${tmp.text} as $tmp")
                }
            }
    }

    private fun onMousePressed(event: MouseEvent) {
        val mouseButton = event.button

        println("MousePressed $mouseButton on ${event.source}")

        when (mouseButton) {
            MouseButton.PRIMARY -> {    // toggle text barr
                val source      = event.source as BarredText
                source.isBarred = !source.isBarred

                println("\tSett isBarred to ${source.isBarred} on $source")
            }
            MouseButton.SECONDARY -> {  // removing elem
                boxContent.remove(event.source)

                BarredText.recolor(boxContent as List<BarredText>)

                println("\tRemoved ${event.source}")
            }
            else -> println("\tDo noting: ${event.button}")
        }

    }
}