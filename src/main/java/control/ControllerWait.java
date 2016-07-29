package control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Send;
import model.worker.CancelDraw;
import model.worker.SuccessDraw;

/**
 * Created by User on 22.07.2016.
 */
public class ControllerWait {
    private static String currentOponent;
    @FXML
    private Button buttonCancelWait;

    @FXML
    private void initialize() {
        ControllerLobby.setDialog(buttonCancelWait);
        CancelDraw.setButton(buttonCancelWait);
        SuccessDraw.setButton(buttonCancelWait);
    }

    public void onCancelClick(){
        Send.sendCancelDraw();
        ((Stage) buttonCancelWait.getScene().getWindow()).close();
        ControllerLobby.setDialog(null);
    }

    public String getCurrentOponent() {
        return currentOponent;
    }

    public void setCurrentOponent(String currentOponent) {
        this.currentOponent = currentOponent;
    }
}
