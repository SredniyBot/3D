import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Player  extends Thread{
    public  double x=50,y=50,w=30,h=30;
    public  double a=0;
    public  static int visionLength =200,//length radius
            visionAngle =150;//watchable positions
    public  double accuracy=720;//number of rays per round
    public  double saturation=360/accuracy;//step of angle for sircle
    public  RoundRectangle2D.Double Hero;
    public  HashMap<Double,Integer> destinations = new HashMap<>();//
    public  HashMap<Double,Shape> boundRays = new HashMap<>();//map<angle,ray comes to 90* angle on map's object>
    public  HashMap<Double,Point> points=new HashMap<>();
    public  static double deltaX;

    Player(int x,int y,int a){
        this.x=x;
        this.y=y;
        this.a=a;
        Hero=new RoundRectangle2D.Double(x-w/2,y-h/2,w,h,w,h);
            PlayerVision pv = new PlayerVision(this);
            pv.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //getObjectVision();
    }


    public  void update(){
        KeyBoard.getInstance().update();
        a+=KeyBoard.getInstance().getDeltaX()/4;
        while (a<0){
            a+=360;
        }
        while (a>=360){
            a-=360;
        }

        double trueA=a+visionAngle/2;
        double cos = Math.cos(Math.toRadians(trueA + 90));
        double sin = Math.sin(Math.toRadians(trueA+90));
        if(KeyBoard.getInstance().isKeyDown(68)){
            y+=sin;
            x+=cos;
            deltaX+=5;
        }else if(KeyBoard.getInstance().isKeyDown(65)){
            y-=sin;
            x-= cos;deltaX+=5;
        }else //walk
        if(KeyBoard.getInstance().isKeyDown(87)){
            y+=Math.sin(Math.toRadians(trueA));
            x+=Math.cos(Math.toRadians(trueA));
            deltaX+=5;
        }else if(KeyBoard.getInstance().isKeyDown(83)){
            y-=Math.sin(Math.toRadians(trueA));
            x-=Math.cos(Math.toRadians(trueA));
            deltaX+=5;
        }else {
            deltaX+=0;
        }

        Hero=new RoundRectangle2D.Double(x-w/2,y-h/2,w,h,w,h);

    }




    int o=0;
    Color c = new Color(237, 101, 31);
    public  void paint3d(Graphics g){
        o++;
        if(o>new Random().nextInt(30)){
            c = new Color(237-new Random().nextInt(20), 101-new Random().nextInt(20), 31-new Random().nextInt(20));
            o=0;
        }
        g.setColor(new Color(75,88,1));
        for(double i = 0; i< visionAngle-saturation; i+=saturation){

//            System.out.println(a+i);//destinations.get(a+i)
            int length;
            if(a+i>=360){
                length=destinations.get(a+i-360)+1;
            }else{
                length=destinations.get(a+i)+1;
            }

            if(length<visionLength){

            g.setColor(new Color(c.getRed()-map(length,0,visionLength,0,c.getRed()),
                    c.getGreen()-map(length,0,visionLength,0,c.getGreen()),
                    c.getBlue()-map(length,0,visionLength,0,c.getBlue())));
                g.fillRect((int)(i*GamePanel.SizeX/visionAngle),GamePanel.SizeY/2-(GamePanel.SizeY*20)/length/2,//map(visionDistanse.get(i),0,visionLength,0,50)/2
                        (int)(GamePanel.SizeX/visionAngle*saturation+1),(GamePanel.SizeY*20)/length);//map(visionDistanse.get(i),0,visionLength,0,50)
                g.setColor(Color.BLACK);
            }
        }
    }

    public  void paint(Graphics g2){
        Graphics2D g =(Graphics2D) g2.create();

        for(double i =0;i<360;i+=saturation){
            if(i>a&&i<a+visionAngle||i<a-360+visionAngle){
                g.setColor(new Color(155,138,94));
            }else{
                g.setColor(new Color(116,139,71));
            }
            g.drawLine((int)x,(int)y,points.get(i).x,points.get(i).y);
            g.drawString(String.valueOf(Math.sqrt((x-points.get(i).x)*(x-points.get(i).x)+(y-points.get(i).y)*(y-points.get(i).y))),1000,100);
        }
        g.setColor(new Color(255, 114, 61));
        g.fill(Hero);

    }



    @Override
    public void run() {

//        PlayerVision pv = new PlayerVision(this,i,90);
//        while(true){
//            getObjectVision();
//        }
    }

//    public  void getObjectVision(){
//        for(double grad=0;grad<360;grad+=saturation){
//            for(int length=0;length<visionLength;length++){
//                boolean found=false;
//                for (Shape s:Map.objects){
//                    if(s.contains(getRay(grad,length))){
//                        found=true;
//                        if(toBound(s,getRay(grad,length))<10){
//                            boundRays.put(grad,s);
//                        }
//                        points.put(grad,getRay(grad,length));
//                        destinations.put(grad,length);
//                        break;
//                    }
//
//                }
//                if (found) {
//                    break;
//                }else{
//                    points.put(grad,getRay(grad,length));
//                    destinations.put(grad,length);
//                }
//            }
//        }
//    }















//    public static ArrayList<Point> getObjectVision(){
//        ArrayList<ArrayList<Point>> vision = getVision();
//        for(int listNumber=0;listNumber<vision.size();listNumber++){
//            ArrayList<Point> list=vision.get(listNumber);
//            for(int point=list.size()-1;point>=0;point--){
//                for(Shape s:Map.objects){
//                    if(s.contains(list.get(point))){
//                       while (list.size()!=point+1){
//                           list.remove(point+1);
//                       }
//                    }
//                }
//            }
//            vision.remove(listNumber);
//            vision.add(listNumber,list);
//        }
//        ArrayList<Point> lengths = new ArrayList<>();
//        for(int listNumber=0;listNumber<vision.size();listNumber++) {
//            lengths.add(vision.get(listNumber).get(vision.get(listNumber).size()-1));
//        }
//        return lengths;
//    }


//    public static ArrayList<ArrayList<Point>> getVision(){
//        ArrayList<ArrayList<Point>>  view = new ArrayList<>();
//        for(int grad=0;grad<360;grad++) {
//            ArrayList<Point> points=new ArrayList<>();
//            for (int l = 1; l < visionLength; l++) {
//                points.add(new Point((int)(x+Math.cos(Math.toRadians(grad))*l),(int)(y+Math.sin(Math.toRadians(grad))*l)));
//            }
//            view.add(points);
//        }
//        return view;
//    }
    public static int toBound(Shape s,Point p){
    int b1=(int)Math.sqrt((s.getBounds().x-p.x)*(s.getBounds().x-p.x)+(s.getBounds().y-p.y)*(s.getBounds().y-p.y));
    int b2=(int)Math.sqrt((s.getBounds().x+s.getBounds().width-p.x)*(s.getBounds().x+s.getBounds().width-p.x)+(s.getBounds().y-p.y)*(s.getBounds().y-p.y));
    int b3=(int)Math.sqrt((s.getBounds().x-p.x)*(s.getBounds().x-p.x)+(s.getBounds().y+s.getBounds().height-p.y)*(s.getBounds().y+s.getBounds().height-p.y));
    int b4=(int)Math.sqrt((s.getBounds().x+s.getBounds().width-p.x)*(s.getBounds().x+s.getBounds().width-p.x)+
            (s.getBounds().y+s.getBounds().height-p.y)*(s.getBounds().y+s.getBounds().height-p.y));
    return Math.min(Math.min(b3,b4),Math.min(b1,b2));
}


    int map(int x, int in_min, int in_max, int out_min, int out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

}
