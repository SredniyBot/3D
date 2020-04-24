import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Obj {
    public int x, y, w, h,ww,hh;
    private RoundRectangle2D.Double rect;
    Obj(int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.rect=new RoundRectangle2D.Double(x,y,w,h,0,0);
    }
    Obj(int x,int y,int w,int h,int ww,int hh){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.ww=ww;
        this.hh=hh;
        this.rect=new RoundRectangle2D.Double(x,y,w,h,ww,hh);
    }
    public Shape getShape(){
        return rect;
    }


}
