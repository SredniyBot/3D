import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class Map {

    public static ArrayList<Shape> objects=new ArrayList<>();

    static {
        Shape r1 = new Rectangle(10,30,50,50);
        Shape r2 = new Rectangle(300,80,100,20);
        Shape r3 = new RoundRectangle2D.Float(200,200,100,1000,10,10);
        objects.add(r1);
        objects.add(r2);
        objects.add(r3);
    }

    public static void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(new Color(156,190,182));
        g2.fillRect(0,0,1000,1000);
        g2.setColor(new Color(75,88,1));
        for(Shape s :objects){
            g2.fill(s);
        }
        PlayerList.paintOnMap(g);

    }



}
