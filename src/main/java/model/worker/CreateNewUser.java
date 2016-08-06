package model.worker;

import control.ControllerEdit;
import control.ControllerLogin;
import javafx.application.Platform;
import model.worker.Doer;

import java.util.ArrayList;

/**
 * Created by User on 19.07.2016.
 */
public class CreateNewUser implements Doer {
    private static ControllerEdit controller;

    public static void setController(ControllerEdit controller) {
        CreateNewUser.controller = controller;
    }

    @Override
    public void doAction(ArrayList parameters) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (parameters.get(1).equals("false")) {
                    controller.checkCreate(true);
                } else {
                    controller.checkCreate(false);
                }
            }
        });
    }
}
