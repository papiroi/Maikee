/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.papiroi.entity;

import com.papiroi.libs.Reference;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author REGGIE
 */
public class Laserbeam{
    
    public static long lastLaserTime = 0; //sounds fun
    
    public static long laserReloadTime = Reference.secInNanoSec / 4;
    
    private int xCoordinate;
    private int yCoordinate;
    private BufferedImage image;
    
    private int velX = 15;
    
    public Laserbeam(int x, int y){
        xCoordinate = x;
        yCoordinate = y;
    }
    
    public Laserbeam(int x, int y, BufferedImage image){
        xCoordinate = x;
        yCoordinate = y;
        this.image = image;
    }
    
    public void tick(){
        xCoordinate += velX;
    }
    
    public void draw(Graphics g){
        //should be drawImage, but for the sake of lulz let's use fillRect
        g.setColor(Color.GREEN);
        g.fillRect(xCoordinate, yCoordinate, 64, 8);
    }
    
    public int getX(){
        return xCoordinate;
    }
    
    public int getY(){
        return yCoordinate;
    }
    
    public Rectangle collisionBox(){
        return new Rectangle(xCoordinate, yCoordinate, 64, 8);
    }
}
