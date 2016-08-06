package model.worker;

import control.Controller;
import control.ControllerLobby;
import javafx.application.Platform;

import java.util.ArrayList;

/**
 * Created by User on 21.07.2016.
 */
public class QueryUser implements Doer {
    private static ControllerLobby controller;

    public static void setController(ControllerLobby controller) {
        QueryUser.controller = controller;
    }

    @Override
    public void doAction(ArrayList parameters) {
        System.out.println(parameters.toString());
        String name = (String)parameters.get(1);
        String game = (String)parameters.get(2);
        String wins = (String)parameters.get(3);
        String loses = (String)parameters.get(4);
        String status = (String)parameters.get(5);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controller.onRefreshList(name, game, wins, loses, status);
            }
        });
    }

}
