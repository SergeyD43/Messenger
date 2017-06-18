package overlays;

import misc.GuiHelper;
import resources.Res;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

public class FormProfileSettings {
    private JPanel rootPanelProfile;
    private JTextField textFieldNameSettings;
    private JTextField textFieldSurnameSettings;
    private JButton buttonProfileSettings;
    private JButton buttonBack;

    public FormProfileSettings() throws IOException {
        textFieldNameSettings.setBorder(null);
        textFieldSurnameSettings.setBorder(null);
        Border border = BorderFactory.createMatteBorder(0,0,2,0, Color.WHITE);
        textFieldNameSettings.setBorder(border);
        textFieldSurnameSettings.setBorder(border);
    }

    private void createUIComponents() {
        buttonProfileSettings = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Res.getButtonBackground(), 0, 0, null);
                g.drawString("СОХРАНИТЬ", 10, 10);
            }
        };
        rootPanelProfile = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Color color = new Color(0,0,0, 150);
                g.setColor(color);
                g.fillRect(0,0,rootPanelProfile.getWidth(),rootPanelProfile.getHeight());
            }
        };
        buttonBack = new JButton(){
            @Override
            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
                GuiHelper.drawImage(g, Res.getIconBack(), 0, 0, this.getWidth(), this.getHeight());
            }
            @Override
            protected void paintBorder(Graphics graphics) {
                //super.paintBorder(graphics);
            }
        };
    }

    public JButton getButtonProfileSettings() {
        return buttonProfileSettings;
    }

    public JPanel getRootPanelProfile() {
        return rootPanelProfile;
    }

    public JButton getButtonBack() {
        return buttonBack;
    }
}
