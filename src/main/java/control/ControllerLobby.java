package control;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Send;
import model.worker.*;
import view.PaneForList;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import static java.util.Objects.isNull;

/**
 * Created by User on 12.07.2016.
 */
public class ControllerLobby implements Chat {
    private String currentItem = null;
    private static Labeled dialog;
    private static Window node;
    public static ObservableList<String> itemsList = FXCollections.observableArrayList();
    private ObservableList<Pane> chatList = FXCollections.observableArrayList ( );
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
    private Label opDrawsL;
    @FXML
    private Label opLoseL;
    @FXML
    private Label myNameL;
    @FXML
    private Label myGameL;
    @FXML
    private Label myWinsL;
    @FXML
    private Label myDrawsL;
    @FXML
    private Label myLoseL;
    @FXML
    private ChoiceBox<String> colorChoice;
    @FXML
    private ListView<Pane> chat;
    @FXML
    private TextArea textMessage;
    @FXML
    private ListView<String> list;

    private static Thread thread;


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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                itemsList.remove(name);
            }
        });
    }

    public static void setLobby(ArrayList<String> lobby) {
        itemsList = FXCollections.observableArrayList (lobby);
    }


    @FXML
    private void initialize() {
        UserInfo.setControllerLobby(this);
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
        Send.sendUserInfo();
        thread.setDaemon(true);// <---
        thread.start();
        CheckDuel.setLabel(myGameL);
        color = "random";
        ObservableList<String> itemsForChoice = FXCollections.observableArrayList ( "Белый", "Черный", "Не важно");
        Duel.setLabel(myGameL);
        colorChoice.setItems(itemsForChoice);
        colorChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int tmpColor = (int) newValue;
                System.out.println(tmpColor);
                if (tmpColor == 0) {
                    color = "white";
                }
                if (tmpColor == 1) {
                    color = "black";
                }
                if (tmpColor == 2) {
                    color = "random";
                }
                ControllerCheck.setColor(color);
            }
        });
        QueryUser.setController(this);
        Message.setController(this);
    }

    public void duelButton(ActionEvent actionEvent) throws IOException {
        String selectedItem = list.getSelectionModel().getSelectedItem();
        if(!isNull(selectedItem)) {
            System.out.println(selectedItem.toString() + " --- выбраный противник");
            Send.sendQueryDuel(selectedItem.toString(), color);
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/xml/lobbyWait.fxml"));
                stage.setTitle("Hello World");
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(myWinsL.getScene().getWindow());
                stage.show();
            } catch (Exception e) {
                e.getStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Внимание!");
            alert.setHeaderText(null);
            System.out.println("Ошибка");
            alert.setContentText("Выберите противника в списке!");
            alert.showAndWait();
        }
    }

    public void onRefreshList(String opName, String opGame,
                              String opWins, String opLoses, String opStatus){
        opNameL.setText(opName);
        opGameL.setText(opGame);
        opWinsL.setText(opWins);
        opWinsL.setText(opLoses);
        opDrawsL.setText(Integer.toString(Integer.parseInt(opGame) -
                Integer.parseInt(opWins) - Integer.parseInt(opLoses)));
        if(opStatus.equals("true")){
            opStatusL.setText("Играет");
        } else {
            opStatusL.setText("Свободен");
        }
    }

    public void onRefreshMyInfo(String myName, String myGame,
                              String myWins, String myLoses){
        myNameL.setText(myName);
        myGameL.setText(myGame);
        myWinsL.setText(myLoses);
        myLoseL.setText(myWins);
        myDrawsL.setText(Integer.toString(Integer.parseInt(myGame) -
                Integer.parseInt(myWins) - Integer.parseInt(myLoses)));
    }


    public void sendMessage(){
        chatList.add(new PaneForList("вы", textMessage.getText()).returnObject());
        chat.setItems(chatList);
        chat.scrollTo(chatList.size()-1);
        Send.sendMessage(textMessage.getText());
        textMessage.setText("");

    }

    public void refreshMessages(String name, String text){
        chatList.add(new PaneForList(name, text).returnObject());
        chat.setItems(chatList);
    }
}
