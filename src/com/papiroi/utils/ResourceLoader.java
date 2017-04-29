/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papiroi.utils;

import com.papiroi.libs.Fonts;
import com.papiroi.libs.Images;
import com.papiroi.libs.Reference;
import java.io.IOException;

/**
 *
 * @author UGZB2
 */
public class ResourceLoader {
    
    private static BufferedImageLoader imageLoader = new BufferedImageLoader();
    
    public static void loadImages(){
        
        try{
            Images.title = imageLoader.loadImage("title.png");
            
            //load images for play button
            Images.playButtonIdle = imageLoader.loadImage("playButtonIdle.png");
            Images.playButtonHover = imageLoader.loadImage("playButtonHover.png");
            Images.playButtonClicked = imageLoader.loadImage("playButtonClicked.png");
            
            //load images for option buttonset
            Images.optionButtonIdle = imageLoader.loadImage("optionButtonIdle.png");
            Images.optionButtonHover = imageLoader.loadImage("optionButtonHover.png");
            Images.optionButtonClicked = imageLoader.loadImage("optionButtonClicked.png");
            
            //load images for quit buttonset
            Images.quitButtonIdle = imageLoader.loadImage("quitButtonIdle.png");
            Images.quitButtonHover = imageLoader.loadImage("quitButtonHover.png");
            Images.quitButtonClicked = imageLoader.loadImage("quitButtonClicked.png");
            
            //load background image for menu
            Images.menuBackgroundImg = imageLoader.loadImage("menuBackgroundImg.png");
            //for game
            Images.bgMaikee = imageLoader.loadImage("bgMaikee.png");
            //for help
            Images.helpBG = imageLoader.loadImage("helpBG.png");
            
            //spritesheet
            Images.spritesheetTest = imageLoader.loadImage("test.png");
            
            //player image
            Images.spaceMaikee = imageLoader.loadImage("playerMaikee.png");
            
            //asteroid image
            Images.asteroidImage = imageLoader.loadImage("Asteroid.png");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void loadFonts(){
        Fonts.addFont(new Fonts("SHERWOOD.TTF"));
    }
    
}
