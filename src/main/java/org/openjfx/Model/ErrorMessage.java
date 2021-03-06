package org.openjfx.Model;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.openjfx.MainApp;

import java.io.IOException;

public class ErrorMessage {


    //Lager et GUI popupvindu med feilmelding, som den får som input i konstruktøren (fra Throw new EmptyTableException)
    //GUI kan KUN lukkes med knapp, ikke alt + f4 eller lignende, den kan heller ikke resizes, flyttes på, legges ned.
    //Når denne popupen er åpen, disables den andre, fordi den ikke skal kunne benyttes før man trykker OK.
    //I tillegg er popupen MODAL, som tvinger oss til å bruke den, før hovedscenet kan brukes igjen.
    public ErrorMessage(String exception) throws IOException {
        MainApp.primaryScene.getRoot().setDisable(true);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOnCloseRequest(windowEvent -> windowEvent.consume());
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);
        Button button = new Button("ok");
        button.setTranslateX(100);
        button.setTranslateY(70);
        button.setPrefSize(100,50);
        Label label = new Label(exception);
        label.setTranslateX(120);

        HBox hBox = new HBox(button);
        HBox spacing = new HBox();
        spacing.setPrefSize(50,50);
        HBox hBox1 = new HBox(label);
        hBox1.setTranslateY(50);
        hBox1.setTranslateX(-60);
        VBox root = new VBox(hBox1, hBox);


        Scene scene = new Scene(root, 300, 200);
        scene.fillProperty().set(Color.BLACK);

        stage.setScene(scene);
        stage.show();
        button.setOnAction(event -> {
            //System.exit(0);
            stage.close();
            MainApp.primaryScene.getRoot().setDisable(false);
        });
    }




}
