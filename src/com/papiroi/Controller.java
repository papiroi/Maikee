/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papiroi;

import com.papiroi.core.CoreObject;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author UGZB2
 */
public class Controller {
 
    private static ArrayList<CoreObject> objects = new ArrayList<>();
    
    public void tick(){
        for(CoreObject obj : objects){
            obj.tick();
        }
    }
    
    public void render(Graphics g){
        for(CoreObject obj : objects){
            obj.render(g);
        }
    }
    
    public static void addObject(CoreObject instance){
        objects.add(instance);
    }
    
    public static void removeObject(CoreObject instance){
        objects.remove(instance);
    }
    
    public static ArrayList<CoreObject> getObjects(){
        return objects;
    }
    
    public static void clearObjects(){
        objects.clear();
        
    }
        
}
