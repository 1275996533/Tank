package bean;

import config.Audio;
import config.Dir;
import config.Group;
import config.imageManager;

import java.awt.*;
import java.util.Random;

/**
 * @ClassName Tank
 * @Description TODO
 * @Author 黄政杰
 * @Date 2020/6/27 7:28
 * @Version 1.0
 **/
public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private boolean moving = true;
    private boolean living = true;
    private TankFrame tankFrame;
    private Group group;
    private Random random = new Random();

    private static final int SPEED = 5;
    public static final int WIDTH = imageManager.goodTankD.getWidth();
    public static final int HEIGHT = imageManager.goodTankD.getHeight();


    public void paint(Graphics g) {
        if (!living) {
            tankFrame.tanks.remove(this);
        }
        switch (dir) {
            case UP:
                g.drawImage(group == Group.BAD ? imageManager.badTankU : imageManager.goodTankU, x, y, null);
                break;
            case LEFT:
                g.drawImage(group == Group.BAD ? imageManager.badTankL : imageManager.goodTankL, x, y, null);
                break;
            case DOWN:
                g.drawImage(group == Group.BAD ? imageManager.badTankD : imageManager.goodTankD, x, y, null);
                break;
            case RIGHT:
                g.drawImage(group == Group.BAD ? imageManager.badTankR : imageManager.goodTankR, x, y, null);
                break;
        }

        move();

    }

    public void move() {

        if (!moving) return;

        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
        }
        if (this.group == Group.BAD && random.nextInt(100) > 95)
            fire();
        if (this.group == Group.BAD && random.nextInt(100) > 95)
            randomDir();
//        if(this.group == Group.GOOD) new Thread(()->new Audio("audio/tank_move.wav").play()).start();
        checkBounds();
    }

    private void checkBounds() {
        if (x < 2) x = 2;
        if (y < 28) y = 28;
        if (y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
        if (x > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tankFrame.bullets.add(new Bullet(bX, bY, dir, tankFrame, group));
        if (this.group == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
    public void explode(Tank tank) {
        int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
        int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
        tankFrame.explodes.add(new Explode(eX, eY, tankFrame));
    }


    public Tank(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void die() {
        setLiving(false);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
