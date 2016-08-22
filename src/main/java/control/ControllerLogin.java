package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import model.worker.CheckInitialize;
import view.Main;


import java.io.IOException;

public class ControllerLogin{

    @FXML
    private VBox vBox;

    @FXML
    private TextField textLogin;

    @FXML
    private TextField textPass;

    @FXML
    private Button button;

    private Send send = new Send();



    @FXML
    private void initialize() {
        LinksControll.setControllerLogin(this);
    }

    public void onClick(ActionEvent actionEvent) throws IOException {
        if(textLogin.getText() != null && !textLogin.getText().equals("")
                && textPass.getText() != null && !textPass.getText().equals(""))
            send.sendQueryAboutIntlz(textLogin.getText(), textPass.getText());

    }

    public void onClickDialog(ActionEvent actionEvent) throws Exception {

        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/xml/edit.fxml"));
            stage.setTitle("Hello World");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();
        }
        catch (Exception e){
            Main.getLog().error(e.getMessage());
        }

    }

    public void nextStage(){
        try {
            Stage stage = new Stage();
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/xml/lobby.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(0, "style.css");
            stage.setScene(scene);
            stage.setTitle("Крутые шахматы");
            stage.show();
            ((Stage) button.getScene().getWindow()).close();
        } catch (IOException e) {
            Main.getLog().error(e.getMessage());
        }

    }

    public void checkInit(boolean info){
        if(info){
            nextStage();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            Main.getLog().info("Ошибка");
            alert.setContentText("Неверный логин или пароль!");
            alert.showAndWait();
        }
    }

}
