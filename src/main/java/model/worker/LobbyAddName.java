package model.worker;

import control.ControllerLobby;

import java.util.ArrayList;

/**
 * Created by Клиент on 27.07.2016.
 */
public class LobbyAddName implements Doer {

    @Override
    public void doAction(ArrayList parameters) {
        ControllerLobby.lobbyAddName((String) parameters.get(1));
    }
}
