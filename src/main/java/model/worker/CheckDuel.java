package model.worker;

import control.Controller;
import control.ControllerCheck;
import control.ControllerLobby;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.LinksControll;
import view.Main;

import java.util.ArrayList;

/**
 * Created by User on 22.07.2016.
 */
public class CheckDuel implements Doer {
    private ControllerLobby controller = LinksControll.getControllerLobby();

    @Override
    public void doAction(ArrayList parameters){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ControllerCheck.setName((String) parameters.get(1));
                try{
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/xml/lobbyCheck.fxml"));
                    stage.setTitle("Hello World");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(controller.getOpWinsL().getScene().getWindow());
                    stage.show();
                }
                catch (Exception e){
                    Main.getLog().error(e.getMessage());
                }
            }
        });
    }

}
