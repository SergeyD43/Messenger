package gui;

import misc.GhostText;
import misc.GuiHelper;
import org.javagram.dao.Person;
import resources.Fonts;
import resources.Res;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;


public class MainForm extends JPanel{
    private JPanel rootPanelContacts;
    private JTextField textFieldListContacts;
    private JButton buttonProfile;
    private JPanel titlePanel;
    private JPanel contactsPanel;
    private JScrollPane contactsScrollPane;
    private JPanel testPanel;
    private JPanel messagesPanel;
    private JButton sendMessageButton;
    private JTextArea messageTextArea;
    private JScrollPane messageTextScrollPane;
    private JPanel buddyPanel;
    private JPanel searchIconPanel;
    private JTextField searchTextField;
    private JButton buddyEditButton;
    private JList<Person> list1;

    private String meText;
    private BufferedImage mePhoto;

    private String buddyText;
    private BufferedImage buddyPhoto;

    public MainForm()  {
        GhostText ghostTextMessage = new GhostText(messageTextArea,"Напишите сообщение...");
        GhostText ghostTextSearch = new GhostText(searchTextField,"Поиск...");

        GuiHelper.decorateScrollPane(messageTextScrollPane);
    }

    private void createUIComponents() {

        rootPanelContacts = this;

        titlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0x02B3E6));
                g.fillRect(0, 0, this.getWidth(), this.getHeight());

                int leftMostPoint = buttonProfile.getX();

                if (meText != null) {

                    int inset = 25;
                    Font font = Fonts.getNameFont().deriveFont(Font.ITALIC, 30);
                    Color color = Color.white;
                    String text = meText;

                    leftMostPoint = GuiHelper.drawText(g, text, color, font, 0, 0, leftMostPoint, this.getHeight(), inset, true);
                }

                if (mePhoto != null) {
                    int inset = 2;
                    BufferedImage image = mePhoto;

                    leftMostPoint = GuiHelper.drawImage(g, image, 0, 0, leftMostPoint, this.getHeight(), inset, true);
                }

                GuiHelper.drawImage(g, Res.getLogoMicro(), 12, 3, leftMostPoint, this.getHeight()-8, 3, false);
            }
        };

        buddyPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);

                int leftMostPoint = buddyEditButton.getX();

                if (buddyText != null) {

                    int inset = 10;
                    Font font = Fonts.getNameFont().deriveFont(Font.ITALIC, 18);
                    Color color = Color.BLACK;
                    String text = buddyText;

                    leftMostPoint = GuiHelper.drawText(graphics, text, color, font, 0, 0, leftMostPoint, this.getHeight(), inset, true);
                }

                if (buddyPhoto != null) {
                    int inset = 2;
                    BufferedImage image = buddyPhoto;

                    GuiHelper.drawImage(graphics, image, 0, 0, leftMostPoint, this.getHeight(), inset, true);
                }

            }
        };

        buddyEditButton = new JButton() {
            @Override
            protected void paintComponent(Graphics graphics) {
                //super.paintComponent(graphics);
                GuiHelper.drawImage(graphics, Res.getIconEdit(), 0, 0, this.getWidth(), this.getHeight());
            }

            @Override
            protected void paintBorder(Graphics graphics) {
                //super.paintBorder(graphics);
            }
        };

        searchIconPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                int inset = 2;
                GuiHelper.drawImage(graphics, Res.getIconSearch(), inset, inset, this.getWidth() - inset * 2, this.getHeight() - inset * 2);
            }
        };

        sendMessageButton = new JButton(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Res.getButtonSend(),0,0, null);
            }
        };

        buttonProfile = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
                GuiHelper.drawImage(g, Res.getIconSettings(), 0, 0, this.getWidth(), this.getHeight());
            }
            @Override
            protected void paintBorder(Graphics graphics) {
                //super.paintBorder(graphics);
            }
        };

        searchTextField = new JTextField() {
            @Override
            protected void paintBorder(Graphics graphics) {
//                super.paintBorder(graphics);
            }
        };

    }

    public void setContactsPanel(Component contactsPanel) {
        this.contactsPanel.removeAll();
        this.contactsPanel.add(contactsPanel);
    }

    public Component getMessagesPanel() {
        return this.messagesPanel.getComponent(0);
    }

    public void setMessagesPanel(Component messagesPanel) {
        this.messagesPanel.removeAll();
        this.messagesPanel.add(messagesPanel);
    }

    public JPanel getRootPanelContacts() {
        return rootPanelContacts;
    }

    public JTextField getTextFieldListContacts() {
        return textFieldListContacts;
    }

    public JButton getButtonProfile() {
        return buttonProfile;
    }

    public JPanel getContactsPanel() {
        return contactsPanel;
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public JList<Person> getList1() {
        return list1;
    }

    public void addSendMessageListener(ActionListener listener) {
        this.sendMessageButton.addActionListener(listener);
    }

    public String getMessageText() {
        return this.messageTextArea.getText();
    }

    public void setMessageText(String text) {
        this.messageTextArea.setText(text);
    }

    public String getMeText() {
        return meText;
    }

    public void setMeText(String meText) {
        this.meText = meText;
        repaint();
    }

    public BufferedImage getMePhoto() {
        return mePhoto;
    }

    public void setMePhoto(BufferedImage mePhoto) {
        this.mePhoto = mePhoto;
        repaint();
    }

    public String getBuddyText() {
        return buddyText;
    }

    public void setBuddyText(String buddyText) {
        this.buddyText = buddyText;
        repaint();
    }

    public BufferedImage getBuddyPhoto() {
        return buddyPhoto;
    }

    public void setBuddyPhoto(BufferedImage buddyPhoto) {
        this.buddyPhoto = buddyPhoto;
        repaint();
    }
}
