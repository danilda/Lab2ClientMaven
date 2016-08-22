package model;

import view.Main;

/**
 * Created by User on 12.07.2016.
 */
public class Send extends Thread{
    private static final String returnCancel =  "<body>\n" +
                                                    " <metaInfo>Cancel</metaInfo>\n" +
                                                "</body>";

    private static final String returnMate =    "<body>\n" +
                                                    " <metaInfo>Mate</metaInfo>\n" +
                                                "</body>";

    private static final String returnDraw =    "<body>\n" +
                                                    " <metaInfo>Draw</metaInfo>\n" +
                                                "</body>";

    private static final String returnCancelDraw =    "<body>\n" +
                                                    " <metaInfo>CancelDraw</metaInfo>\n" +
                                                "</body>";

    private static final String returnSuccessDraw =    "<body>\n" +
                                                        " <metaInfo>SuccessDraw</metaInfo>\n" +
                                                "</body>";

    private static final String returnPass =    "<body>\n" +
                                                    " <metaInfo>Pass</metaInfo>\n" +
                                                "</body>";

    private static final String returnPad =     "<body>\n" +
                                                    " <metaInfo>Pad</metaInfo>\n" +
                                                "</body>";

    private static final String returnDisconnected ="<body>\n" +
                                                    " <metaInfo>Disconnected</metaInfo>\n" +
                                                "</body>";

    private static final String returnUserInfo ="<body>\n" +
                                                    " <metaInfo>UserInfo</metaInfo>\n" +
                                                "</body>";

    public void sendStep(int of, int into) {
        String result;
        result = "<body>\n" +
                "    <metaInfo>DoStep</metaInfo>\n" +
                "    <positionOf>"+ of + "</positionOf>\n" +
                "    <positionInto>"+ into +"</positionInto>\n" +
                "</body>";
        Main.send(result);

    }

    public void sendQueryAboutIntlz(String login, String pass){
        String result;
        result = "<body>\n" +
                "    <metaInfo>CheckInitialize</metaInfo>\n" +
                "    <login>"+ login + "</login>\n" +
                "    <pass>"+ pass +"</pass>\n" +
                "</body>";
        Main.send(result);
    }

    public void sendQueryDuel(String nameOpponent , String color){
        String result;
        result = "<body>\n" +
                "    <metaInfo>Duel</metaInfo>\n" +
                "    <nameOpponent>"+ nameOpponent + "</nameOpponent>\n" +
                "    <color>"+ color + "</color>\n" +
                "</body>";
        Main.send(result);
    }

    public void sendQueryRegistration(String login, String pass){
        String result;
        result = "<body>\n" +
                "    <metaInfo>CreateNewUser</metaInfo>\n" +
                "    <login>"+ login + "</login>\n" +
                "    <pass>"+ pass +"</pass>\n" +
                "</body>";
        Main.send(result);
    }

    public void sendQueryUser(String nameOpponent){
        String result;
        result = "<body>\n" +
                "    <metaInfo>QueryUser</metaInfo>\n" +
                "    <nameOpponent>"+ nameOpponent + "</nameOpponent>\n" +
                "</body>";
        Main.send(result);
    }

    public void sendCancel(){
        Main.send(returnCancel);
    }

    public void sendSuccess(String color){
        String result;
        result = "<body>\n" +
                " <metaInfo>SuccessDuel</metaInfo>\n" +
                " <color>" + color + "</color>\n" +
                "</body>";
        Main.send(result);
    }

    public void sendMate(){
        Main.send(returnMate);
    }

    public void sendQueryDraw(){
        Main.send(returnDraw);
    }

    public void sendCancelDraw(){
        Main.send(returnCancelDraw);
    }

    public void sendSuccessDraw(){
        Main.send(returnSuccessDraw);
    }

    public void sendPass(){
        Main.send(returnPass);
    }

    public void sendMessage(String text){
        String[] textArr = text.split("\n");
        String result;
        System.out.println(textArr.length);
        if(textArr.length == 1) {
            result = "<body>\n" +
                    " <metaInfo>Message</metaInfo>\n" +
                    " <transfer>" +  false + "</transfer>\n" +
                    " <text>" + text + "</text>\n" +
                    "</body>";
            Main.send(result);
        } else {
            result = "<body>\n" +
                    " <metaInfo>Message</metaInfo>\n" +
                    " <transfer>" +  true + "</transfer>\n" +
                    " <text>" + textArr[0] + "</text>\n";
            StringBuilder sb = new StringBuilder(result);
            for(int i = 1; i < textArr.length; i++)
                sb.append(" <textAdded>" + textArr[i] + "</textAdded>\n" );
            sb.append("</body>");
            Main.send(sb.toString());
        }
    }

    public void sendPad(){
        Main.send(returnPad);
    }

    public void sendDisconnection() {
        Main.send(returnDisconnected);
    }

    public void sendPawnUp(byte position, String name) {
        String result;
        result = "<body>\n" +
                " <metaInfo>PawnUp</metaInfo>\n" +
                " <position>"+ position +"</position>\n" +
                " <name>"+ name +"</name>\n" +
                "</body>";
        Main.send(result);
    }

    public void sendUserInfo() {
        Main.send(returnUserInfo);
    }

}
