package model.worker;

import control.ControllerLobby;

import java.util.ArrayList;

/**
 * Created by Клиент on 27.07.2016.
 */
public class LobbyRemoveName implements Doer {
    @Override
    public void doAction(ArrayList parameters) {
        ControllerLobby.lobbyRemoveName((String) parameters.get(1));
    }
}
