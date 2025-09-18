package com.example.dpalgo;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    public HelloApplication() {
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Pane root = (Pane)fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        Image backgroundImage = new Image(this.getClass().getResourceAsStream("/com/example/proj1/img.png"));
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(-1.0, -1.0, false, false, true, false));
        Background background = new Background(new BackgroundImage[]{backgroundImg});
        root.setBackground(background);
        stage.show();
        TextArea textArea = new TextArea();
        textArea.setStyle("-fx-control-inner-background: navy; -fx-text-fill: white;");
    }

    public static void main(String[] args) {
        launch(new String[0]);
    }
}
