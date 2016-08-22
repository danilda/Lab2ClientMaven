package model;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import view.Main;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.security.AccessControlContext;
import java.security.AccessController;


import static java.util.Objects.isNull;

/**
 * Created by User on 15.07.2016.
 */
public class Distribut extends Thread {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    public Distribut(){
        try {
            this.socket = new Socket("localhost", 4444);
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out =  new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            MyShutdownHook myShutdownHook = new MyShutdownHook(out);
            Runtime.getRuntime().addShutdownHook(myShutdownHook);
            if(socket != null) {
                in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                out = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String give() {
        String str = "";
        try {
            str = in.readLine();
        } catch (SocketException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                Main.getLog().error(e1.getMessage());
            }
        } catch (IOException  e) {
            Main.getLog().error(e.getMessage());
        }
        return str;
    }

    @Override
    public void run() {

        while (!this.socket.isClosed()) {
            String str;
            String result = "";
            do {
                str = give();
                result += str + "\n";
            }while(!str.equals("</body>"));

            if(!isNull(result)) {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = null;
                try {
                    Main.getLog().info(result);
                    saxParser = factory.newSAXParser();
                    SaxHandler handler = new SaxHandler();
                    InputSource is = new InputSource(new StringReader(result));
                    saxParser.parse(is, handler);
                    Main.getLog().info(handler.getResult());
                    Parser.callDoer(handler.getResult());
                } catch (IOException e) {
                    Main.getLog().error(e.getMessage());
                }catch (ParserConfigurationException e) {
                    Main.getLog().error(e.getMessage());
                } catch (SAXException e) {
                    Main.getLog().error(e.getMessage());
                }
            }
        }
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }
}
