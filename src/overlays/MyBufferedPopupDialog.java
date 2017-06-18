package overlays;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;


public class MyBufferedPopupDialog extends JLayeredPane
{
    private Component background;
    private Component[] foregrounds;
    private int index;

    private BufferedImage image;
    private OverlayLayout overlayLayout;

    {
        overlayLayout = new OverlayLayout(this);;
        setLayout(overlayLayout);
    }

    private JPanel fakeBackground = new JPanel()
    {
        @Override
        public void paint(Graphics g) {
            //super.paint(g);
            if(image == null || image.getHeight() != background.getHeight() || image.getWidth() != background.getWidth())
            {
                image = new BufferedImage(background.getWidth(), background.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D g2d = image.createGraphics();
                try {
                    background.paint(g2d);
                } finally {
                    g2d.dispose();
                }
            }
            g.drawImage(image, 0, 0, null);
        }
    };

    public MyBufferedPopupDialog(Component background, Component... foregrounds) {

        this.background = background;
        this.background.setVisible(true);
        add(background, new Integer(0));


        fakeBackground.setVisible(false);
        add(fakeBackground, new Integer(1));


        this.foregrounds = Arrays.copyOf(foregrounds, foregrounds.length);
        for(Component foreground : foregrounds) {
            foreground.setVisible(false);
            add(foreground, new Integer(2));
        }

        index = -1;
    }

    public void setIndex(int index) {

        if(index < 0)
            index = -1;
        else //Проверка на диапазон
            foregrounds[index] = foregrounds[index];

        if(this.index != index) {
            if(this.index >= 0 && index >= 0) {
                foregrounds[this.index].setVisible(false);
                foregrounds[index].setVisible(true);
                this.index = index;
            } else if(this.index >= 0) {
                foregrounds[this.index].setVisible(false);
                fakeBackground.setVisible(false);
                background.setVisible(true);
                this.index = -1;
                image = null;
            } else if(index >= 0) {
                image = null;
                foregrounds[index].setVisible(true);
                fakeBackground.setVisible(true);
                this.background.setVisible(false);
                this.index = index;
            }
        }
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void doLayout() {
        super.doLayout();
        for(Component component : getComponents()) {
            component.setBounds(0, 0, this.getWidth(), this.getHeight());
        }
    }

    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }
}
