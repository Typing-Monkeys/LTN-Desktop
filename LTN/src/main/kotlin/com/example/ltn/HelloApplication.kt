package com.example.ltn

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("hello-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 420.0, 540.0)
        stage.title = "Let Them Note"
        stage.scene = scene
        //stage.isAlwaysOnTop = true
        stage.show()
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}