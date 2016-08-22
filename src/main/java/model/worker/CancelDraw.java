package model.worker;

import control.ControllerDraw;
import control.ControllerLobby;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.LinksControll;

import java.util.ArrayList;

/**
 * Created by User on 29.07.2016.
 */
public class CancelDraw implements Doer {

    @Override
    public void doAction(ArrayList parameters) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ((Stage) (((ControllerDraw) LinksControll.getControllers()).getButtonCancelDraw())
                        .getScene().getWindow()).close();
                LinksControll.setControllers(null);
            }
        });
    }
}
