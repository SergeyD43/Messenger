package contacts;

import org.javagram.dao.*;
import org.javagram.dao.Dialog;
import org.javagram.dao.Person;
import org.javagram.dao.proxy.TelegramProxy;
import resources.Res;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ContactForm extends JPanel implements ListCellRenderer <Person>
{
    private JPanel rootPanelFormContact;
    private JLabel nameLabel;
    private JPanel photoPanel;
    private JTextPane lastMessageLabel;
    private boolean hasFocus;
    private Person person;
    private TelegramProxy telegramProxy;
    private final double onlineSignSize = 0.3;
    private final int focusMarkerWidth = 4;

    public ContactForm(TelegramProxy telegramProxy)
    {
        this.telegramProxy = telegramProxy;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanelFormContact = this;

        photoPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                boolean small = true;
                BufferedImage image;

                try {
                    image = telegramProxy.getPhoto(person, small);
                } catch (Exception e) {
                    e.printStackTrace();
                    image = null;
                }

                if(image == null)
                    image = Res.getIconContact();

                graphics.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);

                if(telegramProxy.isOnline(person)) {

                    int dx = (int) (this.getWidth() * onlineSignSize);
                    int dy = (int) (this.getHeight() * onlineSignSize);

                    int x = this.getWidth() - dx;
                    int y = this.getHeight() - dy;

                    dx -= 2;
                    dy -= 2;

                    graphics.setColor(new Color(0x00B000));
                    graphics.fillRoundRect(x, y, dx, dy, dx, dy);

                    graphics.setColor(new Color(0x0000B0));
                    graphics.drawRoundRect(x, y, dx, dy, dx, dy);
                }
            }
        };
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if(hasFocus) {
            graphics.setColor(Color.blue);
            graphics.fillRect(0/*this.getWidth() - focusMarkerWidth*/, 0, focusMarkerWidth, this.getHeight());
        }
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Person> jList,
                                                  Person person, int index, boolean selected, boolean hasFocus) {

        this.person = person;
        Dialog dialog = telegramProxy.getDialog(person);
        nameLabel.setText(person.getFirstName() + " " + person.getLastName());

        if(dialog != null){
            this.lastMessageLabel.setText(dialog.getLastMessage().getText());
        } else {
            this.lastMessageLabel.setText("");
        }

        if(selected)
            setBackground(Color.white);
        else {
            setBackground(Color.lightGray);
        }

        this.hasFocus = hasFocus;

        return this;
    }
}
