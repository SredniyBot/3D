import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    public static int SizeX=Toolkit.getDefaultToolkit().getScreenSize().width,SizeY=
                Toolkit.getDefaultToolkit().getScreenSize().height;

    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,10000,1000);
        PlayerList.update();
        PlayerList.paint3d(g);
        if(KeyBoard.getInstance().isKeyDown(77)){
            g.drawImage(paintMap(),0,0,SizeX,SizeY,null);
        }else{
            g.drawImage(paintMap(),10,10,SizeX/5,SizeY/5,null);
        }

        repaint();
    }


    public static BufferedImage paintMap(){
        BufferedImage img = new BufferedImage(SizeX,SizeY,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D)img.createGraphics();
        Map.paint(g);
        return img;
    }

}
