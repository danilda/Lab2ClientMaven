package control;


import javafx.application.Platform;
import javafx.beans.binding.StringBinding;
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
import model.LinksControll;
import model.Parser;
import model.Send;
import model.worker.*;
import org.apache.log4j.Logger;
import view.Main;
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
    final private static Logger log = Logger.getLogger(ControllerEdit.class);
    private String currentItem = null;
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

    private Thread thread;
    private Send send = new Send();


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

    public String getColor() {
        return color;
    }

    public Label getOpWinsL() {
        return opWinsL;
    }

    public void setOpWinsL(Label opWinsL) {
        this.opWinsL = opWinsL;
    }

    @FXML
    private void initialize() {
        LinksControll.setControllerLobby(this);
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
                            send.sendQueryUser(currentItem);
                        }
                    }
                    if(!list.getItems().equals(itemsList)) {
                        list.setItems(itemsList);
                    }
                }
            }
        });
        send.sendUserInfo();
        thread.setDaemon(true);// <---
        thread.start();
        color = "random";
        ObservableList<String> itemsForChoice = FXCollections.observableArrayList ( "Белый", "Черный", "Не важно");
        colorChoice.setItems(itemsForChoice);
        colorChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int tmpColor = (int) newValue;
                if (tmpColor == 0) {
                    color = "white";
                }
                if (tmpColor == 1) {
                    color = "black";
                }
                if (tmpColor == 2) {
                    color = "random";
                }
            }
        });

    }

    public void duelButton(ActionEvent actionEvent) throws IOException {
        String selectedItem = list.getSelectionModel().getSelectedItem();
        if(!isNull(selectedItem)) {
            Main.getLog().info(selectedItem.toString() + " --- выбраный противник");
            send.sendQueryDuel(selectedItem.toString(), color);
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
                log.error(e.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Внимание!");
            alert.setHeaderText(null);
            log.info("Ошибка");
            alert.setContentText("Выберите противника в списке!");
            alert.showAndWait();
        }
    }

    public void onRefreshList(String opName, String opGame,
                              String opWins, String opLoses, String opStatus){
        opNameL.setText(opName);
        opGameL.setText(opGame);
        opWinsL.setText(opWins);
        opLoseL.setText(opLoses);
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
        myWinsL.setText(myWins);
        myLoseL.setText(myLoses);
        myDrawsL.setText(Integer.toString(Integer.parseInt(myGame) -
                Integer.parseInt(myWins) - Integer.parseInt(myLoses)));
    }


    public void sendMessage(){
        if(textMessage.getText().split("\\s").length > 0 && !textMessage.getText().equals("")) {
            chatList.add(new PaneForList("вы", textMessage.getText()).returnObject());
            chat.setItems(chatList);
            chat.scrollTo(chatList.size() - 1);
            send.sendMessage(textMessage.getText());
            textMessage.setText("");
        }
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
    @Override
    public boolean isLife() {
        return chat.getScene().getWindow().isShowing();
    }
}
