package app;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author vkachenyuk
 */
public class ManageableCanvas extends Canvas implements ManageableScreen {

    private ScreenManager screenManager;
    
    public ManageableCanvas() {
        super();
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
    
    protected void paint(Graphics g) {
        
    }
    
}
