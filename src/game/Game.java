package game;

import app.ManageableCanvas;
import engine.EventType;
import engine.GameState;
import engine.TetrisGameEngine;
import events.Event;
import events.EventDispatcher;
import events.EventListener;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author vkachenyuk
 */
public class Game extends ManageableCanvas implements EventListener, CommandListener {

    private TetrisGameEngine gameEngine;
    private PlayerActionExecutor playerActionExecutor;
    
    private boolean fullScreen = false;
    private GameVisualConfig visualConfig;
    private GameFieldRenderer gameFieldRenderer;
    private GameUIRenderer gameUIRenderer;
    
    
    private int gameStepCounter = 0;
    
    public Game() {
        
        addCommand(new Command("Exit", Command.EXIT, 0));
        addCommand(new Command("Screen", Command.SCREEN, 1));
        setCommandListener(this);
        
        visualConfig = new GameVisualConfig();
        gameFieldRenderer = new GameFieldRenderer(visualConfig);
        
        gameEngine = new TetrisGameEngine();
        gameEngine.addEventListener(this);
        gameEngine.start();
        playerActionExecutor = new PlayerActionExecutor(gameEngine);
        
        setFullScreenMode(fullScreen);
    }

    public void handleEvent(Event event, EventDispatcher sender) {
        if(event.getType().equals(EventType.GAME_STATE)) {
            gameStepCounter++;
            repaint();
        }
    }
    
    public void commandAction(Command command, Displayable display) {
        if(command.getCommandType() == Command.EXIT) {
            gameEngine.stop();
            gameEngine.removeEventListener(this);
            playerActionExecutor.destroy();
            close();
        }
        if(command.getCommandType() == Command.SCREEN) {
            fullScreen = !fullScreen;
            setFullScreenMode(fullScreen);
        }
    }

    protected void keyPressed(int keyCode) {
        super.keyPressed(keyCode);
        switch(keyCode) {
            case UP:
            case KEY_NUM2: {
                playerActionExecutor.rotateShape();
                break;
            }
            case LEFT:
            case KEY_NUM4: {
                playerActionExecutor.startDragToLeftShape();
                break;
            }
            case RIGHT:
            case KEY_NUM6: {
                playerActionExecutor.startDragToRightShape();
                break;
            }
            case DOWN:
            case KEY_NUM8: {
                playerActionExecutor.startDropDownShape();
                break;
            }
        }
    }

    protected void keyReleased(int keyCode) {
        super.keyReleased(keyCode);
        switch(keyCode) {
            case LEFT:
            case KEY_NUM4: {
                playerActionExecutor.stopDragToLeftShape();
                break;
            }
            case RIGHT:
            case KEY_NUM6: {
                playerActionExecutor.stopDragToRightShape();
                break;
            }
            case DOWN:
            case KEY_NUM8: {
                playerActionExecutor.stopDropDownShape();
                break;
            }
        }
    }

    protected void paint(Graphics g) {
        super.paint(g);
        g.setColor(0xC0C0C0);
        g.fillRect(0, 0, getWidth(), getHeight());
        //g.setColor(0x00ff55);
        //g.drawString("Size: " + String.valueOf(getWidth()) + "x" + String.valueOf(getHeight()), 0, 0, 0);
        //g.drawString("Step: " + String.valueOf(gameStepCounter), 0, 20, 0);
        //g.drawString(String.valueOf(System.currentTimeMillis()), 0, 40, 0);
        
        GameState gameState = gameEngine.getGameState();
        gameFieldRenderer.drawField(gameState.fieldData, g);
        gameFieldRenderer.drawCurrentShapeInGameField(gameState.currentShapeData, gameState.currentShapePositionX, gameState.currentShapePositionY, g);
        gameFieldRenderer.drawNextShape(gameState.nextShapeData, g);
    }
    
}
