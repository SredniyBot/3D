import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class KeyBoard implements KeyListener, MouseMotionListener {
    private static int oldPositionX = -1,oldPositionY=-1,deltaX;
    private static boolean[] KeyState = new boolean[256];
    static KeyBoard obj = null;
    public static KeyBoard getInstance(){
        if(obj==null){
            obj=new KeyBoard();
        }
        return obj;
    }


    public  int getDeltaX() {
        int l=deltaX;
        deltaX=0;
        return l;
    }

    public boolean isKeyDown (int code) {
        return KeyState[code];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        KeyState[e.getKeyCode()]=true;
        if(e.getKeyCode()==27){
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        KeyState[e.getKeyCode()]=false;
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        deltaX = e.getX() - GamePanel.SizeX/2;
            try {
                Robot r = new Robot();
                r.mouseMove(GamePanel.SizeX/2,GamePanel.SizeY/2);

            } catch (AWTException ex) {
                ex.printStackTrace();
            }



    }
}
