package control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Send;

/**
 * Created by User on 22.07.2016.
 */
public class ControllerMate {
    @FXML
    private Button buttonCancelMate;

    public void onCancelClick(){
        ((Stage) buttonCancelMate.getScene().getWindow()).close();
        ControllerLobby.setDialog(null);
    }
}
