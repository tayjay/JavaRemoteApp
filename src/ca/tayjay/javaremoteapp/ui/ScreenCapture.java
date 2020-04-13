package ca.tayjay.javaremoteapp.ui;

import com.sun.jna.platform.WindowUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ScreenCapture extends JFrame{
    private JPanel panel1;
    private JTextField textField1;
    private JButton captureButton;
    private JScrollPane scrollPane;
    private JLabel imgLabel;

    public ScreenCapture() {
        super("ScreenCapture");
        setSize(1000, 1000);
        setContentPane(panel1);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        captureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    screenshot();
                } catch (AWTException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    protected void screenshot() throws AWTException, IOException {
        Rectangle windowRect;
        if("".equals(textField1.getText()))
        {
            windowRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        } else {
            windowRect = new Rectangle(0, 0, 0, 0);
            WindowUtils.getAllWindows(false).forEach(desktopWindow -> {
                if (desktopWindow.getTitle().contains(textField1.getText())) {
                    windowRect.setRect(desktopWindow.getLocAndSize());
                }
            });
        }

        Runnable task = () -> {
            for (int i = 0; i < 300; i++) {
                BufferedImage captureFull = null;
                try {
                    captureFull = new Robot().createScreenCapture(windowRect);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
                BufferedImage capture = scaleImage(captureFull);

                imgLabel.setIcon(new ImageIcon(capture));
                scrollPane.repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread th = new Thread(task);
        th.start();



    }

    protected BufferedImage scaleImage(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        int newW = w / 2;
        int newH = h / 2;
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }

    private static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }
}
