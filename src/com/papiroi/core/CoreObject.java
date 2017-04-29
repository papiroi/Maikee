/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papiroi.core;

import com.papiroi.gfx.Texture;
import java.awt.Graphics;

/**
 *
 * @author UGZB2
 */
public abstract class CoreObject {
    
    protected int x, y, velX, velY;
    protected int id;
    protected int width = 32;
    protected int height = 32;
    protected Texture tex;
    
    public CoreObject(int x, int y, int id, Texture tex){
        this.x = x;
        this.y = y;
        this.id = id;
        this.tex = tex;
    }
    
    
    
    public abstract void tick();
    public abstract void render(Graphics g);
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public long getId() {
        return id;
    }

}