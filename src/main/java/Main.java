import bean.Tank;
import bean.TankFrame;
import config.Audio;
import config.Dir;
import config.Group;

/**
 * @ClassName Main
 * @Description TODO
 * @Author 黄政杰
 * @Date 2020/6/27 5:42
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        for (int i = 0; i < 5; i++) {
            tankFrame.tanks.add(new Tank(i*80, 50, Dir.DOWN,tankFrame, Group.BAD));
        }

        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
