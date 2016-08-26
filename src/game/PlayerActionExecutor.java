package game;

import engine.TetrisGameEngine;

/**
 *
 * @author vkachenyuk
 */
public class PlayerActionExecutor implements Runnable {

    private static final int noneActionId = -1;
    private static final int rotateActionId = 0;
    private static final int dropDownActionId = 1;
    private static final int dragToLeftActionId = 2;
    private static final int dragToRightActionId = 3;
    
    public long dropDownInterval = 100;
    public long dragToLeftInterval = 150;
    public long dragToRightInterval = 150;
    
    private Thread thread;
    private TetrisGameEngine engine;
    
    private int currentActionId = noneActionId;
    
    private long lastExecuteActiveTaskTimeStamp;
    
    public PlayerActionExecutor(TetrisGameEngine engine) {
        this.thread = new Thread(this);
        this.engine = engine;
        this.thread.start();
    }
    
    public void run() {
        while(true) {
            if(hasActiveTask()) {
                long delta = System.currentTimeMillis() - lastExecuteActiveTaskTimeStamp;
                if(delta >= getActiveTaskInterval()) {
                    executeActiveTask();
                }
            }
            try {
                Thread.sleep(5);
            } catch (Exception e) {
            }
        }
    }
    
    private synchronized void executeActiveTask() {
        switch(currentActionId) {
            case rotateActionId: {
                engine.rotateShape();
                break;
            }
            case dropDownActionId: {
                engine.dropDownShape();
                break;
            }
            case dragToLeftActionId: {
                engine.dragToLeftShape();
                break;
            }
            case dragToRightActionId: {
                engine.dragToRightShape();
                break;
            }
        }
        lastExecuteActiveTaskTimeStamp = System.currentTimeMillis();
    }
    
    private long getActiveTaskInterval() {
        switch(currentActionId) {
            case dropDownActionId: {
                return dropDownInterval;
            }
            case dragToLeftActionId: {
                return dragToLeftInterval;
            }
            case dragToRightActionId: {
                return dragToRightInterval;
            }
        }
        return Long.MAX_VALUE;
    }
    
    public void rotateShape() {
        if(hasActiveTask()) {
            interruptActiveTask();
        }
        setActiveTask(rotateActionId);
        executeActiveTask();
        interruptActiveTask();
    }
    
    public void startDropDownShape() {
        if(hasActiveTask()) {
            interruptActiveTask();
        }
        setActiveTask(dropDownActionId);
        executeActiveTask();
    }
    
    public void stopDropDownShape() {
        if(currentActionId == dropDownActionId) {
            interruptActiveTask();
        }
    }
    
    public void startDragToLeftShape() {
        if(hasActiveTask()) {
            interruptActiveTask();
        }
        setActiveTask(dragToLeftActionId);
        executeActiveTask();
    }
    
    public void stopDragToLeftShape() {
        if(currentActionId == dragToLeftActionId) {
            interruptActiveTask();
        }
    }
    
    public void startDragToRightShape() {
        if(hasActiveTask()) {
            interruptActiveTask();
        }
        setActiveTask(dragToRightActionId);
        executeActiveTask();
    }
    
    public void stopDragToRightShape() {
        if(currentActionId == dragToRightActionId) {
            interruptActiveTask();
        }
    }
    
    private synchronized void setActiveTask(int taskId) {
        currentActionId = taskId;
    }
    
    private synchronized boolean hasActiveTask() {
        return currentActionId != noneActionId;
    }
    
    private synchronized void interruptActiveTask() {
        currentActionId = noneActionId;
    }
    
    public void destroy() {
        thread.interrupt();
        engine = null;
    }
}
