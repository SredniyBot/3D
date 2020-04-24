import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Book {

    public static boolean left=false,right=false;
    public static int situation=1;
    private static BufferedImage img;
    private static ArrayList<BufferedImage> imgList=new ArrayList<>();
    static {
        try {
            img = ImageIO.read(new File("src/main/resources/book/book-list"+situation+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=1;i<8;i++){
            try {
                img = ImageIO.read(new File("src/main/resources/book/book-list"+i+".png"));
                imgList.add(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static BufferedImage paintBook(){
        BufferedImage imgR= img;
        int wheel=KeyBoard.getInstance().Wheel();
        if(right){
            imgR=imgList.get(situation-1);
            situation--;
            if(situation==0){
                right=false;
            }
        }else if(left){
            imgR=imgList.get(situation-1);
            System.out.println(situation);
            situation++;
            if(situation==8){
                left=false;
            }
        }else if(wheel==1){
            left=true;
            situation=1;
        }else if(wheel==-1){
            System.out.println(2);
            right=true;
            situation=7;
        }

        return imgR;
    }

}

