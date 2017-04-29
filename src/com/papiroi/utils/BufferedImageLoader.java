/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papiroi.utils;

import com.papiroi.Game;
import com.papiroi.libs.Reference;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author UGZB2
 */
public class BufferedImageLoader {
    private BufferedImage image;
    
    public BufferedImage loadImage(String imagePath) throws IOException{
        image = ImageIO.read(Game.class.getResource(Reference.SPRITE_LOCATION + imagePath));
        return image;
    }
    
}
