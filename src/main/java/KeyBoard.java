import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class KeyBoard implements KeyListener, MouseMotionListener, MouseWheelListener {
    private static int oldPositionX = -1,oldPositionY=-1,deltaX;
    private static boolean[] KeyState = new boolean[256];
    boolean[] oldKeyState = new boolean[256];
    private static int wm;
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
    public boolean isKeyDownFirst (int code) {
        return KeyState[code] && !oldKeyState[code];
    }

    public boolean isKeyDown (int code) {
        return KeyState[code];
    }

    public void update () {
        oldKeyState = Arrays.copyOf(KeyState, KeyState.length);
    }

    public int Wheel(){
        int s=0;
        if(wm!=0){
        s=wm;
        wm=0;
        }
        return s;
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
        deltaX = e.getX() - GamePanel.SizeX/2;
        try {
            Robot r = new Robot();
            r.mouseMove(GamePanel.SizeX/2,GamePanel.SizeY/2);

        } catch (AWTException ex) {
            ex.printStackTrace();
        }

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

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        wm=e.getWheelRotation();

    }
}
