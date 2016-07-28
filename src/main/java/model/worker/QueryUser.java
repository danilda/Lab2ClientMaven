package model.worker;

import control.ControllerLobby;

import java.util.ArrayList;

/**
 * Created by User on 21.07.2016.
 */
public class QueryUser implements Doer {

    @Override
    public void doAction(ArrayList parameters) {
        System.out.println(parameters.toString());
        ControllerLobby.setOpName((String)parameters.get(1));
        ControllerLobby.setOpGame((String)parameters.get(2));
        ControllerLobby.setOpWins((String)parameters.get(3));
        ControllerLobby.setOpStatus((String)parameters.get(4));
        ControllerLobby.setSwitcher(true);
    }

}
