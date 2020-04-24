import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.parsers.FactoryConfigurationError;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Program  {


    public static void main(String[] args) {
        window();
    }

    public static void window(){
        JFrame w = new JFrame();
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        w.setResizable(false);
        w.setUndecorated(true);
        GamePanel p =new GamePanel();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Point hotSpot = new Point(0,0);
        BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT);
        Cursor invisibleCursor = toolkit.createCustomCursor(cursorImage, hotSpot, "InvisibleCursor");
        p.setCursor(invisibleCursor);

        try {
            Image i = ImageIO.read(new File("src/main/resources/icon.png"));
            w.setIconImage(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        w.add(p);
        w.setVisible(true);
        w.addKeyListener(KeyBoard.getInstance());
        w.addMouseWheelListener(KeyBoard.getInstance());
        w.addMouseMotionListener(KeyBoard.getInstance());

    }
}
