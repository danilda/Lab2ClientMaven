package control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.LinksControll;
import model.Send;
import model.worker.CancelDraw;

import java.io.IOException;

/**
 * Created by User on 22.07.2016.
 */
public class ControllerDraw implements Controllers{
    @FXML
    private Button buttonCancelDraw;
    private Send send = new Send();

    public Button getButtonCancelDraw() {
        return buttonCancelDraw;
    }

    public void setButtonCancelDraw(Button buttonCancelDraw) {
        this.buttonCancelDraw = buttonCancelDraw;
    }

    @FXML
    private void initialize() {
        LinksControll.setControllers(this);
    }

    public void onCancelClick(){
        send.sendCancelDraw();
        ((Stage) buttonCancelDraw.getScene().getWindow()).close();
    }

    public void onSuccessClick(){
        send.sendSuccessDraw();
        ((Stage) buttonCancelDraw.getScene().getWindow()).close();
        Stage stageTheLabelBelongs = (Stage) buttonCancelDraw.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/xml/lobby.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stageTheLabelBelongs.setScene(new Scene(root));
    }

    @Override
    public boolean isLife() {
        return buttonCancelDraw.getScene().getWindow().isShowing();
    }
    public void cancel(){
        ((Stage) buttonCancelDraw.getScene().getWindow()).close();
    }
}
