package view;

import model.MyShutdownHook;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Distribut;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.Vector;


public class Main extends Application {

    private static final Logger log = Logger.getLogger(Main.class);
    private static BufferedReader in;
    private static PrintWriter out;
    private static Stage stage;


    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        System.out.println(Thread.currentThread().getThreadGroup());
        final Thread currentThread = Thread.currentThread();
        Parent root = FXMLLoader.load(getClass().getResource("/xml/sampleLogin.fxml"));
        primaryStage.setTitle("Крутые Шахматы");
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
        log.info("Начало работы");
        Distribut distribut = new Distribut();
        distribut.setDaemon(true);
        distribut.start();
        Thread.currentThread().getThreadGroup();
        in = distribut.getIn();
        out = distribut.getOut();

    }

    public static BufferedReader getIn() {
        return in;
    }

    public static void setIn(BufferedReader in) {
        Main.in = in;
    }

    public static void send(String message){
        out.println(message);
        out.flush();
        log.info(message);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static Logger getLog() {
        return log;
    }
}
