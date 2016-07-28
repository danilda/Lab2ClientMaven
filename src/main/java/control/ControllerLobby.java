package control;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Send;
import model.worker.CheckDuel;
import model.worker.Duel;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 12.07.2016.
 */
public class ControllerLobby{
    private String currentItem = null;
    private static String myName;
    private static String myGame;
    private static String myWins;
    private static String opName;
    private static String opGame;
    private static String opWins;
    private static String opStatus;
    private static boolean switcher;
    private static Labeled dialog;
    private static Window node;
    public static ObservableList<String> itemsList = FXCollections.observableArrayList();
    private String color;

    @FXML
    private Label opNameL;
    @FXML
    private Label opGameL;
    @FXML
    private Label opWinsL;
    @FXML
    private Label opStatusL;
    @FXML
    private Label myNameL;
    @FXML
    private Label myGameL;
    @FXML
    private Label myWinsL;// добавить в прошлом класе
    @FXML
    private ChoiceBox<String> colorChoice;

    @FXML
    private ListView<String> list;

    private static Thread thread;

    public static String getMyName() {
        return myName;
    }

    public static void setMyName(String myName) {
        ControllerLobby.myName = myName;
    }

    public static String getMyGame() {
        return myGame;
    }

    public static void setMyGame(String myGame) {
        ControllerLobby.myGame = myGame;
    }

    public static String getMyWins() {
        return myWins;
    }

    public static void setMyWins(String myWins) {
        ControllerLobby.myWins = myWins;
    }

    public static String getOpName() {
        return opName;
    }

    public static void setOpName(String opName) {
        ControllerLobby.opName = opName;
    }

    public static String getOpGame() {
        return opGame;
    }

    public static void setOpGame(String opGame) {
        ControllerLobby.opGame = opGame;
    }

    public static String getOpWins() {
        return opWins;
    }

    public static void setOpWins(String opWins) {
        ControllerLobby.opWins = opWins;
    }

    public static boolean isSwitcher() {
        return switcher;
    }

    public static void setSwitcher(boolean switcher) {
        ControllerLobby.switcher = switcher;
    }

    public static String getOpStatus() {
        return opStatus;
    }

    public static void setOpStatus(String opStatus) {
        ControllerLobby.opStatus = opStatus;
    }

    public static Window getNode() {
        return node;
    }

    public static void setNode(Window node) {
        ControllerLobby.node = node;
    }

    public static Labeled getDialog() {
        return dialog;
    }

    public static void setDialog(Labeled dialog) {
        ControllerLobby.dialog = dialog;
    }

    public static Thread getThread() {
        return thread;
    }

    public static void setThread(Thread thread) {
        ControllerLobby.thread = thread;
    }

    public static void lobbyAddName(String name) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                itemsList.add(name);
            }
        });
    }

    public static void lobbyRemoveName(String name) {
        itemsList.remove(name);
    }

    public static void setLobby(ArrayList<String> lobby) {
        itemsList = FXCollections.observableArrayList (lobby);
    }


    @FXML
    private void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList ( );
        list.setItems(items);
        thread = new Thread(new Runnable() {
            public void run() {
                String str = "";
                while (true){
                    currentItem = list.getSelectionModel().getSelectedItem();
                    if(currentItem != null){
                        if(!str.equals(currentItem)){
                            str = currentItem;
                            Send.sendQueryUser(currentItem);
                        }
                    }
                    if(!list.getItems().equals(itemsList)) {
                        list.setItems(itemsList);
                    }
                }
            }
        });
        thread.setDaemon(true);// <---
        thread.start();
        myNameL.setText(myName);
        myGameL.setText(myGame);
        myWinsL.setText(myWins);
        CheckDuel.setLabel(myGameL);
        color = "random";
        ObservableList<String> itemsForChoice = FXCollections.observableArrayList ( "Белый", "Черный", "Не важно");
        Duel.setLabel(myGameL);
        colorChoice.setItems(itemsForChoice);
        colorChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int tmpcolor = (int) newValue;
                System.out.println(tmpcolor);
                if (tmpcolor == 0) {
                    color = "white";
                }
                if (tmpcolor == 1) {
                    color = "black";
                }
                if (tmpcolor == 2) {
                    color = "random";
                }
                ControllerCheck.setColor(color);
            }
        });
    }

    public void duelButton(ActionEvent actionEvent) throws IOException {
        String selectedItem = list.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem.toString() + " --- выбраный противник");
        Send.sendQueryDuel(selectedItem.toString(), color);
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/xml/lobbyWait.fxml"));
            stage.setTitle("Hello World");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(myWinsL.getScene().getWindow());
            stage.show();
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }

    public void onClickList(){
        while (true){
            System.out.println(opName + " " + opGame + " " + opWins + " " + opStatus);
            if(switcher){
                opNameL.setText(opName);
                opGameL.setText(opGame);
                opWinsL.setText(opWins);
                opStatusL.setText(opStatus);
                switcher = false;
                break;
            }
        }
    }


}
