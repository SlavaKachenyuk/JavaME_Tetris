package engine.shapes;

/**
 *
 * @author vkachenyuk
 */
public abstract class TetrisShape {
    
    public static final int initialPositionX = 3;
    public static final int initialPositionY = 0;
    
    public int positionX = initialPositionX;
    public int positionY = initialPositionY;
    
    protected byte[][][] states;
    protected int statePosition = 0;
    
    public void rotate() {
        statePosition = nextRotationStateIndex();
    }
    
    public byte[][] getNextRotationState() {
        
        return states[nextRotationStateIndex()];
    }
    
    private int nextRotationStateIndex() {
        return (statePosition + 1 < states.length) ? statePosition + 1 : 0;
    }
    
    public byte[][] getData() {
        
        return states[statePosition];
    }
    
    public abstract byte getId();
}
