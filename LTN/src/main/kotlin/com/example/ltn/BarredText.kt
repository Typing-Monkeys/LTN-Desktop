package com.example.ltn

import javafx.scene.layout.StackPane
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.scene.shape.Line

class BarredText (text:String): StackPane() {
    private val label = Label(text)
    private val panel = Pane()
    private val line = Line()

    // if TRUE, a line appears on the text
    var isBarred: Boolean
        get() = line.isVisible
        set(value) {
            line.isVisible = value
        }

    // handle label text prop
    var text: String
        get() = label.text
        set(value) {
            label.text = value
        }

    companion object companion {
        var conta = 0

        fun new() {
            this.conta ++
        }

        fun isOdd(): String {
            return if (conta%2 == 0)
                "pane_odd"
            else
                "pane_even"
        }
    }

    init {
        companion.new()

        panel.styleClass.add(companion.isOdd())    // set CSS class

        line.strokeWidth = 1.5  // set line stroke

        // binds the line to the label
        line.startXProperty().bind(label.layoutXProperty())
        line.startYProperty().bind(label.layoutYProperty().add(label.heightProperty().multiply(.54)))
        line.endXProperty().bind(label.layoutXProperty().add(label.widthProperty()))
        line.endYProperty().bind(label.layoutYProperty().add(label.heightProperty().multiply(.54)))

        // on spawn text is not barred
        this.isBarred = false

        // add line to the panel
        panel.children.addAll(line)

        // add panel and label to the StackPane
        super.getChildren().addAll(panel, label)
    }

}