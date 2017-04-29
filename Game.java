/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papiroi;

import com.papiroi.entity.Player;
import com.papiroi.gfx.Renderer;
import com.papiroi.enums.GameState;
import com.papiroi.gfx.Texture;
import com.papiroi.input.KeyInput;
import com.papiroi.screens.Menu;
import com.papiroi.utils.ResourceLoader;
import com.papiroi.input.MouseInput;
import com.papiroi.libs.Identities;
import com.papiroi.objects.Block;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 *
 * @author UGZB2
 */
public class Game extends Canvas implements Runnable{
    
    public static JFrame frame = new JFrame();
    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 4 * 3;
    public static final String TITLE = "The Adventures of Maikee";
    
    private static Game game = new Game();
    
    public static GameState state = GameState.MENU;
    
    private boolean running = false;
    private Thread thread;
    private Renderer gfx;
    private Menu menu;
    private Controller controller = new Controller();
    private Texture texture;
    
    public static Game getInstance(){
        return game;
    }
    
    public Menu getMenu(){
        return menu;
    }
    
    public Controller getController(){
        return controller;
    }
    
    public void init(){
        ResourceLoader.loadImages();
        ResourceLoader.loadFonts();
        texture = new Texture();
        menu = new Menu();
        gfx = new Renderer();
        MouseInput mouse = new MouseInput();
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
        
        int x=0;
        for(int k = 1; k<=20; k++){
            Controller.addObject(new Block(x, HEIGHT - 64, Identities.BLOCK_STONE, texture, texture.blockStone));
            x+=32;
        }
        Controller.addObject(new Player(100, 100, Identities.PLAYER, texture));
        this.addKeyListener(new KeyInput());
    }
    
    public void tick(){
        if(state==GameState.GAME){
            controller.tick();
        }
    }
    
    
    
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs==null){
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        g.setColor(new Color(10, 0, 50));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        ///////////////////////////////
        
        gfx.renderBackground(g);
        gfx.renderForeground(g);
        
        g.dispose();
        bs.show();
    }
    
    @Override
    public void run(){
        init();
        long lastTime = System.nanoTime();
        final double numTicks = 60.0;
        double n = 1000000000 / numTicks;
        double delta = 0;
        int frames = 0;
        int ticks = 0;
        long timer = System.currentTimeMillis();
        
        while(running){
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/n;
            lastTime = currentTime;
            
            if(delta >= 1){
                tick();
                ticks++;
                delta--;
            }
            
            render();
            frames++;
        
            if(System.currentTimeMillis()-timer > 1000){
                timer+=1000;
                System.out.println(ticks + " Ticks, FPS: " + frames);
                
//                frame.setTitle(TITLE+"     Ticks: "+ticks+"    Frames: "+frames);
                ticks = 0;
                frames = 0;
            }
        }
        stop();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        
        Image icon = toolkit.getImage(Game.class.getResource("resources/cursor.png"));
        
        Image cursor = toolkit.getImage(Game.class.getResource("resources/cursor.png"));
        
        frame.add(game);
        frame.setTitle(TITLE);
        frame.setIconImage(icon);
//        frame.setCursor(toolkit.createCustomCursor(cursor, new Point(frame.getX(), frame.getY()), "cursor"));
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.pack();
        game.start();
    }
    
    private synchronized void start(){
        if(running){
            return;
        }
        else{
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    private synchronized void stop(){
        if(!running){
            return;
        }
        else{
            running = false;
        }
        
        try{
            thread.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        System.exit(0);
    }
}
