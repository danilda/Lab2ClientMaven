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

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 02.08.2016.
 */
public class Pad implements Doer {
    private static Button button;

    public static void setButton(Button button) {
        Pad.button = button;
    }

    @Override
    public void doAction(ArrayList parameters) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                button.setText("Закрыть");
                Controller.setEnd(true);
                Stage stage = new Stage();
                Parent root = null;
                try {
                    ControllerMate.setInvisibility(true);
                    root = FXMLLoader.load(getClass().getResource("/xml/gamePad.fxml"));
                    stage.setTitle("Hello World");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(button.getScene().getWindow());
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ControllerMate.setInvisibility(true);
            }
        });
    }
}
