package engine;

import engine.shapes.TetrisShape;
import events.Event;
import events.EventDispatcher;

/**
 *
 * @author vkachenyuk
 */
public class TetrisGameEngine extends EventDispatcher implements Runnable {
    
    public GameSettings settings;
    public int score;
    
    private Thread thread;
    private GameField field;
    private TetrisShape currentShape;
    private TetrisShape nextShape;
    private ShapeGenerator shapeGenerator;
    private boolean isFirstRun = true;
    
    public TetrisGameEngine() {
        this(new GameSettings());
    }
    
    public TetrisGameEngine(GameSettings settings) {
        this.settings = settings;
        this.thread = new Thread(this);
    }

    public void run() {
        initialGameStep();
        delay();
        while(true) {
            gameStep();
            delay();
        }
    }
    
    private void delay() {
        try {
            Thread.sleep(settings.stepInterval);
        } catch (Exception e) {
        }
    }
    
    private synchronized void initialGameStep() {
        this.field = new GameField();
        this.shapeGenerator = new ShapeGenerator();
        this.currentShape = shapeGenerator.generateRandomShape();
        this.nextShape = shapeGenerator.generateRandomShape();
        gameStateUpdated();
    }
    
    private synchronized void gameStep() {
        
        ActionResult dropDownResult = dropDownCurrentShape();
        if(dropDownResult.isDone()) {
            gameStateUpdated();
        } else {
            onCurrentShapeInstaled();
        }
    }
    
    private ActionResult rotateCurrentShape() {
        if(isShapePositionAllowed(currentShape.getNextRotationState(), currentShape.positionX, currentShape.positionY)) {
            currentShape.rotate();
            return ActionResult.done;
        }
        return ActionResult.fail;
    }
    
    private ActionResult dropDownCurrentShape() {
        if(isShapePositionAllowed(currentShape.getData(), currentShape.positionX, currentShape.positionY + 1)) {
            currentShape.positionY++;
            return ActionResult.done;
        } else {
            score +=10;
        }
        return ActionResult.fail;
    }
    
    private ActionResult dragToLeftCurrentShape() {
        if(isShapePositionAllowed(currentShape.getData(), currentShape.positionX - 1, currentShape.positionY)) {
            currentShape.positionX--;
            return ActionResult.done;
        }
        return ActionResult.fail;
    }
    
    private ActionResult dragToRightCurrentShape() {
        if(isShapePositionAllowed(currentShape.getData(), currentShape.positionX + 1, currentShape.positionY + 1)) {
            currentShape.positionX++;
            return ActionResult.done;
        }
        return ActionResult.fail;
    }
    
    private void onCurrentShapeInstaled() {
        putCurrentShapeDataToGameField();
        int numDestroyedLines = destroyFullLines();
        if(numDestroyedLines > 0) {
            
        }
        enterNextShape();
        gameStateUpdated();
    }
    
    private void gameStateUpdated() {
        dispatchEvent(new Event(EventType.GAME_STATE));
    }
    
    /**********Player control actions**********/
    public synchronized ActionResult rotateShape() {
        ActionResult result = rotateCurrentShape();
        if(result.isDone()) {
            gameStateUpdated();
        }
        return result;
    }
    
    public synchronized ActionResult dropDownShape() {
        ActionResult result = dropDownCurrentShape();
        if(result.isDone()) {
            gameStateUpdated();
        } else {
            onCurrentShapeInstaled();
        }
        return result;
    }
    
    public synchronized ActionResult dragToLeftShape() {
        ActionResult result = dragToLeftCurrentShape();
        if(result.isDone()) {
            gameStateUpdated();
        }
        return result;
    }
    
    public synchronized ActionResult dragToRightShape() {
        ActionResult result = dragToRightCurrentShape();
        if(result.isDone()) {
            gameStateUpdated();
        }
        return result;
    }
    /******************************************/
    
    private boolean isShapePositionAllowed(byte[][] shapeData, int positionX, int positionY) {
        for (int i = 0; i < shapeData.length; i++) {
            for (int j = 0; j < shapeData[i].length; j++) {
                if(shapeData[i][j] != 0) {
                    int appropriateFieldRowIndex = positionY + i;
                    int appropriateFieldColumnIndex = positionX + j;
                    if(appropriateFieldColumnIndex < 0 || appropriateFieldColumnIndex >= GameField.WIDTH || appropriateFieldRowIndex >= GameField.HEIGHT) {
                        return false;
                    }
                    if(field.data[appropriateFieldRowIndex][appropriateFieldColumnIndex] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private void putCurrentShapeDataToGameField() {
        if (isFirstRun){
            score = 0;
            isFirstRun = false;
        }
        byte[][] shapeData = currentShape.getData();
        for (int i = 0; i < shapeData.length; i++) {
            for (int j = 0; j < shapeData[i].length; j++) {
                if(shapeData[i][j] != 0) {
                    int appropriateFieldRowIndex = currentShape.positionY + i;
                    int appropriateFieldColumnIndex = currentShape.positionX + j;
                    field.data[appropriateFieldRowIndex][appropriateFieldColumnIndex] = shapeData[i][j];
                }
            }
        }
    }
    
    private int destroyFullLines() {
        int fullLinesCounter = 0;
        for (int i = field.data.length - 1; i >= 0; i--) {
            boolean isLineFull = true;
            for (int j = 0; j < field.data[i].length; j++) {
                if(field.data[i][j] == 0) {
                    isLineFull = false;
                    break;
                }
            }
            if(isLineFull) {
                fullLinesCounter++;
                score += 100;
                for (int k = i-1; k >= 0; k--) {
                    if(k > 0) {
                        for (int j = 0; j < field.data[i].length; j++) {
                            field.data[k+1][j] = field.data[k][j];
                        }
                    } else {
                        for (int j = 0; j < field.data[i].length; j++) {
                            field.data[k][j] = 0;
                        }
                    }
                }
                i++;
            }
        }
        return fullLinesCounter;
    }
    
    private void enterNextShape() {
        currentShape = nextShape;
        nextShape = shapeGenerator.generateRandomShape();
    }
    
    public GameState getGameState() {
        GameState state = new GameState();
        //Object gameFieldClone = field.data.clone();
        state.fieldData = field.data;
        state.currentShapeData = currentShape.getData();
        state.currentShapePositionX = currentShape.positionX;
        state.currentShapePositionY = currentShape.positionY;
        state.nextShapeData = nextShape.getData();
        return state;
    }
    
    public GameSettings getGameSettings() {
        return settings;
    }
    
    public void start() {
        thread.start();
    }
    
    public void pause() {
        
    }
    
    public void stop() {
        thread.interrupt();
    }
    
}