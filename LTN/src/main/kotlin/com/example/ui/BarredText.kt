package com.example.ui

import javafx.collections.ObservableList
import javafx.scene.Node
import javafx.scene.layout.StackPane
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.scene.shape.Line

/**
 * A text with barred option that is not a Text !
 *
 * This class simulate a text with 'line-through' decoration (a line over the text).
 *
 * @param text the text to show
 */
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

    // set the bg color
    var bg: String = ""
        set(value) {
            field = value
            this.style = "-fx-background-color: ${value};"
        }


    companion object Colorator {
        private var conta = 0
        //const val odd = "pane_odd"
        //const val even = "pane_even"

        private fun initBg(): String {
            conta += 1

            return if (conta %2 == 0)
                "white"
            else
                "lightgray"
        }

        fun recolor(list: ObservableList<Node>) {
            conta = 0

            for (elem in list) {
                (elem as BarredText).bg = initBg()
            }
        }
    }

    init {
        bg = initBg()

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