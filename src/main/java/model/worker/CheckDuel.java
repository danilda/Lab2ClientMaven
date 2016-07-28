package model.worker;

import control.ControllerCheck;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by User on 22.07.2016.
 */
public class CheckDuel implements Doer {
    private static Label label;

    public static void setLabel(Label label) {
        CheckDuel.label = label;
    }

    @Override
    public void doAction(ArrayList parameters){
        ControllerCheck.setName((String) parameters.get(1));

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try{
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/xml/lobbyCheck.fxml"));
                    stage.setTitle("Hello World");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(label.getScene().getWindow());
                    stage.show();
                }
                catch (Exception e){
                    e.getStackTrace();
                }
            }
        });
    }

}
