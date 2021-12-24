package com.example.ltn

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.VBox


class HelloController {
    @FXML private lateinit var box:VBox
    @FXML private lateinit var textField: TextField
    //private val list: ArrayList<TextField> = ArrayList()

    /*
    @FXML
    fun initialize() {
        //textField = TextField().apply { setOnKeyPressed { onTextTyped(it) } }

        //box.children.add(textField)
    }
    */
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

                    box.children.add(tmp)   // add elem to VBox

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
                box.children.remove(event.source)

                println("\tRemoved ${event.source}")
            }
            else -> println("\tDo noting: ${event.button}")
        }

    }
}