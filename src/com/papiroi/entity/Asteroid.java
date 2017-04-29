/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.papiroi.entity;

import com.papiroi.Game;
import com.papiroi.libs.Reference;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author REGGIE
 */
public class Asteroid{
    
    private int xCoordinate;
    private int yCoordinate;
    private int velX;
    private BufferedImage image;
    private static Random random = new Random();
    
    public static long lastAsteroidTime = 0;
    public static long timeBetweenAsteroids = Reference.secInNanoSec / 4;
    
    public static int asteroidLanes [][] = {
        {Game.WIDTH, (int)(Game.HEIGHT * 0.10), random.nextInt((5 - 2) + 1) + 2},
        {Game.WIDTH, (int)(Game.HEIGHT * 0.30), random.nextInt((5 - 2) + 1) + 2},
        {Game.WIDTH, (int)(Game.HEIGHT * 0.40), random.nextInt((5 - 2) + 1) + 2},
        {Game.WIDTH, (int)(Game.HEIGHT * 0.60), random.nextInt((5 - 2) + 1) + 2},
        {Game.WIDTH, (int)(Game.HEIGHT * 0.80), random.nextInt((5 - 2) + 1) + 2}
    };
    
    public static int nextAsteroidLane = 0;
   
    public Asteroid(int x, int y, int speed, BufferedImage image){
        xCoordinate = x;
        yCoordinate = y;
        velX = speed;
        this.image = image;
    }
    
    public void tick(){
        xCoordinate -= velX;
    }
        
    
    public void draw(Graphics g){
        g.drawImage(image, xCoordinate, yCoordinate, null);
    }
    
    public int getX(){
        return xCoordinate;
    }
    
    public int getY(){
        return yCoordinate;
    }
    
    public int getWidth(){
        return image.getWidth();
    }
    
    public int getHeight(){
        return image.getHeight();
    }
    
    public Rectangle getCollisionBox(){
        return new Rectangle(xCoordinate, yCoordinate, getWidth(), getHeight());
    }
    
}
