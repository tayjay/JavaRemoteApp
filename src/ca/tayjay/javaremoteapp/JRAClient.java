package ca.tayjay.javaremoteapp;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.zip.GZIPOutputStream;

public class JRAClient {
    public static void main(String[] args) throws IOException {
        SocketFactory ssf = SocketFactory.getDefault();
        Socket ss = ssf.createSocket("127.0.0.1",6969);
        GZIPOutputStream zip = new GZIPOutputStream(ss.getOutputStream());
        String line = "Hello World!\n";
        zip.write(line.getBytes(), 0, line.length());
        zip.finish();
        zip.close();
        ss.close();

    }


}
