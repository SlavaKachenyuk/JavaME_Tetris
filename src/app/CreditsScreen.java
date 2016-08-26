package app;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

/**
 *
 * @author vkachenyuk
 */
public class CreditsScreen extends ManageableForm implements CommandListener {

    public CreditsScreen() {
        
        super("Credits");
        append("Develodep by slava_32.");
        addCommand(new Command("OK", Command.OK, 0));
        setCommandListener(this);
    }
    
    public void commandAction(Command command, Displayable display) {
        if(command.getCommandType() == Command.OK) {
            close();
        }
    }
}
