import java.awt.*;
import java.util.ArrayList;

public class Player  extends Thread{
    public static double x=50,y=50,w=30,h=30;
    public static double a=0;
    public static final int visionLength =200;
    public static final int visionAngle =180;

    public static ArrayList<Point> points=new ArrayList<>();
    public static ArrayList<Integer> visionDistanse =new ArrayList<>();

    Player(int x,int y,int a){
        this.x=x;
        this.y=y;
        this.a=a;
    }


    public  void update(){
        a+=KeyBoard.getInstance().getDeltaX()/4;
        if(a<0){
            a=360-a;
        }else if(a>360){
            a=a-360;
        }

        double trueA=a+visionAngle/2;
        if(KeyBoard.getInstance().isKeyDown(68)){
            y+=Math.sin(Math.toRadians(trueA+90));
            x+=Math.cos(Math.toRadians(trueA+90));
        }else
        if(KeyBoard.getInstance().isKeyDown(65)){
            y-=Math.sin(Math.toRadians(trueA+90));
            x-=Math.cos(Math.toRadians(trueA+90));

        }else

        if(KeyBoard.getInstance().isKeyDown(87)){

            y+=Math.sin(Math.toRadians(trueA));
            x+=Math.cos(Math.toRadians(trueA));
        }else
        if(KeyBoard.getInstance().isKeyDown(83)){
            y-=Math.sin(Math.toRadians(trueA));
            x-=Math.cos(Math.toRadians(trueA));
        }
    }

    public  void paint(Graphics g){
        g.drawString(String.valueOf(Math.toRadians(Math.sin(a+visionAngle/2))),1000,100);
        for(int i =0;i<points.size();i++){
            if(i>a&&i<a+visionAngle||i<a-360+visionAngle){
                g.setColor(new Color(155,138,94));
            }else{
                g.setColor(new Color(116,139,71));
            }
            g.drawLine((int)x,(int)y,points.get(i).x,points.get(i).y);
        }
        g.setColor(new Color(255, 114, 61));
        g.fillOval((int)(x-w/2),(int)(y-w/2),(int)w,(int)h);
    }

    @Override
    public void run() {
        while(true){
            points = getObjectVision();
            ArrayList<Integer> vd = new ArrayList<>();
            for(int i=0;i<visionAngle;i++){
                Point p;
                if(a+i>=360){
                    p =points.get((int)a+i-360);
                }else{
                    p =points.get((int)a+i);
                }
                vd.add((int)Math.sqrt((x-p.x)*(x-p.x)+(y-p.y)*(y-p.y)));
            }
            visionDistanse =vd;
        }
    }

    public  void paint3d(Graphics g){
        g.setColor(new Color(75,88,1));
        for(int i = 0; i< visionDistanse.size(); i++){
            if(visionDistanse.get(i)<visionLength-10){
            g.setColor(new Color(255-map(visionDistanse.get(i).intValue(),0,visionLength,0,255),
                    255-map(visionDistanse.get(i).intValue(),0,visionLength,0,255),
                    255-map(visionDistanse.get(i).intValue(),0,visionLength,0,255)));
                g.fillRect(i*GamePanel.SizeX/visionAngle,GamePanel.SizeY/2-(GamePanel.SizeY*20)/visionDistanse.get(i)/2,//map(visionDistanse.get(i),0,visionLength,0,50)/2
                        GamePanel.SizeX/visionAngle+1,(GamePanel.SizeY*20)/visionDistanse.get(i));//map(visionDistanse.get(i),0,visionLength,0,50)
            }
        }
    }




    public static ArrayList<Point> getObjectVision(){
        ArrayList<ArrayList<Point>> vision = getVision();
        for(int listNumber=0;listNumber<vision.size();listNumber++){
            ArrayList<Point> list=vision.get(listNumber);
            for(int point=list.size()-1;point>=0;point--){
                for(Shape s:Map.objects){
                    if(s.contains(list.get(point))){
                       while (list.size()!=point+1){
                           list.remove(point+1);
                       }
                    }
                }
            }
            vision.remove(listNumber);
            vision.add(listNumber,list);
        }
        ArrayList<Point> lengths = new ArrayList<>();
        for(int listNumber=0;listNumber<vision.size();listNumber++) {
            lengths.add(vision.get(listNumber).get(vision.get(listNumber).size()-1));
        }
        return lengths;
    }


    public static ArrayList<ArrayList<Point>> getVision(){
        ArrayList<ArrayList<Point>>  view = new ArrayList<>();
        for(int grad=0;grad<360;grad++) {
            ArrayList<Point> points=new ArrayList<>();
            for (int l = 1; l < visionLength; l++) {
                points.add(new Point((int)(x+Math.cos(Math.toRadians(grad))*l),(int)(y+Math.sin(Math.toRadians(grad))*l)));
            }
            view.add(points);
        }
        return view;
    }

    int map(int x, int in_min, int in_max, int out_min, int out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

}
