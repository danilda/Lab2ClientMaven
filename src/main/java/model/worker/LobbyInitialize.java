package model.worker;

import control.ControllerLobby;
import view.Main;

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
        Main.getLog().info("Lobby: " + lobby.toString());
        ControllerLobby.setLobby(lobby);
    }
}
