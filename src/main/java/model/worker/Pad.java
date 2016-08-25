package model.worker;

import control.Controller;
import control.ControllerMate;
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
 * Created by User on 02.08.2016.
 */
public class Pad implements Doer {
    private Controller controller = LinksControll.getControllerChess();
    private Button button = controller.getButtonLose();

    @Override
    public void doAction(ArrayList parameters) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                button.setText("Закрыть");
                controller.setEnd(true);
                Stage stage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/xml/gamePad.fxml"));
                    stage.setTitle("Hello World");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(button.getScene().getWindow());
                    stage.show();
                } catch (IOException e) {
                    Main.getLog().error(e);
                }
            }
        });
    }
}
