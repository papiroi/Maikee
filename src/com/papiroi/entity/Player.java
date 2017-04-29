/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papiroi.entity;

import com.papiroi.Game;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author UGZB2
 */
public class Player{
    
    private boolean[] keyDown = new boolean[2];

    private int xCoordinate;
    private int yCoordinate;
    
    private int velX = 0;
    private int velY = 0;
    
    private BufferedImage image;
    
    public Player(){
        
    }
    
    public Player(int x, int y, BufferedImage image){
        xCoordinate = x;
        yCoordinate = y;
        
        this.image = image;
    }
    
    public void draw(Graphics g){
        g.drawImage(image, xCoordinate, yCoordinate, null);
    }
    
    public void tick(){
        yCoordinate += velY;
        
    }

    
    
    public void playerKeyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
                if(key == KeyEvent.VK_W){
                    velY = -5;
                    keyDown[0] = true;
                }
                if(key == KeyEvent.VK_S){
                    velY = 5;
                    keyDown[1] = true;
                }
                if(key == KeyEvent.VK_A){
//                    player.setVelX(0);
//                    keyDown[0] = true;
                }
                if(key == KeyEvent.VK_D){
//                    player.setVelX(5);
//                    keyDown[1] = true;
                }
                if(key == KeyEvent.VK_SPACE){
                   Game.getInstance().shootLaser();
                }
    }
    
    public void playerKeyReleased(KeyEvent e){
        int key = e.getKeyCode();
                if(key == KeyEvent.VK_W){
                    velY = 0;
                    keyDown[0] = false;
                }
                if(key == KeyEvent.VK_S){
                    velY = 0;
                    keyDown[1] = false;
                }
                if(key == KeyEvent.VK_A){
//                    keyDown[0] = false;
                }
                if(key == KeyEvent.VK_D){
//                    keyDown[1] = false;
                }
                
                if(keyDown[0] && !keyDown[1]){
                    velY = -5;
                }
                
                if(!keyDown[0] && keyDown[1]){
                    velY = 5;
                }
                
                if(!keyDown[0] && !keyDown[1]){
                    velY = 0;
                }
        
    }
    
    public int getX(){
        return xCoordinate;
    }
    
    public int getY(){
        return yCoordinate;
    }
    
    public void setX(int x){
        xCoordinate = x;
    }
    
    public void setY(int y){
        yCoordinate = y;
    }
    
    public void setVelX(int x){
        velX = x;
    }
    
    public void setVelY(int y){
        velY = y;
    }
    
    public Rectangle collisionBox(){
        return new Rectangle(xCoordinate, yCoordinate + image.getHeight()/2, image.getWidth(), image.getHeight()/2);
    }
    
}
