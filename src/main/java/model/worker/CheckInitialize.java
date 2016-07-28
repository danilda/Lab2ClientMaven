package model.worker;

import control.ControllerLobby;
import control.ControllerLogin;

import java.util.ArrayList;

/**
 * Created by User on 19.07.2016.
 */
public class CheckInitialize implements Doer {

    @Override
    public void doAction(ArrayList parameters){
        if(parameters.get(1).equals("true") && parameters.get(2).equals("true")){
            ControllerLogin.setCheck((byte)1);
            ControllerLobby.setMyName((String) parameters.get(3));
            ControllerLobby.setMyGame((String) parameters.get(4));
            ControllerLobby.setMyWins((String) parameters.get(5));
        } else {
            ControllerLogin.setCheck((byte)-1);
        }
    }
}
