package contacts;

import misc.GuiHelper;
import org.javagram.dao.Person;
import org.javagram.dao.proxy.TelegramProxy;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;


public class ContactsList extends JPanel {
    private JPanel rootPanel;
    private JList<Person> list;
    private JScrollPane scrollPane;

    {
        GuiHelper.decorateScrollPane(scrollPane);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = this;
    }

    public void setTelegramProxy(TelegramProxy telegramProxy) {
        java.util.List<Person> dialogs = telegramProxy.getPersons();
        list.setCellRenderer(new ContactForm(telegramProxy));
        list.setListData(dialogs.toArray(new Person[dialogs.size()]));
    }

    public Person getSelectedValue() {
        return list.getSelectedValue();
    }

    public void setSelectedValue(Person person) {
        if(person != null) {
            ListModel<Person> model = list.getModel();
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).equals(person)) {
                    list.setSelectedIndex(i);
                    return;
                }
            }
        }
        list.setSelectedIndex(-1);
    }

    public void addListSelectionListener(ListSelectionListener listSelectionListener)  {
        list.addListSelectionListener(listSelectionListener);
    }
}
