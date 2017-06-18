package overlays;

import misc.GuiHelper;
import resources.Res;

import javax.swing.*;
import java.awt.*;

public class PlusOverlay extends JPanel {
    private JPanel rootPanel;
    private JButton plusButton;

    private void createUIComponents() {
        rootPanel = this;

        plusButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                //super.paintComponent(graphics);
                GuiHelper.drawImage(g, Res.getIconPlus(), 0, 0, this.getWidth(), this.getHeight());
            }

            @Override
            protected void paintBorder(Graphics g) {
                //super.paintBorder(graphics);
            }
        };
    }

    public JButton getPlusButton() {
        return plusButton;
    }
}
