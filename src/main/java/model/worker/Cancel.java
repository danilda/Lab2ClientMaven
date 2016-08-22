package model.worker;

import control.ControllerLobby;
import control.ControllerWait;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.LinksControll;

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
                ((Stage)((ControllerWait) LinksControll.getControllers())
                        .getButtonCancelWait().getScene().getWindow()).close();
                LinksControll.setControllers(null);
            }
        });
    }
}
