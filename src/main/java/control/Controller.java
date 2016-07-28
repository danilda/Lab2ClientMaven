package control;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.*;
import model.worker.DoStep;

import java.util.ArrayList;
import static java.util.Objects.isNull;

public class Controller {

    @FXML
    private GridPane board;

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

    private static boolean white ;

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

    public static boolean isNowStep() {
        return nowStep;
    }

    public static void setNowStep(boolean nowStep) {
        Controller.nowStep = nowStep;
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
                    ChessLogik.run(thisBox, matrix, nowI, nowY, true);
                    ChessLogik.checkOnStep(matrix, nowI, nowY);
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
                    System.out.println("Мат -------->" + ChessLogik.checkOnMate(matrix));
                    Send.sendStep(findIndex(matrix, nowBox), findIndex(matrix, thisBox) );
                    if(ChessLogik.checkOnMate(matrix)) {
                        Send.sendMate();
                    }
                    nowBox.image.setImage(null);
                    nowBox.name = null;
                    nowBox.white = false;
                    nowBox = null;
                    DoStep.setMatrix(matrix);
                    nowStep = false;
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
                }
            }

        }

    }
}
