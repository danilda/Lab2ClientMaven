package control;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.LinksControll;
import model.Parser;
import model.Send;
import model.worker.CreateNewUser;
import view.Main;

public class ControllerEdit implements Controllers {

    @FXML
    private TextField regLogin;

    @FXML
    private TextField regPass;

    @FXML
    private TextField regPassCheck;
    private Send send = new Send();


    @FXML
    private void initialize() {
        LinksControll.setControllers(this);
    }

    public void endRegistration(ActionEvent actionEvent){
        if(!regLogin.getText().equals("") && !regPass.getText().equals("")
                && regPass.getText().equals(regPassCheck.getText())){
            send.sendQueryRegistration(regLogin.getText(), regPass.getText());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            Main.getLog().info("Ошибка");
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
            Main.getLog().info("Ошибка");
            alert.setContentText("Пользователь с данным логином уже существует.");
            alert.showAndWait();
        }
    }


    @Override
    public boolean isLife() {
        return regPass.getScene().getWindow().isShowing();
    }

    public void cancel(){
        ((Stage) regPassCheck.getScene().getWindow()).close();
    }
}
