package app;

import java.util.Vector;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

/**
 *
 * @author vkachenyuk
 */
public class ScreenManager {
    
    private final Display display;
    private Vector screens;
    
    public ScreenManager(Display display) {
        
        this.display = display;
        this.screens = new Vector();
    }
    
    public void openScreen(ManageableScreen screen) {
        
        screen.setScreenManager(this);
        //TODO: try solve problem without cast to Displayable
        display.setCurrent((Displayable)screen);
        screens.addElement(screen);
    }
    
    public void closeScreen(ManageableScreen screen) {
        
        if(!screens.isEmpty() && screens.lastElement() == screen) {
            int lastElementIndex = screens.size() - 1;
            screens.removeElementAt(lastElementIndex);
            if(!screens.isEmpty()) {
                ManageableScreen nextScreen = (ManageableScreen)screens.lastElement();
                nextScreen.setScreenManager(this);
                //TODO: try solve problem without cast to Displayable
                display.setCurrent((Displayable)nextScreen);
            }
        }
    }
}
