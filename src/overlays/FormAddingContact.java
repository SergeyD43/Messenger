package overlays;

import misc.GuiHelper;
import resources.Res;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

public class FormAddingContact {
    private JPanel rootPanelAddingContacts;
    private JTextPane TextPane;
    private JTextField textFieldPhoneContact;
    private JTextField textFieldNameContact;
    private JTextField textFieldSurnameContact;
    private JButton buttonAddingContact;
    private JButton buttonBack;

    public FormAddingContact() throws IOException {
        textFieldPhoneContact.setBorder(null);
        textFieldNameContact.setBorder(null);
        textFieldSurnameContact.setBorder(null);
        Border border = BorderFactory.createMatteBorder(0,0,2,0, Color.WHITE);
        textFieldPhoneContact.setBorder(border);
        textFieldNameContact.setBorder(border);
        textFieldSurnameContact.setBorder(border);
    }

    private void createUIComponents() {
        buttonAddingContact = new JButton(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Res.getButtonBackground(), 0, 0, null);
                g.drawString("ДОБАВИТЬ", 10, 10);
            }
        };
        rootPanelAddingContacts = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Color color = new Color(0,0,0, 150);
                g.setColor(color);
                g.fillRect(0,0,rootPanelAddingContacts.getWidth(),rootPanelAddingContacts.getHeight());
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

    public JPanel getRootPanelAddingContacts() {
        return rootPanelAddingContacts;
    }

    public JButton getButtonBack() {
        return buttonBack;
    }
}
