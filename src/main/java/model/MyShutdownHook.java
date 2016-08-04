package model;

import java.io.PrintWriter;

/**
 * Created by Клиент on 02.08.2016.
 */
public class MyShutdownHook extends Thread{

    PrintWriter out;

    public MyShutdownHook(PrintWriter out) {
        this.out = out;
    }

    public void run() {
        Send.sendDisconnection();
    }


}
