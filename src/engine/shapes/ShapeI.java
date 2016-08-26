/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.shapes;

/**
 *
 * @author vkachenyuk
 */
public class ShapeI extends TetrisShape{
    public ShapeI() {
        byte id = getId();
        states = new byte[][][] {{{ 0,id, 0, 0},
                                  { 0,id, 0, 0},
                                  { 0,id, 0, 0},
                                  { 0,id, 0, 0}},
            
                                 {{ 0, 0, 0, 0},
                                  {id,id,id,id},
                                  { 0, 0, 0, 0},
                                  { 0, 0, 0, 0}}};
    }
    
    public byte getId() {
        return 7;
    }
}
