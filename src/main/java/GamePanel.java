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
        g.fillRect(0,0,SizeX,SizeY);
        PlayerList.update();
        PlayerList.paint3d(g);
        g.drawImage(Book.paintBook(),(int)(-SizeX/9+Math.sin(Math.toRadians(Player.deltaX))*10),(int)(SizeY*2/3+Math.cos(Math.toRadians(Player.deltaX))*10),SizeX*4/9,SizeY*4/9,null);
        g.drawImage(light.paintLight(),(int)(SizeX*8/9+Math.cos(Math.toRadians(Player.deltaX))*10),(int)(SizeY*2/3+Math.cos(Math.toRadians(Player.deltaX))*10),SizeX/9,SizeY*4/9,null);

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
