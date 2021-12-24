package com.example.ltn

import javafx.scene.layout.StackPane
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.scene.shape.Line

class BarredText (private var text:String): StackPane() {
    private val label = Label(text)
    private val panel = Pane()
    private val line = Line()

    init {
        panel.styleClass.add("pane")

        line.strokeWidth = 1.5

        line.startXProperty().bind(label.layoutXProperty())
        line.startYProperty().bind(label.layoutYProperty().add(label.heightProperty().multiply(.54)))
        line.endXProperty().bind(label.layoutXProperty().add(label.widthProperty()))
        line.endYProperty().bind(label.layoutYProperty().add(label.heightProperty().multiply(.54)))

        panel.children.addAll(line)

        super.getChildren().addAll(panel, label)
    }
}