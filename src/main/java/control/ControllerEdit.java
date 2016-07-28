package control;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Send;

public class ControllerEdit {

    @FXML
    TextField regLogin;

    @FXML
    TextField regPass;

    @FXML
    TextField regPassCheck;

    private static byte check;

    @FXML
    private void initialize() {
        check = 0;
    }

    public void endRegistration(ActionEvent actionEvent){
        if(regPass.getText().equals(regPassCheck.getText())){
            Send.sendQueryRegistration(regLogin.getText(), regPass.getText());
        }

        while (true){
            System.out.println(check);
            if(check == 1){
                ((Stage) regPassCheck.getScene().getWindow()).close();
                break;
            }
            if(check == -1){
                // в случае плохого рега
                break;
            }
        }
    }

    public static byte getCheck() {
        return check;
    }

    public static void setCheck(byte check) {
        ControllerEdit.check = check;
    }
}
