package ca.tayjay.javaremoteapp;



import ca.tayjay.javaremoteapp.util.GDI32Extra;
import ca.tayjay.javaremoteapp.util.ImageCapture;
import ca.tayjay.javaremoteapp.util.User32Extra;
import com.sun.jna.platform.DesktopWindow;
import com.sun.jna.platform.WindowUtils;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;


public class JavaRemoteApp {
    public static void main(String[] args) {

        try{
            captureScreen();
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*WinDef.HWND hWnd = User32.INSTANCE.FindWindow(null, "Task Manager");
        BufferedImage bufferedImage = ImageCapture.capture(hWnd);

        File outputfile = new File("C:\\temp\\image.jpg");
        try {
            ImageIO.write(bufferedImage, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*List<DesktopWindow> list = WindowUtils.getAllWindows(true);
        for (DesktopWindow window : list) {
            System.out.println(window.getTitle());
        }*/
    }

    public static void captureScreen() throws AWTException, IOException {
        /*WinDef.HWND hWnd = User32.INSTANCE.FindWindow(null, "Task Manager");
        WinDef.HDC hdcWindow = User32Extra.INSTANCE.GetWindowDC(hWnd);
        WinDef.HDC hdcMemDC = GDI32Extra.INSTANCE.CreateCompatibleDC(hdcWindow);

        WinDef.RECT bounds = new WinDef.RECT();
        User32Extra.INSTANCE.GetClientRect(hWnd, bounds);*/

        Robot robot = new Robot();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Rectangle screenbounds = new Rectangle(toolkit.getScreenSize());

        BufferedImage image = robot.createScreenCapture(screenbounds);
        ImageIO.write(image, "png", new File("C:\\temp\\image.png"));


    }


}
