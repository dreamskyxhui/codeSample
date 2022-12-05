package com.share.codesample.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImageCodeUtils {

    private static final char[] chars = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
            'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z'};

    // 默认字符数量
    private final Integer size;
    // 默认干扰线数量
    private final int lines;
    // 默认宽度
    private final int width;
    // 默认高度
    private final int height;
    // 默认字体大小
    private final int fontSize;
    // 默认字体倾斜
    private final boolean tilt;

    private final Color backgroundColor;

    //private Map<String, String> imageCodeMap = new ConcurrentHashMap<>();

    /**
     * 初始化基础参数
     *
     * @param builder
     */
    private ImageCodeUtils(Builder builder) {
        size = builder.size;
        lines = builder.lines;
        width = builder.width;
        height = builder.height;
        fontSize = builder.fontSize;
        tilt = builder.tilt;
        backgroundColor = builder.backgroundColor;
    }

    /**
     * 实例化构造器对象
     *
     * @return
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * 生成随机验证码及图片
     *
     * @return 数组Object。 Object[0]：验证码字符串; Object[1]：验证码图片
     */
    public Object[] createImage() {
        StringBuffer sb = new StringBuffer();
        // 创建空白图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取图片画笔
        Graphics2D graphic = image.createGraphics();

        // 设置抗锯齿
        graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 设置画笔颜色
        graphic.setColor(backgroundColor);

        // 绘制矩形背景
        graphic.fillRect(0, 0, width, height);

        // 画随机字符
        Random ran = new Random();

        //graphic.setBackground(Color.WHITE);

        // 计算每个字符占的宽度，这里预留一个字符的位置用于左右边距
        int codeWidth = width / (size + 1);
        // 字符所处的y轴的坐标
        int y = height * 3 / 4;

        for (int i = 0; i < size; i++) {
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 初始化字体
            Font font = new Font(null, Font.BOLD + Font.ITALIC, fontSize);

            if (tilt) {
                // 随机一个倾斜的角度 -45到45度之间
                int theta = ran.nextInt(45);
                // 随机一个倾斜方向 左或者右
                theta = (ran.nextBoolean() == true) ? theta : -theta;
                AffineTransform affineTransform = new AffineTransform();
                affineTransform.rotate(Math.toRadians(theta), 0, 0);
                font = font.deriveFont(affineTransform);
            }

            // 设置字体大小
            graphic.setFont(font);

            // 计算当前字符绘制的X轴坐标
            int x = (i * codeWidth) + (codeWidth / 2);

            // 取随机字符索引
            int n = ran.nextInt(chars.length);
            // 得到字符文本
            String code = String.valueOf(chars[n]);
            // 画字符
            graphic.drawString(code, x, y);

            // 记录字符
            sb.append(code);
        }
        // 画干扰线
        for (int i = 0; i < lines; i++) {
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 随机画线
            graphic.drawLine(ran.nextInt(width), ran.nextInt(height), ran.nextInt(width), ran.nextInt(height));
        }
        // 返回验证码和图片
        return new Object[]{sb.toString(), image};
    }

    /**
     * 随机取色
     */
    private Color getRandomColor() {
        Random ran = new Random();
        Color color = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
        return color;
    }

    /**
     * 构造器对象
     */
    public static class Builder {

        // 默认字符数量
        private int size = 4;
        // 默认干扰线数量
        private int lines = 10;
        // 默认宽度
        private int width = 80;
        // 默认高度
        private int height = 35;
        // 默认字体大小
        private int fontSize = 25;
        // 默认字体倾斜
        private boolean tilt = true;
        //背景颜色
        private Color backgroundColor = Color.LIGHT_GRAY;

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Builder setLines(int lines) {
            this.lines = lines;
            return this;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setFontSize(int fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        public Builder setTilt(boolean tilt) {
            this.tilt = tilt;
            return this;
        }

        public Builder setBackgroundColor(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public ImageCodeUtils build() {
            return new ImageCodeUtils(this);
        }
    }

}
