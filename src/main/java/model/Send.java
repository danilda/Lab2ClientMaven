package model;

import view.Main;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

/**
 * Created by User on 12.07.2016.
 */
public class Send extends Thread{

    public static void sendStep(int of, int into) {
        String result;
        result = "<body>\n" +
                "    <metaInfo>DoStep</metaInfo>\n" +
                "    <positionOf>"+ of + "</positionOf>\n" +
                "    <positionInto>"+ into +"</positionInto>\n" +
                "</body>";
        Main.send(result);

    }

    public static void sendQueryAboutIntlz(String login, String pass){
        String result;
        result = "<body>\n" +
                "    <metaInfo>CheckInitialize</metaInfo>\n" +
                "    <login>"+ login + "</login>\n" +
                "    <pass>"+ pass +"</pass>\n" +
                "</body>";
        Main.send(result);
    }

    public static void sendQueryDuel(String nameOpponent , String color){
        String result;
        result = "<body>\n" +
                "    <metaInfo>Duel</metaInfo>\n" +
                "    <nameOpponent>"+ nameOpponent + "</nameOpponent>\n" +
                "    <color>"+ color + "</color>\n" +
                "</body>";
        Main.send(result);
    }

    public static void sendQueryRegistration(String login, String pass){
        String result;
        result = "<body>\n" +
                "    <metaInfo>CreateNewUser</metaInfo>\n" +
                "    <login>"+ login + "</login>\n" +
                "    <pass>"+ pass +"</pass>\n" +
                "</body>";
        Main.send(result);
    }

    public static void sendQueryUser(String nameOpponent){
        String result;
        result = "<body>\n" +
                "    <metaInfo>QueryUser</metaInfo>\n" +
                "    <nameOpponent>"+ nameOpponent + "</nameOpponent>\n" +
                "</body>";
        Main.send(result);
    }

//    public static void sendQueryLobb(){
//        String result;
//        result = "<body>\n" +
//                " <metaInfo>Lobby</metaInfo>\n" +
//                "</body>";
//        Main.send(result);
//    }


    public static void sendCancel(){
        String result;
        result = "<body>\n" +
                " <metaInfo>Cancel</metaInfo>\n" +
                "</body>";
        Main.send(result);
    }

    public static void sendSuccess(String color){
        String result;
        result = "<body>\n" +
                " <metaInfo>SuccessDuel</metaInfo>\n" +
                " <color>" + color + "</color>\n" +
                "</body>";
        Main.send(result);
    }

    public static void sendMate(){
        String result;
        result = "<body>\n" +
                " <metaInfo>Mate</metaInfo>\n" +
                "</body>";
        Main.send(result);
    }

    public static void sendQueryDraw(){
        String result;
        result = "<body>\n" +
                " <metaInfo>Draw</metaInfo>\n" +
                "</body>";
        Main.send(result);
    }

    public static void sendCancelDraw(){
        String result;
        result = "<body>\n" +
                " <metaInfo>CancelDraw</metaInfo>\n" +
                "</body>";
        Main.send(result);
    }

    public static void sendSuccessDraw(){
        String result;
        result = "<body>\n" +
                " <metaInfo>SuccessDraw</metaInfo>\n" +
                "</body>";
        Main.send(result);
    }

    public static void sendPass(){
        String result;
        result = "<body>\n" +
                " <metaInfo>Pass</metaInfo>\n" +
                "</body>";
        Main.send(result);
    }

    public static void sendMessage(String text){
        String result;
        result = "<body>\n" +
                " <metaInfo>Message</metaInfo>\n" +
                " <text>" + text + "</text>\n" +
                "</body>";
        Main.send(result);
    }

    public static void sendPad(){
        String result;
        result = "<body>\n" +
                " <metaInfo>Pad</metaInfo>\n" +
                "</body>";
        Main.send(result);
    }

    public static void sendDisconnection() {
        String result;
        result = "<body>\n" +
                " <metaInfo>Disconnected</metaInfo>\n" +
                "</body>";
        Main.send(result);
    }

    public static void sendPawnUp(byte position, String name) {
        String result;
        result = "<body>\n" +
                " <metaInfo>PawnUp</metaInfo>\n" +
                " <position>"+ position +"</position>\n" +
                " <name>"+ name +"</name>\n" +
                "</body>";
        Main.send(result);
    }

}
