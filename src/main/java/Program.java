import javax.swing.*;
import javax.xml.parsers.FactoryConfigurationError;
import java.awt.*;

public class Program  {


    public static void main(String[] args) {
        window();
    }

    public static void window(){
        JFrame w = new JFrame();
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        w.setResizable(false);
        GamePanel p =new GamePanel();
        Cursor c = new Cursor(Cursor.MOVE_CURSOR);
        p.setCursor(c);
        w.add(p);
        w.setVisible(true);
        w.addKeyListener(KeyBoard.getInstance());
        w.addMouseMotionListener(KeyBoard.getInstance());

    }
}
