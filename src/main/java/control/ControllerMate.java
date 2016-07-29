package control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Send;

/**
 * Created by User on 22.07.2016.
 */
public class ControllerMate {
    private static boolean invisibility;

    @FXML
    private Label changeLable;

    @FXML
    private Button buttonCancelMate;
    @FXML
    private void initialize() {
        if(!invisibility){
            changeLable.setVisible(false);
        }
    }

    public static void setInvisibility(boolean invisibility) {
        ControllerMate.invisibility = invisibility;
    }

    public void onCancelClick(){
        ((Stage) buttonCancelMate.getScene().getWindow()).close();
        ControllerLobby.setDialog(null);
    }
}
