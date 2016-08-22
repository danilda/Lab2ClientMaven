package view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by User on 01.08.2016.
 */
public class PaneForList {
    private String name;
    private String message;
    private Pane pane;
    private Label label = new Label();
    public PaneForList(String name, String message){
        this.name = name;
        this.message = message;
    }


    public Pane returnObject(){
        label.setText(name + " : " +message);
        pane = new Pane();
        pane.setStyle("-fx-width: 240px;");
        pane.setStyle("-fx-background-color: #ffffff");
        pane.getChildren().add(label);
        return pane;
    }
}
