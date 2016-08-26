package engine.shapes;

/**
 *
 * @author vkachenyuk
 */
public class ShapeJ extends TetrisShape {
    public ShapeJ() {
        byte id = getId();
        states = new byte[][][] {{{ 0,id, 0},
                                  { 0,id, 0},
                                  {id,id, 0}},
            
                                 {{id, 0, 0},
                                  {id,id,id},
                                  { 0, 0, 0}},
                                 
                                 {{ 0,id,id},
                                  { 0,id, 0},
                                  { 0,id, 0}},
                                 
                                 {{ 0, 0, 0},
                                  {id,id,id},
                                  { 0, 0,id}}};
    }
    
    public byte getId() {
        return 4;
    }
}
