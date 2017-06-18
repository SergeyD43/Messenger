package resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Res
{
    private Res() {
    }

    private static BufferedImage background;
    private static BufferedImage logo;
    private static BufferedImage iconPhone;
    private static BufferedImage logoMini;
    private static BufferedImage iconLock;
    private static BufferedImage buttonBackground;
    private static BufferedImage logoMicro;
    private static BufferedImage iconHide;
    private static BufferedImage iconClose;
    private static BufferedImage iconContact;
    private static BufferedImage buttonSend;
    private static BufferedImage iconBack;
    private static BufferedImage iconPlus;
    private static BufferedImage iconSettings;
    private static BufferedImage iconSearch;
    private static BufferedImage iconEdit;

    static {
        try{
            background = ImageIO.read(Res.class.getResource("/img/background.png"));
        }
        catch (Exception e){
            e.printStackTrace();
            background = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try{
            logo = ImageIO.read(Res.class.getResource("/img/logo.png"));
        }
        catch (Exception e){
            e.printStackTrace();
            logo = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try{
            iconPhone = ImageIO.read(Res.class.getResource("/img/icon-phone.png"));
        }
        catch (Exception e){
            e.printStackTrace();
            iconPhone = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try{
            logoMini = ImageIO.read(Res.class.getResource("/img/logo-mini.png"));
        }
        catch (Exception e){
            e.printStackTrace();
            logoMini = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try{
            iconLock = ImageIO.read(Res.class.getResource("/img/icon-lock.png"));
        }
        catch (Exception e){
            e.printStackTrace();
            iconLock = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try{
            buttonBackground = ImageIO.read(Res.class.getResource("/img/button-background.png"));
        }
        catch (Exception e){
            e.printStackTrace();
            buttonBackground = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try{
            logoMicro = ImageIO.read(Res.class.getResource("/img/logo-micro.png"));
        }
        catch (Exception e){
            e.printStackTrace();
            logoMicro = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try{
            iconHide = ImageIO.read(Res.class.getResource("/img/icon-hide.png"));
        }
        catch (Exception e){
            e.printStackTrace();
            iconHide = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try{
            iconClose = ImageIO.read(Res.class.getResource("/img/icon-close.png"));
        }
        catch (Exception e){
            e.printStackTrace();
            iconClose = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try{
            iconContact = ImageIO.read(Res.class.getResource("/img/images.jpg"));
        }
        catch (Exception e){
            e.printStackTrace();
            iconContact = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try{
            buttonSend = ImageIO.read(Res.class.getResource("/img/button-send.png"));
        }
        catch (Exception e){
            e.printStackTrace();
            buttonSend = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try{
            iconBack = ImageIO.read(Res.class.getResource("/img/icon-back.png"));
        }
        catch (Exception e){
            e.printStackTrace();
            iconBack = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try{
            iconPlus = ImageIO.read(Res.class.getResource("/img/icon-plus.png"));
        }
        catch (Exception e){
            e.printStackTrace();
            iconPlus = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try{
            iconSettings = ImageIO.read(Res.class.getResource("/img/icon-settings.png"));
        }
        catch (Exception e){
            e.printStackTrace();
            iconSettings = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try{
            iconSearch = ImageIO.read(Res.class.getResource("/img/icon-search.png"));
        }
        catch (Exception e){
            e.printStackTrace();
            iconSearch = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try{
            iconEdit = ImageIO.read(Res.class.getResource("/img/icon-edit.png"));
        }
        catch (Exception e){
            e.printStackTrace();
            iconEdit = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
    }

    public static BufferedImage getBackground() {
        return background;
    }

    public static BufferedImage getLogo() {
        return logo;
    }

    public static BufferedImage getIconPhone() {
        return iconPhone;
    }

    public static BufferedImage getLogoMini() {
        return logoMini;
    }

    public static BufferedImage getIconLock() {
        return iconLock;
    }

    public static BufferedImage getButtonBackground() {
        return buttonBackground;
    }

    public static BufferedImage getLogoMicro() {
        return logoMicro;
    }

    public static BufferedImage getIconHide() {
        return iconHide;
    }

    public static BufferedImage getIconClose() {
        return iconClose;
    }

    public static BufferedImage getIconContact() {
        return iconContact;
    }

    public static BufferedImage getButtonSend() {
        return buttonSend;
    }

    public static BufferedImage getIconBack() {
        return iconBack;
    }

    public static BufferedImage getIconPlus() {
        return iconPlus;
    }

    public static BufferedImage getIconSettings() {
        return iconSettings;
    }

    public static BufferedImage getIconSearch() {
        return iconSearch;
    }

    public static BufferedImage getIconEdit() {
        return iconEdit;
    }
}
