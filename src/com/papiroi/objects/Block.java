/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papiroi.objects;

import com.papiroi.core.CoreObject;
import com.papiroi.gfx.Texture;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author UGZB2
 */
public class Block extends CoreObject{
    
    
    private BufferedImage image;
    public Block(int x, int y, int id, Texture tex, BufferedImage image){
        super(x, y, id, tex);
        this.image = image;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, null);
    }
    
    public Rectangle getTopBounds(){
        return new Rectangle(x, y, width, height/2);
    }
    
    
}
