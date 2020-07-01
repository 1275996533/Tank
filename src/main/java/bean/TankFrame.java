package bean;

import bean.Bullet;
import bean.Tank;
import config.Dir;
import config.Group;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TankFream
 * @Description TODO
 * @Author 黄政杰
 * @Date 2020/6/27 5:38
 * @Version 1.0
 **/
public class TankFrame extends Frame {
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    public List<Bullet> bullets = new ArrayList<>();
    public List<Tank> tanks = new ArrayList<>();
    public List<Explode> explodes = new ArrayList<>();

    Tank mainTank = new Tank(200, 200, Dir.DOWN,this, Group.GOOD);

    public TankFrame() {
        init();
    }

    public void init() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setVisible(true);
        setResizable(false);
        setTitle("tank war");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new KeyListener());
    }

    @Override
    public void paint(Graphics g) {

        setMessage(g);
        mainTank.paint(g);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).estimateLive(tanks.get(j));
            }
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
    }

    private void setMessage(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.red);
        g.drawString("子弹数量" + bullets.size(), 10, 50);
        g.drawString("敌人数量" + tanks.size(), 11, 70);
        g.setColor(color);
    }

    class KeyListener extends KeyAdapter {
        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    left = true;
                    break;
                case KeyEvent.VK_UP:
                    up = true;
                    break;
                case KeyEvent.VK_DOWN:
                    down = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = true;
                    break;
            }
            mainTankMoving();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    left = false;
                    break;
                case KeyEvent.VK_UP:
                    up = false;
                    break;
                case KeyEvent.VK_DOWN:
                    down = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    mainTank.fire();
            }
            mainTankMoving();
        }

        private void mainTankMoving() {
            if (!up && !down && !left && !right) mainTank.setMoving(false);
            else
                mainTank.setMoving(true);
            if (up) mainTank.setDir(Dir.UP);
            if (left) mainTank.setDir(Dir.LEFT);
            if (right) mainTank.setDir(Dir.RIGHT);
            if (down) mainTank.setDir(Dir.DOWN);
        }
    }

    //利用双缓冲解决屏幕闪烁

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0,0,null);
    }
}
