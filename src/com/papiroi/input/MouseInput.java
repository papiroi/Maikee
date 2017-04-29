/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papiroi.input;

import com.papiroi.Game;
import com.papiroi.enums.GameState;
import com.papiroi.screens.Help;
import com.papiroi.screens.Menu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author UGZB2
 */
public class MouseInput extends MouseAdapter{
    
    public static int MOTION_X, MOTION_Y, CLICK_X, CLICK_Y;
    private Menu menu = Game.getInstance().getMenu();
    private Help help = Game.getInstance().getHelp();
    
    @Override
    public void mouseClicked(MouseEvent e){
        int mouse = e.getButton();
        CLICK_X = e.getX();
        CLICK_Y = e.getY();
        
        
        if(mouse == MouseEvent.BUTTON1){
            
            switch(Game.state){
                case MENU:
                    if(menu.play.contains(CLICK_X, CLICK_Y)){
                        Game.getInstance().restart();
                        Game.state = GameState.GAME;
                    }else if(menu.options.contains(CLICK_X, CLICK_Y)){
                        Game.state = GameState.OPTIONS;
                    }else if(menu.quit.contains(CLICK_X, CLICK_Y)){
                        System.exit(0);
                    }
                    break;
                case GAME:
                    break;
                case OPTIONS:
                    if(help.quit.contains(CLICK_X, CLICK_Y)){
                        Game.state = GameState.MENU;
                    }
                    break;
                case PAUSE:
                    break;
                default:
                    throw new AssertionError(Game.state.name());
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MOTION_X = e.getX();
        MOTION_Y = e.getY();
    }
    
    
}
