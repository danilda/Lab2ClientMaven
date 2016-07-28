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

    public static void sendQueryLobb(){
        String result;
        result = "<body>\n" +
                " <metaInfo>Lobby</metaInfo>\n" +
                "</body>";
        Main.send(result);
    }


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

}