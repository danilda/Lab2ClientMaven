package model.worker;

import control.ControllerLobby;
import control.ControllerLogin;
import javafx.application.Platform;

import java.util.ArrayList;

/**
 * Created by User on 19.07.2016.
 */
public class CheckInitialize implements Doer {
    private static ControllerLogin controller;

    public static void setController(ControllerLogin controller) {
        CheckInitialize.controller = controller;
    }

    @Override
    public void doAction(ArrayList parameters){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (parameters.get(1).equals("true")) {
                    controller.checkInit(true);
                } else {
                    controller.checkInit(false);
                }
            }
        });
    }
}
