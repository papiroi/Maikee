/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papiroi.screens;

import com.papiroi.Game;
import com.papiroi.input.MouseInput;
import com.papiroi.libs.Buttons;
import com.papiroi.libs.Images;
import com.papiroi.libs.Reference;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author UGZB2
 */
public class Menu {
    
    public Rectangle play, options, quit;
    private Buttons playButton, optionsButton, quitButton;
//    private boolean isClicked;
//    private static int centerX = Game.WIDTH / 2;
//    private static int centerY = Game.HEIGHT / 2;
    
    public Menu(){
        int fillerY = 200;
        play = new Rectangle(Reference.CENTER_X + 50, fillerY, Images.playButtonIdle.getWidth(), Images.playButtonIdle.getHeight());
        options = new Rectangle(Reference.CENTER_X + 50, fillerY +=60, Images.optionButtonIdle.getWidth(), Images.optionButtonIdle.getHeight());
        quit = new Rectangle(Reference.CENTER_X + 50, fillerY +=60, Images.quitButtonIdle.getWidth(), Images.quitButtonIdle.getHeight());
        
        //initialize buttons
        /**
         * @param idleImage etc - image to be loaded when button is idle, hover, clicked.
         */
        playButton = new Buttons(Images.playButtonIdle, Images.playButtonHover, Images.playButtonClicked);
        optionsButton = new Buttons(Images.optionButtonIdle, Images.optionButtonHover, Images.optionButtonClicked);
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
        g.drawImage(Images.menuBackgroundImg, 0, 0, null);
//        g.drawImage(Images.title, (Game.WIDTH - Images.title.getWidth())/2, 0, null); //draw title
        drawButton(g, playButton, play, "Play", 65);
        drawButton(g, optionsButton, options, "Options", 40);
        drawButton(g, quitButton, quit, "Quit", 65);
    }

    
    
}
