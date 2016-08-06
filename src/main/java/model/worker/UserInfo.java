package model.worker;

import control.ControllerLobby;
import control.ControllerLogin;
import javafx.application.Platform;

import java.util.ArrayList;

/**
 * Created by Клиент on 04.08.2016.
 */
public class UserInfo implements Doer {
    private static ControllerLobby controllerLobby;

    public static void setControllerLobby(ControllerLobby controllerLobby) {
        UserInfo.controllerLobby = controllerLobby;
    }

    @Override
    public void doAction(ArrayList parameters){
        System.out.println(parameters.toString());
        String name = (String)parameters.get(1);
        String game = (String)parameters.get(2);
        String wins = (String)parameters.get(3);
        String loses = (String)parameters.get(4);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controllerLobby.onRefreshMyInfo(name, game, wins, loses);
            }
        });
    }
}
