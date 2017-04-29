/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papiroi.utils;

import java.awt.image.BufferedImage;

/**
 *
 * @author UGZB2
 */
public class SpriteSheet {
    
    private BufferedImage image;
    private int width, height;
    
    public SpriteSheet(BufferedImage image, int width){
        this.image = image;
        this.width = width;
        this.height = width;
    }
    
    public SpriteSheet(BufferedImage image, int width, int height){
        this.image = image;
        this.width = width;
        this.height = height;
    }
    
    public BufferedImage getSprite(int col, int row){
        return image.getSubimage((col*width)-width,(row*height)-height , width, height);
    }
    
}
