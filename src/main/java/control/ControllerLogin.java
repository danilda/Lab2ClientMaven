package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;


import java.io.IOException;

public class ControllerLogin{



    @FXML
    VBox vBox;

    @FXML
    TextField textLogin;

    @FXML
    TextField textPass;

    @FXML
    Button button;

    private static byte check;


    @FXML
    private void initialize() {
        check = 0;
    }


    /**
     * Now we don't say about protocol.
     * That is why "Send.send...()" is commented.
     * When we speak about this, i change this method.
     * Actually I will commend source between "start" and "end".
     * And discommend "Send.send...()"
     * @throws IOException
     */
    public void onClick(ActionEvent actionEvent) throws IOException {
        Send.sendQueryAboutIntlz(textLogin.getText(), textPass.getText());
        while (true){
            System.out.println("Check = " + check);
            if(ControllerLogin.check == 1){
                System.out.println("Check = " + check);
                nextStage();
                break;
            }
            if(ControllerLogin.check == -1){
                // в случае плохого входа
                break;
            }
        }
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
            throw e;
        }

    }

    public void nextStage(){
        System.out.println("какае-то имба");
        Stage stageTheLabelBelongs = (Stage) button.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/xml/lobby.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stageTheLabelBelongs.setScene(new Scene(root));
    }
    public static byte getCheck() {
        return check;
    }

    public static void setCheck(byte check) {
        ControllerLogin.check = check;
    }

}
