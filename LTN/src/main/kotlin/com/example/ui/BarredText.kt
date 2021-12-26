package com.example.ui

import javafx.scene.layout.StackPane
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.scene.shape.Line

class BarredText (text:String): StackPane() {
    private val label = Label(text)
    private val panel = Pane()
    private val line = Line()
    private var _bg = ""

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

    var bg: String
        get() = _bg
        set(value) {
            _bg = value
            when (value) {
                odd -> {
                    panel.styleClass.remove(even)
                    panel.styleClass.add(odd)
                }
                even -> {
                    panel.styleClass.remove(odd)
                    panel.styleClass.add(even)
                }
                else -> {
                    panel.styleClass.add(value)
                }
            }


        }


    companion object Colorator {
        private var conta = 0
        const val odd = "pane_odd"
        const val even = "pane_even"

        private fun initBg(): String {
            conta += 1

            return if (conta %2 == 0)
                even
            else
                odd
        }

        fun recolor(list: List<BarredText>) {
            conta = 0

            for (elem in list) {
                elem.bg = initBg()
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