/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papiroi.gfx;

import com.papiroi.libs.Images;
import com.papiroi.utils.SpriteSheet;
import java.awt.image.BufferedImage;

/**
 *
 * @author UGZB2
 */
public class Texture {
    
    private SpriteSheet sheetTest;
    public BufferedImage blockDirt;
    public BufferedImage blockStone;
    public BufferedImage playerMaikee;
    
    public Texture(){
        sheetTest = new SpriteSheet(Images.spritesheetTest, 32);
        
        initTextures();
    }
    
    private void initTextures(){
        blockDirt = sheetTest.getSprite(1,1);
        blockStone = sheetTest.getSprite(2,1);
//        playerMaikee = sheetTest.getSprite(3,1);
    }
    
}
