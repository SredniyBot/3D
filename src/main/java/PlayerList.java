import java.awt.*;
import java.util.ArrayList;

public class PlayerList {

    public static ArrayList<Player> list = new ArrayList<>();

    static{
        Player p = new Player(100,100,0);
        p.start();
        list.add(p);
    }

    public static void paintOnMap(Graphics g){
        for(Player p :list){
            p.paint(g);
        }
    }

    public static void update(){
        for(Player p: list){
            p.update();
        }
    }
    public static void paint3d(Graphics g){
        for(Player p : list){
            p.paint3d(g);
        }
    }

}
