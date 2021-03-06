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
import model.ChessLogik;
import model.LinksControll;
import view.Main;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 28.07.2016.
 */
public class Mate implements Doer {
    private Controller controller = LinksControll.getControllerChess();
    private Button button = controller.getButtonLose();


    @Override
    public void doAction(ArrayList parameters) {
        controller.setEnd(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                button.setText("Закрыть");
                Stage stage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/xml/gameMate.fxml"));
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