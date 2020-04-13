package ca.tayjay.javaremoteapp;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.zip.GZIPInputStream;

public class JRAServer {
    public static void main(String[] args) throws IOException {
        ServerSocketFactory ssf = ServerSocketFactory.getDefault();
        ServerSocket ss = ssf.createServerSocket(6969);

        System.out.println("Ready...");
        while (true) {
            new JRAServer(ss.accept()).run();
            System.out.println("Connected...");
        }

    }

    private Socket socket;

    public JRAServer(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            GZIPInputStream zip = new GZIPInputStream(socket.getInputStream());
            while (true) {
                int c;
                c = zip.read();
                if (c == -1)
                    break;
                System.out.print((char) c);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
