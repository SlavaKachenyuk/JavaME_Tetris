package engine.shapes;

/**
 *
 * @author vkachenyuk
 */
public class ShapeZ extends TetrisShape{
    public ShapeZ() {
        byte id = getId();
        states = new byte[][][] {{{id,id, 0},
                                  { 0,id,id},
                                  { 0, 0, 0}},
            
                                 {{ 0, 0,id},
                                  { 0,id,id},
                                  { 0,id, 0}}};
    }
    
    public byte getId() {
        return 6;
    }
}
