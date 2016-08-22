package model.worker;

import control.Controller;
import javafx.scene.image.Image;
import model.Box;
import model.LinksControll;

import java.util.ArrayList;

/**
 * Created by User on 04.08.2016.
 */
public class PawnUp implements Doer {
    private Controller controller = LinksControll.getControllerChess();

    @Override
    public void doAction(ArrayList parameters) {
        Box[][] matrix = controller.getMatrix();
        matrix[7][Integer.parseInt((String) parameters.get(1))%10].name = (String) parameters.get(2);
        matrix[7][Integer.parseInt((String) parameters.get(1))%10].white = controller.isWhite();
        Image img = null;
        if(parameters.get(2).equals("rook"))
            img = controller.getRook();
        if(parameters.get(2).equals("rookWhite"))
            img = controller.getRookWhite();
        if(parameters.get(2).equals("horse"))
            img = controller.getHorse();
        if(parameters.get(2).equals("horseWhite"))
            img = controller.getHorseWhite();
        if(parameters.get(2).equals("bis"))
            img = controller.getBis();
        if(parameters.get(2).equals("bisWhite"))
            img = controller.getBisWhite();
        if(parameters.get(2).equals("queen"))
            img = controller.getQueen();
        if(parameters.get(2).equals("queenWhite"))
            img = controller.getQueenWhite();
        matrix[7][Integer.parseInt((String) parameters.get(1))%10].image.setImage(img);
    }
}
