package com.hzj;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @ClassName test
 * @Description TODO
 * @Author 黄政杰
 * @Date 2020/6/27 9:46
 * @Version 1.0
 **/
public class test {
    @Test
    public void test01() throws IOException {
        BufferedImage read = ImageIO.read(test.class.getClassLoader().getResourceAsStream("imgs/0.gif"));
        System.out.println(read);
    }
}
