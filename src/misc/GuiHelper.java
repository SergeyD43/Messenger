package misc;

import javax.swing.*;
import java.awt.*;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;

public class GuiHelper
{
//    public static void drawImage(Graphics g, BufferedImage image, int x, int y, int width, int height) {
//        double dx = width * 1.0 / image.getWidth();
//        double dy = height * 1.0 / image.getHeight();
//        double d = Math.min(dy, dx);
//        int imageWidth = (int)Math.round(d * image.getWidth());
//        int imageHeight = (int)Math.round(d * image.getHeight());
//        x += (width - imageWidth) / 2;
//        y += (height - imageHeight) / 2;
//        g.drawImage(image, x, y, imageWidth, imageHeight, null);
//    }

    public static void decorateScrollPane(JScrollPane scrollPane) {
        int width = 3;

        JScrollBar verticalScrollBar =  scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new MyScrollbarUI());
        verticalScrollBar.setPreferredSize(new Dimension(width, Integer.MAX_VALUE));

        JScrollBar horizontalScrollBar =  scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setUI(new MyScrollbarUI());
        horizontalScrollBar.setPreferredSize(new Dimension(Integer.MAX_VALUE, width));

        for (String corner : new String[] {ScrollPaneConstants.LOWER_RIGHT_CORNER, ScrollPaneConstants.LOWER_LEFT_CORNER,
                ScrollPaneConstants.UPPER_LEFT_CORNER, ScrollPaneConstants.UPPER_RIGHT_CORNER}) {
            JPanel panel = new JPanel();
            panel.setBackground(Color.white);
            scrollPane.setCorner(corner, panel);
        }
    }

    public static Rectangle drawImage(Graphics g, BufferedImage image, int x, int y, int width, int height) {
        Rectangle rect = getAreaFor(new Rectangle(x, y, width, height), new Dimension(image.getWidth(), image.getHeight()));
        g.drawImage(image, rect.x, rect.y, rect.width, rect.height, null);
        return rect;
    }

    public static int drawImage(Graphics g, BufferedImage image, int x, int y, int width, int height, int inset, boolean right) {
        int photoHeight = height - inset * 2;
        int photoWidth = width - inset * 2;

        x += inset;
        y += inset;

        Rectangle area = getAreaFor(new Rectangle(x, y, photoWidth, photoHeight), new Dimension(image.getWidth(), image.getHeight()));

        if(right) {
            x += width - area.width;
        }

        g.drawImage(image, x, area.y, area.width, area.height, null);

        if(right)
            return x - inset;
        else
            return x + area.width + inset;

    }

    public static Rectangle getAreaFor(Rectangle area, Dimension size) {
        int x = area.x;
        int y = area.y;
        double dx = area.width / size.getWidth();
        double dy = area.height / size.getHeight();
        double d = Math.min(dy, dx);
        int newWidth = (int)Math.round(d * size.getWidth());
        int newHeight = (int)Math.round(d * size.getHeight());
        x += (area.width - newWidth) / 2;
        y += (area.height - newHeight) / 2;
        return new Rectangle(x, y, newWidth, newHeight);
    }

    public static int drawText(Graphics g, String text, Color color, Font font, int x, int y, int width, int height, int inset, boolean right) {

        String line = text;

        x += inset;
        int maxWidth = width - inset * 2;
        FontMetrics fontMetrics = g.getFontMetrics(font);

        while (fontMetrics.stringWidth(line) > maxWidth) {
            if (line.length() > 3)
                line = line.substring(0, line.length() - 4) + "...";
            else if(right)
                return x + maxWidth - inset;
            else
                return x + inset;
        }

        LineMetrics lineMetrics = fontMetrics.getLineMetrics(line, g);
        y += (int) Math.round((height - lineMetrics.getHeight()) / 2.0 + fontMetrics.getAscent());

        if(right)
            x += maxWidth - fontMetrics.stringWidth(line);

        g.setColor(color);
        g.setFont(font);
        g.drawString(line, x, y);

        if(right)
            return x - inset;
        else
            return x + fontMetrics.stringWidth(line) + inset;
    }

    public static boolean equal(Object a, Object b) {
        return a == b || a != null && a.equals(b);
    }
}
