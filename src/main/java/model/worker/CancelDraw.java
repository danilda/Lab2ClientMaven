package model.worker;

import control.ControllerLobby;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by User on 29.07.2016.
 */
public class CancelDraw implements Doer {
    private static Button button;

    public static void setButton(Button button) {
        CancelDraw.button = button;
    }

    @Override
    public void doAction(ArrayList parameters) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ((Stage) button.getScene().getWindow()).close();
            }
        });
    }
}
