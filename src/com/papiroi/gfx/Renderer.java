/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papiroi.gfx;

import com.papiroi.Game;
import com.papiroi.entity.Asteroid;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


/**
 *
 * @author UGZB2
 */
public class Renderer {
    
    public void renderBackground(Graphics g){
        switch(Game.state){
            case MENU:
                Game.getInstance().getMenu().render(g);
                break;
            case GAME:
                break;
            case OPTIONS:
                Game.getInstance().getHelp().render(g);
                break;
            case PAUSE:
                break;
            default:
                g.setColor(Color.RED);
                g.drawString("UNKNOWN GAMESTATE", 150, 150);
                break;
        }
        
        
    }
    
    public void renderForeground(Graphics g){
        switch(Game.state){
            case MENU:
                break;
            case GAME:
                Game.getInstance().getPlayer().draw(g);
                
                //Asteroids
                for(int i = 0; i < Game.getInstance().getAsteroids().size(); i++){
                    Game.getInstance().getAsteroids().get(i).draw(g);
                }
                
                //Lazars
                for(int i = 0; i < Game.getInstance().getLasers().size(); i++){
                    Game.getInstance().getLasers().get(i).draw(g);
                }
                
                
                
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
    

