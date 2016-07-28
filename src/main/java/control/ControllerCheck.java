package control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Send;

/**
 * Created by User on 22.07.2016.
 */
public class ControllerCheck {
    private static String name;
    private static String color;

    @FXML
    private Label opNameCheck;

    @FXML
    private void initialize() {
        ControllerLobby.setDialog(opNameCheck);
        opNameCheck.setText(name);
        color = "random";
    }

    public void onCancelClick(){
        Send.sendCancel();
        ((Stage) opNameCheck.getScene().getWindow()).close();
        ControllerLobby.setDialog(null);
    }

    public void onSuccessClick(){
        Send.sendSuccess(color);
        ((Stage) opNameCheck.getScene().getWindow()).close();
        ControllerLobby.setDialog(null);
    }


    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        ControllerCheck.name = name;
    }

    public static String getColor() {
        return color;
    }

    public static void setColor(String color) {
        ControllerCheck.color = color;
    }
}
