package model.worker;

import control.ControllerEdit;
import model.worker.Doer;

import java.util.ArrayList;

/**
 * Created by User on 19.07.2016.
 */
public class CreateNewUser implements Doer {

    @Override
    public void doAction(ArrayList parameters) {
        if(parameters.get(1).equals("false")){
            ControllerEdit.setCheck((byte)1);
        } else {
            ControllerEdit.setCheck((byte)-1);
        }
    }
}
