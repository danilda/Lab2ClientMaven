package model.worker;

import control.Controller;
import control.ControllerLobby;
import control.ControllerMate;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Send;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 29.07.2016.
 */
public class SuccessDraw implements Doer {
    private static Button buttonDialog;
    private static Button buttonControll;

    public static void setButton(Button button) {
        SuccessDraw.buttonDialog = button;
    }

    public static void setButtonControll(Button buttonControll) {
        SuccessDraw.buttonControll = buttonControll;
    }

    @Override
    public void doAction(ArrayList parameters) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ((Stage) buttonDialog.getScene().getWindow()).close();
                buttonControll.setText("Закрыть");
                Controller.setEnd(true);
                Stage stage = new Stage();
                Parent root = null;
                try {
                    ControllerMate.setInvisibility(true);
                    root = FXMLLoader.load(getClass().getResource("/xml/gameDrawSuccess.fxml"));
                    stage.setTitle("Hello World");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(buttonControll.getScene().getWindow());
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
