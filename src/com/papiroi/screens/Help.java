/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.papiroi.screens;

import com.papiroi.input.MouseInput;
import com.papiroi.libs.Buttons;
import com.papiroi.libs.Images;
import com.papiroi.libs.Reference;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author REGGIE
 */
public class Help {
    
    public Rectangle quit;
    private Buttons quitButton;
//    private boolean isClicked;
//    private static int centerX = Game.WIDTH / 2;
//    private static int centerY = Game.HEIGHT / 2;
    
    public Help(){
        int fillerY = 200;
        quit = new Rectangle(Reference.CENTER_X - 250, fillerY +=200, Images.quitButtonIdle.getWidth(), Images.quitButtonIdle.getHeight());
        
        //initialize buttons
        /**
         * @param idleImage etc - image to be loaded when button is idle, hover, clicked.
         */
        quitButton = new Buttons(Images.quitButtonIdle, Images.quitButtonHover, Images.quitButtonClicked);
    }
    
    
    public void drawButton(Graphics g, Buttons button, Rectangle rect, String text, int offsetX){
        if(rect.contains(MouseInput.MOTION_X, MouseInput.MOTION_Y)){
            g.drawImage(button.hover, rect.x, rect.y, null);
        }else{
            g.drawImage(button.idle, rect.x, rect.y, null);
        }
    }
    
    
    public void render(Graphics g){
        g.drawImage(Images.helpBG, 0, 0, null);
//        g.drawImage(Images.title, (Game.WIDTH - Images.title.getWidth())/2, 0, null); //draw title
        drawButton(g, quitButton, quit, "Quit", 65);
    }
}
