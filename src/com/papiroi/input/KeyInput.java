/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papiroi.input;

import com.papiroi.Controller;
import com.papiroi.Game;
import com.papiroi.entity.Asteroid;
import com.papiroi.entity.Laserbeam;
import com.papiroi.entity.Player;
import com.papiroi.libs.Identities;
import com.papiroi.libs.Images;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author UGZB2
 */
public class KeyInput extends KeyAdapter{
    
    
    
    public KeyInput(){
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        
        switch(Game.state){
            case MENU:
                break;
            case GAME:
                Game.getInstance().getPlayer().playerKeyPressed(e);
                break;
            case OPTIONS:
                break;
            case PAUSE:
                break;
            default:
                throw new AssertionError(Game.state.name());  
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        
        switch(Game.state){
            case MENU:
                break;
            case GAME:
                Game.getInstance().getPlayer().playerKeyReleased(e);
                break;
            case OPTIONS:
                break;
            case PAUSE:
                break;
            default:
                throw new AssertionError(Game.state.name());  
        }
    }
              
    
}
