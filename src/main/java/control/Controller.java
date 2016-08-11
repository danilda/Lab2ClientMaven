package control;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import model.worker.*;
import view.PaneForList;

import java.io.IOException;
import java.util.ArrayList;
import static java.util.Objects.isNull;

public class Controller implements Chat {

    @FXML
    private ListView<Pane> chat;

    @FXML
    private TextArea textMessage;

    @FXML
    private GridPane board;

    @FXML
    private Button buttonLose;

    private Image pawn = new Image("/image/pawn.png");
    private Image pawnWhite = new Image("/image/pawnWhite.png");
    private Image horse = new Image("/image/horse.png");
    private Image horseWhite = new Image("/image/horseWhite.png");
    private Image rook = new Image("/image/rook.png");
    private Image rookWhite = new Image("/image/rookWhite.png");
    private Image bis = new Image("/image/bis.png");
    private Image bisWhite = new Image("/image/bisWhite.png");
    private Image king = new Image("/image/king.png");
    private Image kingWhite = new Image("/image/kingWhite.png");
    private Image queen = new Image("/image/queen.png");
    private Image queenWhite = new Image("/image/queenWhite.png");

    private ObservableList<Pane> chatList = FXCollections.observableArrayList ( );
    private static boolean white ;
    private static boolean end ;

    private Box[][] matrix;

    private Box nowBox;
    private Box changedBox = new Box(new Pane(new ImageView()));

    private static boolean nowStep;

    public static boolean isWhite() {
        return white;
    }

    public static void setWhite(boolean white) {
        Controller.white = white;
    }


    public static void setNowStep(boolean nowStep) {
        Controller.nowStep = nowStep;
    }


    public static void setEnd(boolean end) {
        Controller.end = end;
    }

    public Box[][] getMatrix() {
        return matrix;
    }

    public Image getHorse() {
        return horse;
    }

    public void setHorse(Image horse) {
        this.horse = horse;
    }

    public Image getRook() {
        return rook;
    }

    public void setRook(Image rook) {
        this.rook = rook;
    }

    public Image getBis() {
        return bis;
    }

    public void setBis(Image bis) {
        this.bis = bis;
    }

    public Image getQueen() {
        return queen;
    }

    public void setQueen(Image queen) {
        this.queen = queen;
    }

    public Image getHorseWhite() {
        return horseWhite;
    }

    public Image getRookWhite() {
        return rookWhite;
    }

    public Image getBisWhite() {
        return bisWhite;
    }

    public Image getQueenWhite() {
        return queenWhite;
    }

    @FXML
    private void initialize(){
        ObservableList allChildren = board.getChildren();
        ArrayList<Pane> array = new ArrayList<>();
        matrix = new Box[8][8];
        int n = 0;

        for(int i = 0; i < 8; i++)
            for(int y = 0; y < 8; y++){
                matrix[i][y] = new Box((Pane) allChildren.get(n));
                int tmp = 0;
                if(white)
                    tmp = 1;
                if ((i+y + tmp) % 2 == 0) {
                    matrix[i][y].pane.setStyle("-fx-background-color: #ffffe7;");
                } else {
                    matrix[i][y].pane.setStyle("-fx-background-color: #e6a875;");
                }
                n++;
            }

        for(int i = 0; i < 8; i++){
            if(white){
                matrix[6][i].image.setImage(pawnWhite);
                matrix[6][i].white = true;
                matrix[6][i].name = "pawnWhite";
                matrix[1][i].image.setImage(pawn);
                matrix[1][i].name = "pawn";
            }else{
                matrix[1][i].image.setImage(pawnWhite);
                matrix[1][i].white = true;
                matrix[1][i].name = "pawnWhite";
                matrix[6][i].image.setImage(pawn);
                matrix[6][i].name = "pawn";
            }
        }

        if(white){
            matrix[0][0].image.setImage(rook);
            matrix[0][0].name = "rook";

            matrix[0][7].image.setImage(rook);
            matrix[0][7].name = "rook";

            matrix[7][0].image.setImage(rookWhite);
            matrix[7][0].name = "rookWhite";
            matrix[7][0].white = true;

            matrix[7][7].image.setImage(rookWhite);
            matrix[7][7].name = "rookWhite";
            matrix[7][7].white = true;

            matrix[0][1].image.setImage(horse);
            matrix[0][1].name = "horse";

            matrix[0][6].image.setImage(horse);
            matrix[0][6].name = "horse";

            matrix[7][1].image.setImage(horseWhite);
            matrix[7][1].name = "horseWhite";
            matrix[7][1].white = true;

            matrix[7][6].image.setImage(horseWhite);
            matrix[7][6].name = "horseWhite";
            matrix[7][6].white = true;

            matrix[0][2].image.setImage(bis);
            matrix[0][2].name = "bis";

            matrix[0][5].image.setImage(bis);
            matrix[0][5].name = "bis";

            matrix[7][2].image.setImage(bisWhite);
            matrix[7][2].name = "bisWhite";
            matrix[7][2].white = true;

            matrix[7][5].image.setImage(bisWhite);
            matrix[7][5].name = "bisWhite";
            matrix[7][5].white = true;

            matrix[0][3].image.setImage(king);
            matrix[0][3].name = "king";

            matrix[0][4].image.setImage(queen);
            matrix[0][4].name = "queen";

            matrix[7][3].image.setImage(kingWhite);
            matrix[7][3].name = "kingWhite";
            matrix[7][3].white = true;

            matrix[7][4].image.setImage(queenWhite);
            matrix[7][4].name = "queenWhite";
            matrix[7][4].white = true;

        } else {
            matrix[7][0].image.setImage(rook);
            matrix[7][0].name = "rook";

            matrix[7][7].image.setImage(rook);
            matrix[7][7].name = "rook";

            matrix[0][0].image.setImage(rookWhite);
            matrix[0][0].name = "rookWhite";
            matrix[0][0].white = true;

            matrix[0][7].image.setImage(rookWhite);
            matrix[0][7].name = "rookWhite";
            matrix[0][7].white = true;

            matrix[7][1].image.setImage(horse);
            matrix[7][1].name = "horse";

            matrix[7][6].image.setImage(horse);
            matrix[7][6].name = "horse";

            matrix[0][1].image.setImage(horseWhite);
            matrix[0][1].name = "horseWhite";
            matrix[0][1].white = true;

            matrix[0][6].image.setImage(horseWhite);
            matrix[0][6].name = "horseWhite";
            matrix[0][6].white = true;

            matrix[7][2].image.setImage(bis);
            matrix[7][2].name = "bis";

            matrix[7][5].image.setImage(bis);
            matrix[7][5].name = "bis";

            matrix[0][2].image.setImage(bisWhite);
            matrix[0][2].name = "bisWhite";
            matrix[0][2].white = true;

            matrix[0][5].image.setImage(bisWhite);
            matrix[0][5].name = "bisWhite";
            matrix[0][5].white = true;

            matrix[7][3].image.setImage(king);
            matrix[7][3].name = "king";

            matrix[7][4].image.setImage(queen);
            matrix[7][4].name = "queen";

            matrix[0][3].image.setImage(kingWhite);
            matrix[0][3].name = "kingWhite";
            matrix[0][3].white = true;

            matrix[0][4].image.setImage(queenWhite);
            matrix[0][4].name = "queenWhite";
            matrix[0][4].white = true;
        }
        DoStep.setMatrix(matrix);
        Mate.setButton(buttonLose);
        Pad.setButton(buttonLose);
        CheckDraw.setButton(buttonLose);
        SuccessDraw.setButtonControll(buttonLose);
        ControllerDraw.setButtonControll(buttonLose);
        Pass.setButton(buttonLose);
        Message.setController(this);
        ControllerPawnUp.setController(this);
        PawnUp.setController(this);
    }

    private int findIndex(Box[][] matrix , Box box){
        int i;
        int y;
        for( i = 0 ; i < 8; i++)
            for( y = 0 ; y < 8; y++){
                if(box == matrix[i][y]){
                    return i*10+y;
                }
            }
        return -1;
    }



    public void onPaneClick(Event event){
        DoStep.setMatrix(matrix);
        if(nowStep) {
            Pane thisPane = (Pane) event.getSource();
            Box thisBox = null;
            int nowI = -10;
            int nowY = -10;
            for (int i = 0; i < 8; i++)
                for (int y = 0; y < 8; y++) {
                    if (matrix[i][y].pane.equals(thisPane)) {
                        thisBox = matrix[i][y];
                        nowI = i;
                        nowY = y;
                        break;
                    }
                }
            if (isNull(nowBox) || (thisBox.name != null && thisBox.white == this.white)){
                for (int i = 0; i < 8; i++)
                    for (int y = 0; y < 8; y++) {
                        if (matrix[i][y].pane.getStyle().split(" ")[1].equals("#a5f2de;") ||
                                matrix[i][y].pane.getStyle().split(" ")[1].equals("#ff8584;")) {
                            int tmp = 0;
                            if(white)
                                tmp = 1;
                            if ((i+y + tmp) % 2 == 0) {
                                matrix[i][y].pane.setStyle("-fx-background-color: #ffffe7;");
                            } else {
                                matrix[i][y].pane.setStyle("-fx-background-color: #e6a875;");
                            }
                        }
                    }
                if (thisBox.white == this.white && thisBox.name != null) {
                    ChessLogik.checkOnStep(matrix, nowI, nowY, true);
                    nowBox = thisBox;
                }

            }
            if (!isNull(nowBox)){
                if(thisBox.pane.getStyle().split(" ")[1].equals("#a5f2de;")
                        || thisBox.pane.getStyle().split(" ")[1].equals("#ff8584;")){
                    thisBox.image.setImage(nowBox.image.getImage());
                    thisBox.name = nowBox.name;
                    thisBox.white = nowBox.white;
                    changedBox.image.setImage(nowBox.image.getImage());
                    changedBox.name = nowBox.name;
                    changedBox.white = nowBox.white;
//                    System.out.println("Мат -------->" + ChessLogik.checkOnMate(matrix));
                    Send.sendStep(findIndex(matrix, nowBox), findIndex(matrix, thisBox) );
                    if(findIndex(matrix, thisBox)<10 && (thisBox.name.equals("pawn")
                            || thisBox.name.equals("pawnWhite"))){
                        ControllerPawnUp.setPosition((byte) findIndex(matrix, thisBox));
                        Stage stage = new Stage();
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/xml/gamePawnUp.fxml"));
                            stage.setTitle("Hello World");
                            stage.setResizable(false);
                            stage.setScene(new Scene(root));
                            stage.initModality(Modality.WINDOW_MODAL);
                            stage.initOwner(buttonLose.getScene().getWindow());
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < 8; i++)
                        for (int y = 0; y < 8; y++) {
                            if (matrix[i][y].pane.getStyle().split(" ")[1].equals("#a5f2de;") ||
                                    matrix[i][y].pane.getStyle().split(" ")[1].equals("#ff8584;")) {
                                int tmp = 0;
                                if(white)
                                    tmp = 1;
                                if ((i+y + tmp) % 2 == 0) {
                                    matrix[i][y].pane.setStyle("-fx-background-color: #ffffe7;");
                                } else {
                                    matrix[i][y].pane.setStyle("-fx-background-color: #e6a875;");
                                }
                            }
                        }
                    nowBox.image.setImage(null);
                    nowBox.name = null;
                    nowBox.white = false;
                    if(ChessLogik.checkOnMate(matrix)) {
                        buttonLose.setText("Закрыть");
                        Send.sendMate();
                        end = true;
                        Stage stage = new Stage();
                        Parent root = null;
                        try {
                            ControllerMate.setInvisibility(true);
                            root = FXMLLoader.load(getClass().getResource("/xml/gameWin.fxml"));
                            stage.setTitle("Крутые Шахматы");
                            stage.setResizable(false);
                            stage.setScene(new Scene(root));
                            stage.initModality(Modality.WINDOW_MODAL);
                            stage.initOwner(buttonLose.getScene().getWindow());
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(ChessLogik.checkOnPad(matrix)){
                        buttonLose.setText("Закрыть");
                        Send.sendPad();
                        end = true;
                        Stage stage = new Stage();
                        Parent root = null;
                        try {
                            ControllerMate.setInvisibility(true);
                            root = FXMLLoader.load(getClass().getResource("/xml/gamePad.fxml"));
                            stage.setTitle("Крутые Шахматы");
                            stage.setResizable(false);
                            stage.setScene(new Scene(root));
                            stage.initModality(Modality.WINDOW_MODAL);
                            stage.initOwner(buttonLose.getScene().getWindow());
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ControllerMate.setInvisibility(true);
                    }

                    nowBox = null;
                    DoStep.setMatrix(matrix);
                    nowStep = false;

                }
            }

        }

    }
    public void goDraw(){
        Send.sendQueryDraw();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/xml/lobbyWait.fxml"));
            stage.setTitle("Крутые Шахматы");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(board.getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buttonLoseAction(){
        if(end){
            try {
                Stage stage = new Stage();
                Parent root = null;
                root = FXMLLoader.load(getClass().getResource("/xml/lobby.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add(0, "style.css");
                stage.setScene(scene);
                stage.setTitle("Крутые шахматы");
                ((Stage) chat.getScene().getWindow()).close();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Send.sendPass();
            try {
                Stage stage = new Stage();
                Parent root = null;
                root = FXMLLoader.load(getClass().getResource("/xml/lobby.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add(0, "style.css");
                stage.setScene(scene);
                stage.setTitle("Крутые шахматы");
                ((Stage) chat.getScene().getWindow()).close();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void sendMessage(){
        chatList.add(new PaneForList("вы", textMessage.getText()).returnObject());
        chat.setItems(chatList);
        chat.scrollTo(chatList.size()-1);
        Send.sendMessage(textMessage.getText());
        textMessage.setText("");

    }

    public void refreshMessages(ArrayList parameters){
        if(parameters.get(1).equals("false")){
            chatList.add(new PaneForList(
                    (String)parameters.get(2),(String)parameters.get(3)).returnObject());
        } else {
            StringBuilder text = new StringBuilder();
            for(int i = 3; i < parameters.size(); i++){
                text.append(parameters.get(i)+"\n");
            }
            chatList.add(new PaneForList(
                    (String)parameters.get(2), text.toString()).returnObject());
        }
        chat.setItems(chatList);
    }

    private static Box[][] swichMatrix(Box[][] matrixPr){
        Box[][] tmpMatrix = new Box[8][8];
        for(int i = 0 ; i < 8; i++) {
            for (int y = 0; y < 8; y++) {
                tmpMatrix[i][y] = new Box(new Pane(new ImageView()));
                tmpMatrix[i][y].pane.setStyle(matrixPr[i][y].pane.getStyle());
                tmpMatrix[i][y].white = matrixPr[i][y].white;
                tmpMatrix[i][y].name = matrixPr[i][y].name;
                tmpMatrix[i][y].image.setImage(matrixPr[i][y].image.getImage());
            }
        }
        return tmpMatrix;
    }

}
