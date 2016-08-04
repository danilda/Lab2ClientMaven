package control;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Box;
import model.Send;
import model.worker.CancelDraw;
import model.worker.SuccessDraw;

/**
 * Created by User on 03.08.2016.
 */
public class ControllerPawnUp {
    private static Controller controller;

    private static byte position;

    @FXML
    private ImageView rook;

    @FXML
    private ImageView horse;

    @FXML
    private ImageView bis;

    @FXML
    private ImageView queen;

    public static void setController(Controller controller) {
        ControllerPawnUp.controller = controller;
    }

    public static void setPosition(byte position) {
        ControllerPawnUp.position = position;
    }

    @FXML
    private void initialize() {
        rook.setImage(controller.getRook());
        horse.setImage(controller.getHorse());
        bis.setImage(controller.getBis());
        queen.setImage(controller.getQueen());
    }

    public void rookClicked(){
        String n = "";
        Image img = controller.getRook();
        if(Controller.isWhite()){
            n = "White";
            img = controller.getRookWhite();
        }
        Box[][] matrix = controller.getMatrix();
        matrix[0][position%10].name = "rook" + n;
        matrix[0][position%10].white = Controller.isWhite();
        matrix[0][position%10].image.setImage(img);
        Send.sendPawnUp(position, "rook" + n );
        ((Stage) bis.getScene().getWindow()).close();
    }
    public void horseClicked(){
        String n = "";
        Image img = controller.getHorse();
        if(Controller.isWhite()){
            n = "White";
            img = controller.getHorseWhite();
        }
        Box[][] matrix = controller.getMatrix();
        matrix[0][position%10].name = "horse" + n;
        matrix[0][position%10].white = Controller.isWhite();
        matrix[0][position%10].image.setImage(img);
        Send.sendPawnUp(position, "horse" + n );
        ((Stage) bis.getScene().getWindow()).close();
    }
    public void bisClicked(){
        String n = "";
        Image img = controller.getBis();
        if(Controller.isWhite()){
            n = "White";
            img = controller.getBisWhite();
        }
        Box[][] matrix = controller.getMatrix();
        matrix[0][position%10].name = "bis" + n;
        matrix[0][position%10].white = Controller.isWhite();
        matrix[0][position%10].image.setImage(img);
        Send.sendPawnUp(position, "bis" + n );
        ((Stage) bis.getScene().getWindow()).close();
    }
    public void queenClicked(){
        String n = "";
        Image img = controller.getQueen();
        if(Controller.isWhite()){
            n = "White";
            img = controller.getQueenWhite();
        }
        Box[][] matrix = controller.getMatrix();
        matrix[0][position%10].name = "queen" + n;
        matrix[0][position%10].white = Controller.isWhite();
        matrix[0][position%10].image.setImage(img);
        Send.sendPawnUp(position, "queen" + n );
        ((Stage) bis.getScene().getWindow()).close();
    }
}
