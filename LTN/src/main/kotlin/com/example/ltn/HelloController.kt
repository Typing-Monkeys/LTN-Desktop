package com.example.ltn

import javafx.css.PseudoClass
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.shape.Line


class HelloController {
    @FXML private lateinit var welcomeText: Label
    @FXML private lateinit var box:VBox

    private val list: ArrayList<TextField> = ArrayList()
    private lateinit var textField: TextField

    @FXML
    fun initialize() {
        textField = TextField().apply { setOnKeyPressed { onTextTyped(it) } }

        box.children.add(textField)

        var label = Label("Io sono una scritta lunga")

        //box.children.add(label)

        val pane = Pane()
        pane.styleClass.add("pane")
        val blueLine = Line()
        blueLine.strokeWidth = 1.5
        blueLine.startXProperty().bind(label.layoutXProperty())
        blueLine.startYProperty().bind(label.layoutYProperty().add(label.heightProperty().multiply(.6)))
        blueLine.endXProperty().bind(label.layoutXProperty().add(label.widthProperty()))
        blueLine.endYProperty().bind(label.layoutYProperty().add(label.heightProperty().multiply(.6)))


        pane.children.addAll(blueLine)

        val aaa = StackPane(pane, label)


        box.children.add(aaa)

        //aaa.children[0].isVisible = false
    }


    fun onTextTyped(event: KeyEvent) {
        if (event.code != null)
            if (event.code == KeyCode.ENTER) {
                println("ENTER PRESSED !")
                if (textField.text.isNotEmpty()) {
                    val tmp = Label(textField.text).apply {
                        setOnMouseClicked { onMousePressed(it) }
                    }

                    textField.text = ""

                    box.children.remove(textField)
                    box.children.add(tmp)
                    box.children.add(textField)
                }
            }
    }

    fun onMousePressed(event: MouseEvent) {
        println("MousePressed $event")
        val source = event.source as Label

        val withdrawn = PseudoClass.getPseudoClass("withdrawn")
        source.pseudoClassStateChanged(withdrawn, true);

    }
}