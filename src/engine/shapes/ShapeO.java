package engine.shapes;

/**
 *
 * @author vkachenyuk
 */
public class ShapeO extends TetrisShape {
    
    public ShapeO() {
        positionX = 4;
        byte id = getId();
        states = new byte[][][] {{{id,id},
                                  {id,id}}};
    }
    
    public byte getId() {
        return 1;
    }
}
