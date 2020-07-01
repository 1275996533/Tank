package bean;

import config.Dir;
import config.Group;
import config.imageManager;

import java.awt.*;

/**
 * @ClassName Bullet
 * @Description TODO
 * @Author 黄政杰
 * @Date 2020/6/27 7:57
 * @Version 1.0
 **/
public class Bullet {
    private Integer x,y;
    private Dir dir;
    private TankFrame tankFrame;
    private boolean live = true;
    private Group group;



    private static final int SPEED = 10;
    public static final int WIDTH = imageManager.bulletD.getWidth();
    public static final int HEIGHT = imageManager.bulletD.getHeight();



    public void paint(Graphics g){
        if (!live){
            tankFrame.bullets.remove(this);
            return;
        }
        switch (dir){
            case UP:
                g.drawImage(imageManager.bulletU, x, y, null);
                break;
            case LEFT:
                g.drawImage(imageManager.bulletL, x, y, null);
                break;
            case DOWN:
                g.drawImage(imageManager.bulletD, x, y, null);
                break;
            case RIGHT:
                g.drawImage(imageManager.bulletR, x, y, null);
                break;
        }
        move();
    }
    public void move(){
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
        live();
    }
    public void live(){
        if (x < 0 || y<0 || x > tankFrame.GAME_WIDTH || y>tankFrame.GAME_HEIGHT){
            this.setLive(false);
        }
    }
    public void remove(){
        tankFrame.bullets.remove(this);
    }

    public void estimateLive(Tank tank) {
        if (group == tank.getGroup()) return;
        Rectangle rectangleBullet = new Rectangle(x,y,WIDTH,HEIGHT);
        Rectangle rectangleTank = new Rectangle(tank.getX(),tank.getY(),tank.WIDTH,tank.HEIGHT);
        boolean intersects = rectangleBullet.intersects(rectangleTank);
        if (intersects){
            this.die();
            tank.die();
            tank.explode(tank);
        }
    }
    public Bullet(Integer x, Integer y, Dir dir,TankFrame tankFrame,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
    }
    private void die() {
        setLive(false);
    }









    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
