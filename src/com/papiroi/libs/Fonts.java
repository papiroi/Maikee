/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papiroi.libs;

import com.papiroi.Game;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author UGZB2
 */
public class Fonts {
    
    private static ArrayList<Fonts> fontList = new ArrayList<>();
    
    private static String fontPath;
    
    public Fonts(String filePath){
        Fonts.fontPath = Reference.FONT_LOCATION + filePath;
        registerFont();
    }
    
    private void registerFont(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        try{
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/com/papiroi/" + fontPath)));
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
    }
    
    public static void addFont(Fonts font){
        fontList.add(font);
    }
}
