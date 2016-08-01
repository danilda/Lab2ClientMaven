package model.worker;

import control.Controller;
import javafx.application.Platform;

import java.util.ArrayList;

/**
 * Created by User on 01.08.2016.
 */
public class Message implements Doer {
    private static Controller controller;

    public static void setController(Controller controller) {
        Message.controller = controller;
    }

    @Override
    public void doAction(ArrayList parameters) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controller.refreshMessages((String) parameters.get(1), (String) parameters.get(2));
            }
        });
    }
}
