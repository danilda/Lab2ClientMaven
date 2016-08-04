package view;

import model.MyShutdownHook;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Distribut;

import java.io.*;
import java.net.Socket;
import java.util.Vector;


public class Main extends Application {

    public static Thread distribut;
    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;
    public static Stage stage;


    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        System.out.println(Thread.currentThread().getThreadGroup());
        final Thread currentThread = Thread.currentThread();
        Parent root = FXMLLoader.load(getClass().getResource("/xml/sampleLogin.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
        try {
            this.socket = new Socket("localhost", 4444);
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out =  new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            MyShutdownHook myShutdownHook = new MyShutdownHook(out);
            Runtime.getRuntime().addShutdownHook(myShutdownHook);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Distribut distribut = new Distribut(this.socket);
        distribut.setDaemon(true);
        distribut.start();
        this.distribut = distribut;
        Thread.currentThread().getThreadGroup();

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
        System.out.println(message);
        System.out.println();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
