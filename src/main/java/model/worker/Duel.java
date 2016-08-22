package model.worker;

import control.Controller;
import control.ControllerLobby;
import control.Controllers;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.LinksControll;
import view.Main;

import java.io.IOException;
import java.util.ArrayList;

import static java.util.Objects.isNull;

/**
 * Created by User on 21.07.2016.
 */
public class Duel implements Doer {
    private Controllers controllers = LinksControll.getControllers();
    private Label label = LinksControll.getControllerLobby().getOpWinsL();

    @Override
    public void doAction(ArrayList parameters) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                Main.getLog().info(parameters.get(1));
                Controller.setWhite( Boolean.parseBoolean((String) parameters.get(1)));


                if(!isNull(controllers))
                    if(controllers.isLife())
                        controllers.cancel();
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
