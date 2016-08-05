package model.worker;

import control.Controller;
import control.ControllerLobby;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 21.07.2016.
 */
public class Duel implements Doer {
    private static Label label;

    public static Label getLabel() {
        return label;
    }

    public static void setLabel(Label label) {
        Duel.label = label;
    }

    @Override
    public void doAction(ArrayList parameters) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                System.out.println(parameters.get(1));
                ControllerLobby.getThread().interrupt();
                Controller.setWhite( Boolean.parseBoolean((String) parameters.get(1)));
                Controller.setNowStep( Boolean.parseBoolean((String) parameters.get(1)));
                if(Boolean.parseBoolean((String) parameters.get(1))){
                    Controller.setNowStep(true);
                } else {
                    Controller.setNowStep(false);
                }
                if(ControllerLobby.getDialog()!=null)
                    ((Stage) ControllerLobby.getDialog().getScene().getWindow()).close();
                try {
                    Stage stage = new Stage();
                    Parent root = null;
                    root = FXMLLoader.load(getClass().getResource("/xml/sample.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(0, "style.css");
                    stage.setScene(scene);
                    stage.setTitle("Крутые шахматы");
                    Stage stageTheLabelBelongs = (Stage) label.getScene().getWindow();
                    stageTheLabelBelongs.close();
                    stage.show();

                } catch (Exception e){

                }

            }
        });
    }
}
