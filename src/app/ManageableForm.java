package app;

import javax.microedition.lcdui.Form;

/**
 *
 * @author vkachenyuk
 */
public class ManageableForm extends Form implements ManageableScreen {

    private ScreenManager screenManager;
    
    public ManageableForm(String title) {
        super(title);
    }

    public void setScreenManager(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public void showNextScreen(ManageableScreen manageableScreen) {
        screenManager.openScreen(manageableScreen);
    }
    
    public void close() {
        this.screenManager.closeScreen(this);
        this.screenManager = null;
    }

}
