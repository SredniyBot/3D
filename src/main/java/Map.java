import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class Map {

    public static ArrayList<Shape> objects=new ArrayList<>();

    static {
        Shape r1 = new Rectangle(0,0,GamePanel.SizeX,50);
        Shape r2 = new Rectangle(0,0,50,GamePanel.SizeY);
        Shape r3 = new Rectangle(GamePanel.SizeX-50,0,50,GamePanel.SizeY);
        Shape r4 = new Rectangle(0,GamePanel.SizeY-50,GamePanel.SizeX,50);
        Shape r5 = new Rectangle(300,100,100,20);
        Shape r6 = new RoundRectangle2D.Float(200,200,30,200,10,10);
        Shape r7 = new RoundRectangle2D.Float(500,500,30,200,10,10);
        objects.add(r1);
        objects.add(r2);
        objects.add(r3);
        objects.add(r4);
        objects.add(r5);
        objects.add(r6);
        objects.add(r7);

    }

    


    public static void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(new Color(156,190,182));
        g2.fillRect(0,0,GamePanel.SizeX,GamePanel.SizeY);
        g2.setColor(new Color(75,88,1));
        for(Shape s :objects){
            g2.fill(s);
        }
        PlayerList.paintOnMap(g);

    }



}
