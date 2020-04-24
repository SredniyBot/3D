import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class PlayerVision extends Thread{

    public Player p;
    //double start,lengthA;
    PlayerVision(Player p ){
        this.p=p;
        //this.start=start;
        //this.lengthA=lengthA;
    }

    @Override
    public void run() {
        while (true) {
            getObjectVision();//start, lengthA
        }
    }

    public void getObjectVision(){//double start,double lengthA
        int trueLength;
        HashMap<Double,Integer> destinations=new HashMap<>();
        HashMap<Double,Point> points=new HashMap<>();

        for(double grad=0;grad<360;grad+=p.saturation){
            if(grad>p.a-20&&grad<p.a+p.visionAngle+20||grad<p.a-360+p.visionAngle+20){
                trueLength=p.visionLength ;
            }else{
                trueLength=20;
            }
            for(int length=0;length<trueLength;length+=1){
                boolean found=false;
                for (Shape s:Map.objects){
                    if(s.contains(getRay(grad,length))){
                        found=true;
                        if(p.toBound(s,getRay(grad,length))<10){
                            p.boundRays.put(grad,s);
                        }
                        points.put(grad,getRay(grad,length));
                        destinations.put(grad,length);
                        break;
                    }
                }
                if (found) {
                    break;
                }else if(length==trueLength-1){
                    points.put(grad,getRay(grad,length));
                    destinations.put(grad,length);
                }
            }
        }
        p.points=points;
        p.destinations=destinations;
    }

//    public void getObjectVision(double start,double lengthA){
//        for(double grad=start;grad<start+lengthA;grad+=p.saturation){
//            for(int length=0;length<p.visionLength;length++){
//                boolean found=false;
//                for (Shape s:Map.objects){
//                    if(s.contains(getRay(grad,length))){
//                        found=true;
//                        if(p.toBound(s,getRay(grad,length))<10){
//                            p.boundRays.put(grad,s);
//                        }
//                        p.points.put(grad,getRay(grad,length));
//                        p.destinations.put(grad,length);
//                        break;
//                    }
//
//                }
//                if (found) {
//                    break;
//                }else{
//                    p.points.put(grad,getRay(grad,length));
//                    p.destinations.put(grad,length);
//                }
//            }
//        }
//    }
    public  Point getRay(double grad,int l){
        return new Point((int)(p.x+Math.cos(Math.toRadians(grad))*l),(int)(p.y+Math.sin(Math.toRadians(grad))*l));
    }




}
