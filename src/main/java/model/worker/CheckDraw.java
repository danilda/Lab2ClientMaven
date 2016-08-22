package model.worker;

import control.ControllerDraw;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.LinksControll;
import view.Main;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 28.07.2016.
 */
public class CheckDraw implements Doer {
    private Button button = ((ControllerDraw) LinksControll.getControllers()).getButtonCancelDraw();


    @Override
    public void doAction(ArrayList parameters) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                button.setText("Закрыть");
                Stage stage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/xml/gameDraw.fxml"));
                    stage.setTitle("Hello World");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(button.getScene().getWindow());
                    stage.show();
                } catch (IOException e) {
                    Main.getLog().error(e.getMessage());
                }
            }
        });
    }
}
