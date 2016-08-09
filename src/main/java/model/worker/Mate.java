package model.worker;

import control.Controller;
import control.ControllerMate;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ChessLogik;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 28.07.2016.
 */
public class Mate implements Doer {
    private static Button button;

    public static void setButton(Button button) {
        Mate.button = button;
    }

    @Override
    public void doAction(ArrayList parameters) {
        Controller.setEnd(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                button.setText("Закрыть");
                Stage stage = new Stage();
                Parent root = null;
                try {
                    ControllerMate.setInvisibility(true);
                    root = FXMLLoader.load(getClass().getResource("/xml/gameMate.fxml"));
                    stage.setTitle("Hello World");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(button.getScene().getWindow());
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}