package control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.LinksControll;
import model.Send;

/**
 * Created by User on 22.07.2016.
 */
public class ControllerMate implements Controllers{

    @FXML
    private Label changeLable;

    @FXML
    private Button buttonCancelMate;
    @FXML
    private void initialize() {
        LinksControll.setControllers(this);
    }


    public void onCancelClick(){
        ((Stage) buttonCancelMate.getScene().getWindow()).close();
    }

    @Override
    public boolean isLife() {
        return changeLable.getScene().getWindow().isShowing();
    }
    public void cancel(){
        onCancelClick();
    }
}
