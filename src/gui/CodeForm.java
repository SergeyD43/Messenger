package gui;

import resources.Res;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

public class CodeForm {
    private JPanel rootPanelCode;
    private JButton ButtonCode;
    private JPasswordField passwordField;
    private JTextPane TextPane;
    private JPanel panelLogo;
    private JPanel panelLock;
    private JPanel panelCode;
    private JTextField textFieldPhone;

    public CodeForm() throws IOException {
        passwordField.setBorder(null);
        Border border = BorderFactory.createMatteBorder(0,0,2,0, Color.WHITE);
        panelCode.setBorder(border);
        textFieldPhone.setBorder(null);
    }

    public JPanel getRootPanelCode() {
        return rootPanelCode;
    }

    public JButton getButtonCode() {
        return ButtonCode;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanelCode = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Res.getBackground(), 0, 0, getRootPanelCode().getWidth() , getRootPanelCode().getHeight(), null);
            }
        };
        panelLogo = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Res.getLogoMini(), 0, 0, getPanelLogo().getWidth(), getPanelLogo().getHeight(), null);
            }
        };
        panelLock = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Res.getIconLock(), 0, 0,  null);
            }
        };
    }

    public JTextField getTextFieldPhone() {
        return textFieldPhone;
    }

    public JPanel getPanelLogo() {
        return panelLogo;
    }

    public JPanel getPanelLock() {
        return panelLock;
    }
}
