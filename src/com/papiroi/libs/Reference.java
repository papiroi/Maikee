/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papiroi.libs;

import com.papiroi.Game;
import java.net.URL;

/**
 *
 * @author UGZB2
 */
public class Reference {
    
    public static final long secInNanoSec = 1000000000L;
    public static final int CENTER_X = Game.WIDTH / 2;
    public static final int CENTER_Y = Game.HEIGHT / 2;
    
    
    public static final URL GAME_RESOURCE = Game.class.getResource("resources/");
    public static final String RESOURCE_LOCATION = "resources/";
    public static final String SPRITE_LOCATION = RESOURCE_LOCATION + "sprites/";
    public static final String FONT_LOCATION = RESOURCE_LOCATION + "fonts/";
    
    
    public static int percentHealth = 0;
}
