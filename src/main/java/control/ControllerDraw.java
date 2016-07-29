package control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Send;
import model.worker.CancelDraw;

import java.io.IOException;

/**
 * Created by User on 22.07.2016.
 */
public class ControllerDraw {
    @FXML
    private Button buttonCancelDraw;

    private static Button buttonControll;

    public static void setButtonControll(Button buttonControll) {
        ControllerDraw.buttonControll = buttonControll;
    }

    @FXML
    private void initialize() {
        CancelDraw.setButton(buttonCancelDraw);
    }

    public void onCancelClick(){
        Send.sendCancelDraw();
        ((Stage) buttonCancelDraw.getScene().getWindow()).close();
    }

    public void onSuccessClick(){
        Send.sendSuccessDraw();
        ((Stage) buttonCancelDraw.getScene().getWindow()).close();
        Stage stageTheLabelBelongs = (Stage) buttonControll.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/xml/lobby.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stageTheLabelBelongs.setScene(new Scene(root));

    }
}
