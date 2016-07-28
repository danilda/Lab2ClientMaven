package model.worker;

import control.ControllerLobby;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by User on 24.07.2016.
 */
public class Cancel implements Doer {
    @Override
    public void doAction(ArrayList parameters) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ((Stage) ControllerLobby.getDialog().getScene().getWindow()).close();
                ControllerLobby.setDialog(null);
            }
        });
    }
}
