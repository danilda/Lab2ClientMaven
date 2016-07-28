package model.worker;

import control.Controller;
import javafx.application.Platform;
import model.Box;
import model.ChessLogik;

import java.util.ArrayList;

/**
 * Created by User on 25.07.2016.
 */
public class DoStep implements Doer {
    private static Box[][] matrix;

    @Override
    public void doAction(ArrayList parameters) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ChessLogik.step(matrix,
                        matrix[7 - Integer.parseInt((String) parameters.get(1))/10]
                                [Integer.parseInt((String) parameters.get(1))%10],
                        matrix[7 - Integer.parseInt((String) parameters.get(2))/10]
                                [Integer.parseInt((String) parameters.get(2))%10]);
                Controller.setNowStep(true);
            }
        });
    }

    public static Box[][] getMatrix() {
        return matrix;
    }

    public static void setMatrix(Box[][] matrix) {
        DoStep.matrix = matrix;
    }
}
