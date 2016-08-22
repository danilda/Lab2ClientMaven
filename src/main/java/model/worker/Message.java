package model.worker;

import control.Chat;
import control.Controller;
import javafx.application.Platform;
import model.LinksControll;

import java.util.ArrayList;

/**
 * Created by User on 01.08.2016.
 */
public class Message implements Doer {
    private Chat controller;


    @Override
    public void doAction(ArrayList parameters) {
        if(LinksControll.getControllerChess().isLife()){
            controller = LinksControll.getControllerChess();
        } else {
            controller = LinksControll.getControllerLobby();
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controller.refreshMessages(parameters);
            }
        });
    }
}
