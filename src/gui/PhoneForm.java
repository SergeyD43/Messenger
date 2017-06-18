package gui;

import resources.Res;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;


public class PhoneForm extends JPanel
{
    private JPanel rootPanelPhone;
    private JButton ButtonPhone;
    private JFormattedTextField formattedTextFieldPhone;
    private JPanel panelJavagram;
    private JTextPane TextPane;
    private JPanel panelPhone;
    private JPanel PanelFieldPhone;


    public PhoneForm() throws IOException
    {
        formattedTextFieldPhone.setBorder(null);
        Border border = BorderFactory.createMatteBorder(0,0,2,0, Color.WHITE);
        PanelFieldPhone.setBorder(border);
    }

    public JPanel getRootPanelPhone()
    {
        return rootPanelPhone;
    }

    public JButton getButtonPhone() {
        return ButtonPhone;
    }

    private void createUIComponents() {

        try {
            MaskFormatter formatter = new MaskFormatter("+7 ### ###-##-##");
            formatter.setPlaceholderCharacter(' ');
            formattedTextFieldPhone = new JFormattedTextField(formatter);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        rootPanelPhone = new JPanel(){
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(Res.getBackground(), 0, 0, getRootPanelPhone().getWidth(), getRootPanelPhone().getHeight(), null);
            }
        };
        panelJavagram = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Res.getLogo(), 0, 0, getPanelJavagram().getWidth(), getPanelJavagram().getHeight(), null);
            }
        };
        panelPhone = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Res.getIconPhone(), 0, 0, getPanelPhone().getWidth(), getPanelPhone().getHeight(), null);
            }
        };
    }

    public String getStringFieldPhone()
    {
        try
        {
            formattedTextFieldPhone.commitEdit();
            return formattedTextFieldPhone.getValue().toString();
        } catch (ParseException e) {
            return null;
        }
    }

    public JFormattedTextField getFormattedTextFieldPhone()
    {
        return formattedTextFieldPhone;
    }

    public JPanel getPanelJavagram() {
        return panelJavagram;
    }

    public JPanel getPanelPhone() {
        return panelPhone;
    }
}
