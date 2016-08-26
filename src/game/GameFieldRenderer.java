package game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author vkachenyuk
 */
public class GameFieldRenderer {
    
    private final Image cellImage;
    private final GameVisualConfig config;
    public GameFieldRenderer(GameVisualConfig config) {
        
        this.config = config;
        
        int[] cellRGBData = new int[] {0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 
                                       0x000000, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0x000000,
                                       0x000000, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0x000000,
                                       0x000000, 0xA8A8A8, 0xA8A8A8, 0xF8F8F8, 0xF8F8F8, 0xF8F8F8, 0xF8F8F8, 0xF8F8F8, 0xF8F8F8, 0xA8A8A8, 0xA8A8A8, 0x000000,
                                       0x000000, 0xA8A8A8, 0xA8A8A8, 0xF8F8F8, 0xF8F8F8, 0xF8F8F8, 0xF8F8F8, 0xF8F8F8, 0xF8F8F8, 0xA8A8A8, 0xA8A8A8, 0x000000,
                                       0x000000, 0xA8A8A8, 0xA8A8A8, 0xF8F8F8, 0xF8F8F8, 0xA8A8A8, 0xA8A8A8, 0x000000, 0x000000, 0xA8A8A8, 0xA8A8A8, 0x000000,
                                       0x000000, 0xA8A8A8, 0xA8A8A8, 0xF8F8F8, 0xF8F8F8, 0xA8A8A8, 0xA8A8A8, 0x000000, 0x000000, 0xA8A8A8, 0xA8A8A8, 0x000000,
                                       0x000000, 0xA8A8A8, 0xA8A8A8, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0xA8A8A8, 0xA8A8A8, 0x000000,
                                       0x000000, 0xA8A8A8, 0xA8A8A8, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0xA8A8A8, 0xA8A8A8, 0x000000,
                                       0x000000, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0x000000,
                                       0x000000, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0xA8A8A8, 0x000000,
                                       0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000, 0x000000};
        
        cellImage = Image.createRGBImage(cellRGBData, config.cellSize, config.cellSize, false);
    }
    
    public void drawField(byte[][] data, Graphics graphics) {
        graphics.setColor(config.fieldBGColor);
        graphics.fillRect(config.fieldPositionX, config.fieldPositionY, config.fieldWidth, config.fieldHeight);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != 0) {
                    graphics.drawImage(cellImage, config.fieldPositionX + config.cellSize * j, config.fieldPositionY + config.cellSize * i, 0);
                }
            }
        }
    }
    
    public void drawCurrentShapeInGameField(byte[][] currentShapeData, int positionX, int positionY, Graphics graphics) {
        for (int i = 0; i < currentShapeData.length; i++) {
            for (int j = 0; j < currentShapeData[i].length; j++) {
                if(currentShapeData[i][j] != 0) {
                    int appropriateFieldRowIndex = positionY + i;
                    int appropriateFieldColumnIndex = positionX + j;
                    graphics.drawImage(cellImage, config.fieldPositionX + appropriateFieldColumnIndex * config.cellSize, config.fieldPositionY + appropriateFieldRowIndex * config.cellSize, 0);
                }
            }
        }
    }
    
    public void drawNextShape(byte[][] nextShapeData, Graphics graphics) {
        
    }
}
