package ca.tayjay.javaremoteapp.ui;

import ca.tayjay.javaremoteapp.util.ImageCapture;
import com.sun.jna.platform.WindowUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

public class OutputWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");

        StackPane sp = new StackPane();
        try {
            sp.getChildren().add(screenshot());
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(sp);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    protected ImageView screenshot() throws AWTException, IOException {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        Rectangle windowRect = new Rectangle(0, 0, 0, 0);
        WindowUtils.getAllWindows(false).forEach(desktopWindow -> {
            if (desktopWindow.getTitle().contains("Notepad")) {

                windowRect.setRect(desktopWindow.getLocAndSize());
            }
        });
        BufferedImage captureFull = new Robot().createScreenCapture(windowRect);
        BufferedImage capture = scaleImage(captureFull);
        ImageView imageView = new ImageView();
        Image image = convertToFxImage(captureFull);
        imageView.setImage(image);

        return imageView;
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
