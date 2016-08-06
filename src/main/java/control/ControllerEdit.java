package control;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Send;
import model.worker.CreateNewUser;

public class ControllerEdit {

    @FXML
    TextField regLogin;

    @FXML
    TextField regPass;

    @FXML
    TextField regPassCheck;


    @FXML
    private void initialize() {
        CreateNewUser.setController(this);
    }

    public void endRegistration(ActionEvent actionEvent){
        if(!regLogin.getText().equals("") && !regPass.getText().equals("")
                && regPass.getText().equals(regPassCheck.getText())){
            Send.sendQueryRegistration(regLogin.getText(), regPass.getText());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            System.out.println("Ошибка");
            alert.setContentText("Ошибка ввода полей.");
            alert.showAndWait();
        }
    }

    public void checkCreate(boolean info){
        if(info){
            ((Stage) regPassCheck.getScene().getWindow()).close();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            System.out.println("Ошибка");
            alert.setContentText("Пользователь с данным логином уже существует.");
            alert.showAndWait();
        }
    }
}
