package model;

import model.worker.Doer;

import java.util.ArrayList;

/**
 * Created by User on 19.07.2016.
 */
public class Parser{

    public static void callDoer(ArrayList str){

        Doer doer = null;
        Class classe;

        try {
            classe = Class.forName("model.worker." + str.get(0));
            try {
                doer = (Doer)classe.newInstance();
            } catch (InstantiationException e) {

            } catch (IllegalAccessException e) {

            }

        } catch (ClassNotFoundException e) {

        }
        if(doer != null){
            doer.doAction(str);
        }
    }

}
