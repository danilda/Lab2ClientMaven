package model.worker;

import control.ControllerLobby;

import java.util.ArrayList;

/**
 * Created by Клиент on 20.07.2016.
 */
public class LobbyInitialize implements Doer {

    @Override
    public void doAction(ArrayList parameters) {
        ArrayList lobby = new ArrayList();
        for(int i = 1; i < parameters.size(); i++) {
            lobby.add(parameters.get(i));
        }
        System.out.println("Lobby: " + lobby.toString());
        ControllerLobby.setLobby(lobby);
    }
}
