package gui;

import contacts.ContactForm;
import contacts.ContactsList;
import messages.MessagesForm;
import misc.GuiHelper;
import org.javagram.dao.*;
import org.javagram.dao.Dialog;
import org.javagram.dao.Person;
import org.javagram.dao.proxy.TelegramProxy;
import org.javagram.dao.proxy.changes.UpdateChanges;
import org.telegram.api.engine.RpcException;
import overlays.FormAddingContact;
import overlays.FormProfileSettings;
import overlays.MyBufferedPopupDialog;
import overlays.PlusOverlay;
import overlays.MyLayeredPane;
import resources.Res;
import undecorated.FormTopButtons;
import undecorated.ComponentResizerExtended;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;


public class MyFrame extends JFrame
{
    private FormTopButtons formTopButtons = new FormTopButtons();

    private TelegramDAO telegramDAO = new ApiBridgeTelegramDAO("149.154.167.50:443",12688,"7c5ba94e78905b0269dc4c7500f4344f");
    private TelegramProxy telegramProxy;

    private PhoneForm phoneForm = new PhoneForm();
    private CodeForm codeForm = new CodeForm();
    private MainForm mainForm = new MainForm();
    private ContactsList contactsList = new ContactsList();

    private FormRegistration formRegistration = new FormRegistration();
    private FormProfileSettings formProfileSettings = new FormProfileSettings();
    private FormAddingContact formAddingContact = new FormAddingContact();

    private int pX, pY;

    private Timer timer;
    private int messagesFrozen;

    MyBufferedPopupDialog myBufferedPopupDialog = new MyBufferedPopupDialog(mainForm
            ,formAddingContact.getRootPanelAddingContacts(),
            formProfileSettings.getRootPanelProfile());

    private MyLayeredPane contactsLayeredPane = new MyLayeredPane();
    private PlusOverlay plusOverlay = new PlusOverlay();

    public MyFrame() throws IOException
    {
        changeContentPanel(getPhoneForm().getRootPanelPhone());
        setContentPane(getFormTopButtons().getRootPanelTopButtons());
        setSize(1000,700);
        setMinimumSize(new Dimension(500,500));
        setUndecorated(true);

        ComponentResizerExtended cr = new ComponentResizerExtended(ComponentResizerExtended.KEEP_RATIO_CENTER, this);
        cr.registerComponent(this);

        mainForm.setContactsPanel(contactsLayeredPane);
        contactsLayeredPane.add(contactsList, new Integer(0));
        contactsLayeredPane.add(plusOverlay,new Integer(1));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                programClosing();
            }
        });

        formTopButtons.getPanelTop().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                pX = me.getX();
                pY = me.getY();
            }
        });

        formTopButtons.getPanelTop().addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {
                setLocation(getLocation().x + me.getX() - pX,
                        getLocation().y + me.getY() - pY);
            }
        });

        phoneForm.getButtonPhone().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeThePhone();
            }
        });

        formRegistration.getRegistrationButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    takeRegistration();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
        });

        codeForm.getButtonCode().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeTheCode();
            }
        });


        formTopButtons.getCloseButtonPhone().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                programClosing();
            }
        });

        formTopButtons.getRollUpButtonPhone().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setState(JFrame.ICONIFIED);
            }
        });

//        mainForm.setContactsPanel(contactsList);

        mainForm.getButtonProfile().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myBufferedPopupDialog.setIndex(1);
            }
        });

        plusOverlay.getPlusButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myBufferedPopupDialog.setIndex(0);
            }
        });

        mainForm.addSendMessageListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Person buddy =  contactsList.getSelectedValue();
                String text = mainForm.getMessageText().trim();
                if(telegramProxy != null && buddy != null && !text.isEmpty()) {
                    try {
                        telegramProxy.sendMessage(buddy, text);
                        mainForm.setMessageText("");
                        checkForUpdates();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(mainForm.getRootPanelContacts(),
                                "Не могу отправить сообщение",
                                "Ошибка!",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        formProfileSettings.getButtonBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myBufferedPopupDialog.setIndex(-1);
            }
        });

        formAddingContact.getButtonBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myBufferedPopupDialog.setIndex(-1);
            }
        });

        formProfileSettings.getButtonProfileSettings().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        phoneForm.getFormattedTextFieldPhone().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    takeThePhone();
                }
            }
        });

        codeForm.getPasswordField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    takeTheCode();
                }
            }
        });

        formRegistration.getNameRegistrationTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    try {
                        takeRegistration();
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        formRegistration.getSurnameRegistrationTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    try {
                        takeRegistration();
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                checkForUpdates();
            }
        });
        timer.start();

        contactsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {

                if(messagesFrozen != 0)
                    return;

                if(telegramProxy == null)  {
                    displayDialog(null);
                    return;
                }

                displayDialog(contactsList.getSelectedValue());
            }
        });
    }

    protected void checkForUpdates() {
        if (telegramProxy != null) {
            UpdateChanges updateChanges = telegramProxy.update();

            int photosChangedCount = updateChanges.getLargePhotosChanged().size() +
                    updateChanges.getSmallPhotosChanged().size() +
                    updateChanges.getStatusesChanged().size();

            if (updateChanges.getListChanged()) {
                updateContacts();
            } else if (updateChanges.getLargePhotosChanged().size() +
                    updateChanges.getSmallPhotosChanged().size() +
                    updateChanges.getStatusesChanged().size() != 0) {
                contactsList.repaint();
            } else {

            }

            Person currentBuddy = getMessagesForm().getPerson();
            Person targetPerson = contactsList.getSelectedValue();

            Dialog currentDialog = currentBuddy != null ? telegramProxy.getDialog(currentBuddy) : null;

//            if (!equal(targetPerson, currentBuddy)||
//                    updateChanges.getDialogsToReset().contains(currentDialog) ||
//                    updateChanges.getDialogsChanged().getDeleted().contains(currentDialog)) {
//                updateMessages();
//            }

            if (!GuiHelper.equal(targetPerson, currentBuddy) ||
                    updateChanges.getDialogsToReset().contains(currentDialog) ||
                    //updateChanges.getDialogsChanged().getChanged().containsKey(currentDialog) ||
                    updateChanges.getDialogsChanged().getDeleted().contains(currentDialog)) {
                updateMessages();
            } else if(updateChanges.getPersonsChanged().getChanged().containsKey(currentBuddy) || photosChangedCount != 0) {
                displayBuddy(targetPerson);
            }

            if(updateChanges.getPersonsChanged().getChanged().containsKey(telegramProxy.getMe()) || photosChangedCount != 0) {
                displayMe(telegramProxy.getMe());
            }
        }
    }

    private boolean equal(Object a, Object b) {
        return a == b || a != null && a.equals(b);
    }

    public void programClosing()
    {
        telegramDAO.close();
        setVisible(false);
        System.exit(0);
    }

    public void takeThePhone()
    {
        try
        {
            String phoneNumber = phoneForm.getStringFieldPhone();
            if(phoneNumber == null)
            {
                JOptionPane.showMessageDialog(phoneForm.getRootPanelPhone(),
                        "Ошибка ввода!",
                        "WARNING",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            telegramDAO.acceptNumber(phoneNumber);
        }
        catch (RpcException e1)
        {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(phoneForm.getRootPanelPhone(),
                    "Номер не существует!",
                    "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        catch (IOException e2)
        {
            e2.printStackTrace();
            return;
        }

        if(telegramDAO.canSignIn()) {
            try {
                telegramDAO.sendCode();
                codeForm.getTextFieldPhone().setText(phoneForm.getStringFieldPhone());
                changeContentPanel(getCodeForm().getRootPanelCode());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(phoneForm.getRootPanelPhone(),
                        "Потеряно соединение с сервером!",
                        "WARNING",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        } else if(telegramDAO.canSignUp())
        {
            changeContentPanel(getFormRegistration().getRootPanelRegistration());
            return;
        }
    }

    public void takeTheCode()
    {
        String smsCode = new String(getCodeForm().getPasswordField().getPassword());
        if(!telegramDAO.canSignIn())
        {
            try
            {
                telegramDAO.signUp(smsCode,
                        formRegistration.getNameRegistrationTextField().getText(),
                        formRegistration.getSurnameRegistrationTextField().getText());
                createTelegramProxy();
            } catch (IOException e1) {
                warning(e1);
            }
        }
        else
        {
            try
            {
                telegramDAO.signIn(smsCode);
                createTelegramProxy();
                displayMe(telegramProxy.getMe());
            } catch (IOException e1) {
                warning(e1);
            }
        }
    }

    public void takeRegistration() throws ParseException {
        try {
            telegramDAO.sendCode();
            changeContentPanel(getCodeForm().getRootPanelCode());
            getCodeForm().getTextFieldPhone().setText(getPhoneForm().getStringFieldPhone());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void createTelegramProxy() throws IOException {
        telegramProxy = new TelegramProxy(telegramDAO);

        messagesFrozen++;
        try {
            contactsList.setTelegramProxy(telegramProxy);
            contactsList.setSelectedValue(null);
            createMessagesForm();
            displayDialog(null);
        } finally {
            messagesFrozen--;
        }
        setMainForm();
    }

    public void setMainForm()
    {
        changeContentPanel(myBufferedPopupDialog);
        mainForm.revalidate();
        mainForm.repaint();
    }

    private void updateContacts() {
        messagesFrozen++;
        try {
            Person person = contactsList.getSelectedValue();
            contactsList.setTelegramProxy(telegramProxy);
            contactsList.setSelectedValue(person);
        } finally {
            messagesFrozen--;
        }
    }

    private void updateMessages() {
        displayDialog(contactsList.getSelectedValue());
        mainForm.revalidate();
        mainForm.repaint();
    }

    private MessagesForm createMessagesForm() {
        MessagesForm messagesForm = new MessagesForm(telegramProxy);
        mainForm.setMessagesPanel(messagesForm);
        mainForm.revalidate();
        mainForm.repaint();
        return messagesForm;
    }

    private MessagesForm getMessagesForm() {
        if(mainForm.getMessagesPanel() instanceof MessagesForm) {
            return (MessagesForm) mainForm.getMessagesPanel();
        } else {
            return createMessagesForm();
        }
    }

    private void displayDialog(Person person) {
        try {
            MessagesForm messagesForm = getMessagesForm();
            messagesForm.display(person);
//            mainForm.setText(person != null ? person.getFirstName() + " " + person.getLastName() : null);
            displayBuddy(person);
            messagesForm.revalidate();
            messagesForm.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayMe(Me me) {
        if(me == null) {
            mainForm.setMeText(null);
            mainForm.setMePhoto(null);
        } else {
            mainForm.setMeText(me.getFirstName() + " " + me.getLastName());
            try {
                BufferedImage meImage = telegramProxy.getPhoto(me, true);
                if (meImage == null)
                    mainForm.setMePhoto(Res.getIconContact());
                else
                    mainForm.setMePhoto(meImage);
            } catch (IOException e) {
                mainForm.setMePhoto(Res.getIconContact());
                e.printStackTrace();
            }
        }
    }

    private void displayBuddy(Person person) {
        if(person == null) {
            mainForm.setBuddyText(null);
            mainForm.setBuddyPhoto(null);
        } else {
            mainForm.setBuddyText(person.getLastName() + " " + person.getFirstName());
            try {
                BufferedImage buddyImage = telegramProxy.getPhoto(person, true);
                if (buddyImage == null)
                    mainForm.setBuddyPhoto(Res.getIconContact());
                else
                    mainForm.setBuddyPhoto(buddyImage);
            } catch (IOException e) {
                mainForm.setBuddyPhoto(Res.getIconContact());
                e.printStackTrace();
            }
        }
    }

    public void warning(IOException e1)
    {
        e1.printStackTrace();
        JOptionPane.showMessageDialog(codeForm.getRootPanelCode(),
                "Введен неверный код",
                "WARNING",
                JOptionPane.WARNING_MESSAGE);
    }

    private void changeContentPanel(Container contentPanel) {
        formTopButtons.setContentPanel(contentPanel);
    }

    public PhoneForm getPhoneForm()
    {
        return phoneForm;
    }
    public CodeForm getCodeForm()
    {
        return codeForm;
    }
    public MainForm getMainForm()
    {
        return mainForm;
    }
    public FormTopButtons getFormTopButtons()
    {
        return formTopButtons;
    }
    public FormRegistration getFormRegistration() {
        return formRegistration;
    }
    public FormAddingContact getFormAddingContact() {
        return formAddingContact;
    }
    public FormProfileSettings getFormProfileSettings() {
        return formProfileSettings;
    }


}
