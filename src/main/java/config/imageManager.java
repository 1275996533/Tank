package config;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @ClassName imageManager
 * @Description TODO
 * @Author 黄政杰
 * @Date 2020/6/27 10:00
 * @Version 1.0
 **/
public class imageManager {
    public static BufferedImage goodTankU,goodTankL,goodTankD,goodTankR,
                                badTankU,badTankL,badTankD,badTankR,
                          bulletU,bulletL,bulletD,bulletR;
    public static BufferedImage[] explode = new BufferedImage[16];
    static {
        try {
            goodTankU = ImageIO.read(imageManager.class.getClassLoader().getResourceAsStream("imgs/GoodTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU,-90);
            goodTankR = ImageUtil.rotateImage(goodTankU,90);
            goodTankD = ImageUtil.rotateImage(goodTankU,180);

            badTankU = ImageIO.read(imageManager.class.getClassLoader().getResourceAsStream("imgs/badTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU,-90);
            badTankR = ImageUtil.rotateImage(badTankU,90);
            badTankD = ImageUtil.rotateImage(badTankU,180);

            bulletU = ImageIO.read(imageManager.class.getClassLoader().getResourceAsStream("imgs/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU,-90);
            bulletR = ImageUtil.rotateImage(bulletU,90);
            bulletD = ImageUtil.rotateImage(bulletU,180);




            for (int i = 0; i < explode.length; i++) {
                explode[i] = ImageIO.read(imageManager.class.getClassLoader().getResourceAsStream("imgs/e" + (i+1) + ".gif"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
