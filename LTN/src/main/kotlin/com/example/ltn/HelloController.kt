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

    private val list: ArrayList<TextField> = ArrayList()
    @FXML private lateinit var textField: TextField

    /*
    @FXML
    fun initialize() {
        //textField = TextField().apply { setOnKeyPressed { onTextTyped(it) } }

        //box.children.add(textField)
    }
    */
    @FXML
    private fun onTextTyped(event: KeyEvent) {
        if (event.code != null)
            if (event.code == KeyCode.ENTER) {
                println("ENTER PRESSED !")
                if (textField.text.isNotEmpty()) {
                    val tmp = BarredText(textField.text).apply {
                        setOnMouseClicked { onMousePressed(it) }
                    }

                    textField.text = ""

                    box.children.add(tmp)
                }
            }
    }

    private fun onMousePressed(event: MouseEvent) {
        println("MousePressed ${event.button} on ${event.source}")

        when (event.button) {
            MouseButton.PRIMARY -> {
                val source = event.source as BarredText

                source.isBarred = !source.isBarred
            }
            MouseButton.SECONDARY -> {
                box.children.remove(event.source)
            }
            else -> println("Do noting: ${event.button}")
        }

    }
}