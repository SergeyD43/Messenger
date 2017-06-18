package undecorated;

import resources.Res;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FormTopButtons {
    private JPanel rootPanelTopButtons;
    private JButton rollUpButtonPhone;
    private JButton closeButtonPhone;
    private JPanel PanelTop;
    private JPanel contentPanel;

    public FormTopButtons() throws IOException {
        rollUpButtonPhone.setBorder(null);
        closeButtonPhone.setBorder(null);
    }

    public JButton getCloseButtonPhone() {
        return closeButtonPhone;
    }

    public JPanel getPanelTop() {
        return PanelTop;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public JPanel getRootPanelTopButtons() {
        return rootPanelTopButtons;
    }

    public JButton getRollUpButtonPhone() {
        return rollUpButtonPhone;
    }

    public  void setContentPanel(Component added)
    {
        contentPanel.removeAll();
        contentPanel.add(added);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void createUIComponents() {
        rollUpButtonPhone = new JButton(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Res.getIconHide(), 0, 0, null);
            }
        };
        closeButtonPhone = new JButton(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Res.getIconClose(), 0, 0, null);
            }
        };
    }
}
