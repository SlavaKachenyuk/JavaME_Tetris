package engine;

import engine.shapes.ShapeI;
import engine.shapes.ShapeJ;
import engine.shapes.ShapeL;
import engine.shapes.ShapeO;
import engine.shapes.ShapeS;
import engine.shapes.ShapeT;
import engine.shapes.ShapeZ;
import engine.shapes.ShapeB;
import engine.shapes.TetrisShape;
import java.util.Random;

/**
 *
 * @author vkachenyuk
 */
public class ShapeGenerator {
    
    private Random random = new Random();
    private int[] shapeTypes = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
    
    public TetrisShape generateRandomShape() {
        
        int randomShapeType = shapeTypes[random.nextInt(shapeTypes.length)];
        TetrisShape randomShape = null;
        switch(randomShapeType) {
            case 1: {
                randomShape = new ShapeO();
                break;
            }
            case 2: {
                randomShape = new ShapeT();
                break;
            }
            case 3: {
                randomShape = new ShapeL();
                break;
            }
            case 4: {
                randomShape = new ShapeJ();
                break;
            }
            case 5: {
                randomShape = new ShapeS();
                break;
            }
            case 6: {
                randomShape = new ShapeZ();
                break;
            }
            case 7: {
                randomShape = new ShapeI();
                break;
            }
            case 8: {
                randomShape = new ShapeB();
                break;
            }
        }
        int numRotations = random.nextInt(4);
        while(numRotations > 0) {
            randomShape.rotate();
            numRotations--;
        }
        return randomShape;
    }
    
}
