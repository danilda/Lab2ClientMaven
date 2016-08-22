package control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.LinksControll;
import model.Send;

/**
 * Created by User on 22.07.2016.
 */
public class ControllerCheck implements Controllers {
    private ControllerLobby controllerLobby = (ControllerLobby) LinksControll.getControllerLobby();
    private static String name;
    private String color;
    private Send send = new Send();

    @FXML
    private Label opNameCheck;

    @FXML
    private void initialize() {
        LinksControll.setControllers(this);
        opNameCheck.setText(name);
        color = controllerLobby.getColor();
    }

    public void onCancelClick(){
        send.sendCancel();
        ((Stage) opNameCheck.getScene().getWindow()).close();

    }

    public void onSuccessClick(){
        send.sendSuccess(color);
        ((Stage) opNameCheck.getScene().getWindow()).close();
    }

    public static void setName(String name) {
        ControllerCheck.name = name;
    }


    @Override
    public boolean isLife() {
        return opNameCheck.getScene().getWindow().isShowing();
    }
    public void cancel(){
        ((Stage) opNameCheck.getScene().getWindow()).close();
    }
}
