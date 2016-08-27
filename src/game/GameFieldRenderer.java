package game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import engine.TetrisGameEngine;

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
        graphics.fillRect(config.secondFieldPositionX, config.secondFieldPositionY, config.secondFieldWidth, config.secondFieldHeight);
        graphics.fillRect(config.scoreFieldPositionX, config.scoreFieldPositionY, config.scoreFieldWidth, config.scoreFieldHeight);
        
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
        for (int i = 0; i < nextShapeData.length; i++) {
            for (int j = 0; j < nextShapeData[i].length; j++) {
                if(nextShapeData[i][j] != 0) {
                    int appropriateFieldRowIndex = i;
                    int appropriateFieldColumnIndex = j;
                    if (nextShapeData.length == 4){
                        graphics.drawImage(cellImage, config.secondFieldPositionX + 15 + appropriateFieldColumnIndex * config.cellSize, config.secondFieldPositionY + 7 + appropriateFieldRowIndex * config.cellSize, 0);
                    } else if (nextShapeData[i].length == 4){
                        graphics.drawImage(cellImage, config.secondFieldPositionX + 3 + appropriateFieldColumnIndex * config.cellSize, config.secondFieldPositionY + 17 + appropriateFieldRowIndex * config.cellSize, 0);
                    } else if(nextShapeData.length == 2 && nextShapeData[i].length == 2){
                        graphics.drawImage(cellImage, config.secondFieldPositionX + 19 + appropriateFieldColumnIndex * config.cellSize, config.secondFieldPositionY + 17 + appropriateFieldRowIndex * config.cellSize, 0);
                    } else {
                        graphics.drawImage(cellImage, config.secondFieldPositionX + 13 + appropriateFieldColumnIndex * config.cellSize, config.secondFieldPositionY + 13 + appropriateFieldRowIndex * config.cellSize, 0);
                    }
                }
            }
        }
    }
    public void textField(Graphics graphics) {
        graphics.setColor(0);
        graphics.setStrokeStyle(Graphics.SOLID);
        graphics.drawString("Score:", 135, 79, graphics.LEFT);
    }
    
    public void scoreField(int score,Graphics graphics) {
        //graphics.drawString(score, 135, 79, 0);
    }
}
