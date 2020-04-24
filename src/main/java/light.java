import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class light {


    private static BufferedImage img;
    private static ArrayList<BufferedImage> imgList=new ArrayList<>();
    private static double situation=1;
    public static boolean light=true;
    public static int Vision=Player.visionLength;
    static {
        for(int i=1;i<6;i++){
            try {
                img = ImageIO.read(new File("src/main/resources/light/light1"+i+".png"));
                imgList.add(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static BufferedImage paintLight(){
        BufferedImage imgR= new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
        if(KeyBoard.getInstance().isKeyDownFirst(69)){
            light=!light;
        }
        if(light) {
            Player.visionLength=Vision;
            imgR = imgList.get(new Random().nextInt(5));
        }else {
            Player.visionLength=Vision/100;
        }
        return imgR;
    }
}
