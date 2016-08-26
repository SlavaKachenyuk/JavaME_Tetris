package engine.shapes;

/**
 *
 * @author vkachenyuk
 */
public class ShapeT extends TetrisShape {

    public ShapeT() {
        byte id = getId();
        states = new byte[][][] {{{ 0,id, 0},
                                  {id,id,id},
                                  { 0, 0, 0}},
            
                                 {{ 0,id, 0},
                                  { 0,id,id},
                                  { 0,id, 0}},
                                 
                                 {{ 0, 0, 0},
                                  {id,id,id},
                                  { 0,id, 0}},
                                 
                                 {{ 0,id, 0},
                                  {id,id, 0},
                                  { 0,id, 0}}};
    }
    
    public byte getId() {
        return 2;
    }
}
