package gui;

import misc.GhostText;
import org.javagram.dao.Person;
import resources.Res;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;


public class FormRegistration {
    private JTextField surnameRegistrationTextField;
    private JTextField nameRegistrationTextField;
    private JButton registrationButton;
    private JPanel rootPanelRegistration;
    private JTextPane TextPane;
    private JPanel panelLogo;

    public FormRegistration() throws IOException {
        nameRegistrationTextField.setBorder(null);
        surnameRegistrationTextField.setBorder(null);
        Border border = BorderFactory.createMatteBorder(0,0,2,0, Color.WHITE);
        nameRegistrationTextField.setBorder(border);
        surnameRegistrationTextField.setBorder(border);
        GhostText ghostTextName = new GhostText(nameRegistrationTextField,"Имя");
        GhostText ghostTextSurname = new GhostText(surnameRegistrationTextField,"Фамилия");
    }

    public JPanel getRootPanelRegistration() {
        return rootPanelRegistration;
    }

    public JButton getRegistrationButton() {
        return registrationButton;
    }

    public JTextField getNameRegistrationTextField() {
        return nameRegistrationTextField;
    }

    public JTextField getSurnameRegistrationTextField() {
        return surnameRegistrationTextField;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanelRegistration = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Res.getBackground(), 0, 0, getRootPanelRegistration().getWidth() , getRootPanelRegistration().getHeight(), null);
            }
        };
        panelLogo = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Res.getLogoMini(), 0, 0, getPanelLogo().getWidth(), getPanelLogo().getHeight(), null);
            }
        };
    }

    public JPanel getPanelLogo() {
        return panelLogo;
    }
}
