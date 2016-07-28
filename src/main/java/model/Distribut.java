package model;

import org.xml.sax.SAXException;
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
    public Distribut(Socket socket){
        this.socket = socket;
        if(socket != null)
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out =  new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } catch (IOException e) {
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
                e1.printStackTrace();
            }
        } catch (IOException  e) {
            e.printStackTrace();
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
                    FileWriter writer = new FileWriter("src//main//resources//xml//ForParse"+result.hashCode()+".xml", false);
                    System.out.println(result);
                    writer.write(result);
                    writer.flush();
                    InputStream xmlInput = new FileInputStream( "src//main//resources//xml//ForParse"+result.hashCode()+".xml" );
                    saxParser = factory.newSAXParser();
                    SaxHandler handler = new SaxHandler();
                    saxParser.parse(xmlInput, handler);
                    writer.close();
                    new File("src//main//resources//xml//ForParse"+result.hashCode()+".xml" ).delete();
                    System.out.println(handler.getResult());
                    Parser.callDoer(handler.getResult());
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
