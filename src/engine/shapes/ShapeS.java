package engine.shapes;

/**
 *
 * @author vkachenyuk
 */
public class ShapeS extends TetrisShape{
    public ShapeS() {
        byte id = getId();
        states = new byte[][][] {{{ 0,id,id},
                                  {id,id, 0},
                                  { 0, 0, 0}},
            
                                 {{id, 0, 0},
                                  {id,id, 0},
                                  { 0,id, 0}}};
        
    }
    
    public byte getId() {
        return 5;
    }
}
