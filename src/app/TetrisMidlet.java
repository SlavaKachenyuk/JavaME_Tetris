package app;

import game.Game;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

/**
 * @author vkachenyuk
 */
public class TetrisMidlet extends MIDlet implements CommandListener {

    ScreenManager screenManager;
    MainMenu mainMenu;
    
    public TetrisMidlet() {
        
        screenManager = new ScreenManager(Display.getDisplay(this));
        mainMenu = new MainMenu(this, "Tetris");
    }
    
    public void startApp() {
        
        screenManager.openScreen(mainMenu);
        //Display.getDisplay(this).setCurrent(mainMenu);
    }
    
    public void requestStartNewGame() {
        Game game = new Game();
        screenManager.openScreen(game);
    }
    
    public void requestExitApp() {
        
        destroyApp(false);
	notifyDestroyed();
    }
    
    public void pauseApp() {
        
    }
    
    public void destroyApp(boolean unconditional) {
        
    }
    
    public void commandAction(Command command, Displayable display) {
        
    }
}
