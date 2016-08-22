package control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.LinksControll;
import model.Send;
import model.worker.CancelDraw;
import model.worker.SuccessDraw;

/**
 * Created by User on 22.07.2016.
 */
public class ControllerWait implements Controllers {
    private Send send = new Send();
    @FXML
    private Button buttonCancelWait;

    @FXML
    private void initialize() {
        LinksControll.setControllers(this);
    }

    public void onCancelClick(){
        send.sendCancelDraw();
        ((Stage) buttonCancelWait.getScene().getWindow()).close();
    }

    public Button getButtonCancelWait() {
        return buttonCancelWait;
    }

    @Override
    public boolean isLife() {
        return buttonCancelWait.getScene().getWindow().isShowing();
    }
    public void cancel(){
        ((Stage) buttonCancelWait.getScene().getWindow()).close();
    }
}
