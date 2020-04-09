package ca.tayjay.javaremoteapp;



import ca.tayjay.javaremoteapp.util.ImageCapture;
import com.sun.jna.platform.DesktopWindow;
import com.sun.jna.platform.WindowUtils;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class JavaRemoteApp {
    public static void main(String[] args) {
        WinDef.HWND hWnd = User32.INSTANCE.FindWindow(null, "Twitch");
        BufferedImage bufferedImage = ImageCapture.capture(hWnd);

        File outputfile = new File("C:\\temp\\image.jpg");
        try {
            ImageIO.write(bufferedImage, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*List<DesktopWindow> list = WindowUtils.getAllWindows(true);
        for (DesktopWindow window : list) {
            System.out.println(window.getTitle());
        }*/
    }


}
