package model.worker;

import control.ControllerLobby;
import control.ControllerLogin;

import java.util.ArrayList;

/**
 * Created by Клиент on 04.08.2016.
 */
public class UserInfo implements Doer {

        @Override
        public void doAction(ArrayList parameters){
            ControllerLobby.setMyName((String) parameters.get(1));
            ControllerLobby.setMyGame((String) parameters.get(2));
            ControllerLobby.setMyWins((String) parameters.get(3));
            ControllerLobby.setMyLoses((String) parameters.get(4));
        }
}
