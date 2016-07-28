package model;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Created by User on 10.07.2016.
 */
public class Box {
    public Pane pane;
    public String name;
    public boolean white;
    public ImageView image;

    public Box(Pane pane){
        this.pane = pane;
        image = (ImageView) pane.getChildren().get(0);
    }
    public Box(){

    }
}

