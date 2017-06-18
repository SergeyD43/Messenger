import gui.MyFrame;

import java.io.IOException;

public class Loader
{
    public static void main(String[] args) {
        MyFrame myFrame;
        try {
            myFrame = new MyFrame();
            myFrame.setLocationRelativeTo(null);
            myFrame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
