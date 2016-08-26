package app;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;

/**
 *
 * @author vkachenyuk
 */
public class MainMenu extends ManageableForm implements CommandListener {
    
    public static final String NEW_GAME_COMMAND = "New Game";
    public static final String GAME_SETTINGS_COMMAND = "Settings";
    public static final String SHOW_CREDITS_COMMANT = "Credits";
    public static final String LOAD_GAME_COMMANT = "Load Game";
    public static final String EXIT_COMMANT = "Exit";
    
    private TetrisMidlet owner;
    
    public MainMenu(TetrisMidlet owner, String title) {
        super(title);
        
        this.owner = owner;
        setBGImage(createBGImage());
        setMenuCommands();
        setCommandListener(this);
        
    }
    
    private void setBGImage(Image image) {
        
        append(image);
    }
    
    private Image createBGImage() {
        
        Image image = null;
        try {
            image = Image.createImage("MainMenuBG.png");
        } catch (java.io.IOException ex) {
            // just let return value be null
        }
        return image;
    }
    
    private void setMenuCommands() {
        
        addCommand(new Command(NEW_GAME_COMMAND, Command.ITEM, 0));
        addCommand(new Command(GAME_SETTINGS_COMMAND, Command.ITEM, 1));
        addCommand(new Command(LOAD_GAME_COMMANT, Command.ITEM, 1));
        addCommand(new Command(SHOW_CREDITS_COMMANT, Command.ITEM, 1));
        addCommand(new Command(EXIT_COMMANT, Command.ITEM, 2));
    }
    
    public void commandAction(Command command, Displayable display) {
        
        if(command.getLabel() == NEW_GAME_COMMAND) {
            owner.requestStartNewGame();
        }
        if(command.getLabel() == GAME_SETTINGS_COMMAND) {
            
        }
        if(command.getLabel() == LOAD_GAME_COMMANT) {
            
        }
        if(command.getLabel() == SHOW_CREDITS_COMMANT) {
            showNextScreen(new CreditsScreen());
        }
        if(command.getLabel() == EXIT_COMMANT) {
            owner.requestExitApp();
        }
    }
    
}
