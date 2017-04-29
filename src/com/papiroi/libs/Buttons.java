/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papiroi.libs;

import java.awt.image.BufferedImage;

/**
 *
 * @author UGZB2
 */
public class Buttons {
    
    public BufferedImage idle, hover, clicked;
    public boolean isClicked = false;
    
    public Buttons(BufferedImage idleImg, BufferedImage hoverImg, BufferedImage clickedImg){
        idle = idleImg;
        hover = hoverImg;
        clicked = clickedImg;
    }
    
    
    
}
