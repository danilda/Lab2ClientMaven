package model.worker;

import control.Controller;
import javafx.application.Platform;
import model.Box;
import model.ChessLogik;
import model.LinksControll;
import model.Parser;

import java.util.ArrayList;

/**
 * Created by User on 25.07.2016.
 */
public class DoStep implements Doer {
    private Controller controller = LinksControll.getControllerChess();

    private Box[][] matrix = controller.getMatrix();

    @Override
    public void doAction(ArrayList parameters) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ChessLogik chessLogik = new ChessLogik();
                chessLogik.step(matrix,
                        matrix[7 - Integer.parseInt((String) parameters.get(1))/10]
                                [Integer.parseInt((String) parameters.get(1))%10],
                        matrix[7 - Integer.parseInt((String) parameters.get(2))/10]
                                [Integer.parseInt((String) parameters.get(2))%10]);
                controller.setNowStep(true);
            }
        });
    }

}
