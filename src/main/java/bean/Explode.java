package bean;

import config.Audio;
import config.Dir;
import config.imageManager;

import java.awt.*;

/**
 * @ClassName Explode
 * @Description TODO
 * @Author 黄政杰
 * @Date 2020/6/27 17:00
 * @Version 1.0
 **/
public class Explode {
    private Integer x,y;
    private TankFrame tankFrame;
    private int count;
    public static final int WIDTH = imageManager.explode[0].getWidth();
    public static final int HEIGHT = imageManager.explode[0].getHeight();

    public Explode(Integer x, Integer y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g){
        if (count>=15) tankFrame.explodes.remove(this);
        g.drawImage(imageManager.explode[count++],x,y,null);

    }
}
