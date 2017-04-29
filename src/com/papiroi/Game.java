/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papiroi;

import com.papiroi.entity.Asteroid;
import com.papiroi.entity.Laserbeam;
import com.papiroi.entity.Player;
import com.papiroi.gfx.Renderer;
import com.papiroi.enums.GameState;
import com.papiroi.gfx.Texture;
import com.papiroi.input.KeyInput;
import com.papiroi.screens.Menu;
import com.papiroi.utils.ResourceLoader;
import com.papiroi.input.MouseInput;
import com.papiroi.libs.Images;
import com.papiroi.screens.Help;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author UGZB2
 */
public class Game extends Canvas implements Runnable{
    
    //Heto yung nag-iinitialize ng JFrame - yung mismong window ng laro
    public static JFrame frame = new JFrame();
    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 4 * 3;
    public static final String TITLE = "The Adventures of Maikee in Outer Space";
    
    //Declare ng instance ng game
    private static Game game = new Game();
    
    //Ito yung variable para sa current state ng laro. Possbile values: MENU, OPTIONS(help), GAME
    public static GameState state = GameState.MENU;
    
    //Ito yung declaration ng objects ng game
    private boolean running = false;
    private Thread thread;
    private Renderer gfx;
    private Menu menu;
    private Controller controller;
    private Texture texture;
    private Help help;
    
    private Font font;
    private int score = 0;
    
    private Player player;
    private ArrayList<Asteroid> asteroids;
    private ArrayList<Laserbeam> lasers;
    
    
    
    //ito yung x-coordinate value ng background image
    private int bgX = 0;
    
    //create instance ng Random object para sa random number generator
    private Random random = new Random();
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //Following methods are getter methods -
    //special function na nagrereturn ng encapsulated value (i.e. private) para magamit ng ibang class.
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //method para kunin ang instance ng Game
    public static Game getInstance(){
        return game;
    }
   
    //method para kunin ang instance ng Menu
    public Menu getMenu(){
        return menu;
    }
    
    //method para kunin ang instance ng Help
    public Help getHelp(){
        return help;
    }
    
    //method para kunin ang instance ng Controller
    public Controller getController(){
        return controller;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    //Mga listahan ng Objects. Ano ang ArrayList? Halos pareho lang sila ng Array kaso immutable (i.e. di pwede dagdag-bawas) ang array.
    //Ang ArrayList, pwedeng pwede.
    
    public ArrayList<Asteroid> getAsteroids(){
        return asteroids;
    }
    
    public ArrayList<Laserbeam> getLasers(){
        return lasers;
    }
    
    
    
    /**
     * Initialize lahat ng declared objects - 
     * gawa ng instance para sa bawat isa.
     */
    public void init(){
        ResourceLoader.loadImages();
        ResourceLoader.loadFonts();
        texture = new Texture();
        menu = new Menu();
        help = new Help();
        gfx = new Renderer();
        font = new Font("monospaced", Font.BOLD, 18);
        controller = new Controller();
        MouseInput mouse = new MouseInput();
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
        
        lasers = new ArrayList<>();
        asteroids = new ArrayList<>();
        player = new Player(100, 100, Images.spaceMaikee);
        
        this.addKeyListener(new KeyInput());
    }
    
    /**
     * Kapag na-dedo ka... heto ang method.
     * Resets variables when invoked.
     */
    public void restart(){
        lasers.clear();
        asteroids.clear();
        
        player.setX(100);
        player.setY(100);
        player.setVelY(0);
        
        Laserbeam.lastLaserTime = 0;
        
        score = 0;
    }
    
    /**
     * Update logic ng game.
     * 
     */
    public void tick(){
        if(state==GameState.GAME){
            player.tick();
            checkBounds();
            addAsteroids();
            tickAsteroids();
            tickLasers();
        }
        
        bgX -= 1;
    }
    
    //Method na nagtse-check ng collision between objects
    public void checkBounds(){
        
        //check if shuttle hits top bound
        if(player.getY() <= 0){
            player.setVelY(0);
            player.setY(0);
        }
        
        //check if shuttle hits bottom bound
        if(player.getY() >= Game.HEIGHT - 90){
            player.setVelY(0);
            player.setY(Game.HEIGHT - 90);
        }
        
        for(int i = 0; i < asteroids.size(); i++){
            Asteroid asteroid = asteroids.get(i);
            
            if(asteroid.getX() <= 0 - asteroid.getWidth()){ //Kapag lumagpas na ang 'asteroid sa screen...
                asteroids.remove(i); //...saka natin siya aalisin.
            }
            
            if(asteroid.getCollisionBox().intersects(player.collisionBox())){ //Kapag nagkabunggo ang asteroid at spaceship ni Maikee...
                JOptionPane.showMessageDialog(null, "You crashed!!", "Game over", JOptionPane.PLAIN_MESSAGE); //...JOptionPane(i.e. ipamukha) mo sa player na nag-crash siya. >:D
                Game.state = GameState.MENU; //Balik sa main menu
            }
            
            for(int j = 0; j < lasers.size(); j++){ //I-check ang mga laser beam isa isa kung...
                Laserbeam laser = lasers.get(j);
                
                if(laser.collisionBox().intersects(asteroid.getCollisionBox())){ //...bumangga ba ito sa asteroid. Kapag bumangga...
                    asteroids.remove(i); //...burahin si Asteroid number 'i'...
                    lasers.remove(j); //...gayundin si laser beam. Masyado naman atang overpowered kung tatagos lang si laserbeam.
                    score += 10; //Dagdagan ng score kung makakasira si laser (yep, hindi ikaw) ng asteroid.
                }
            }
        }
        
        
            
        
    }
    
    /*
    Method par
    */
    
    public void addAsteroids(){ 
        if(System.nanoTime() - Asteroid.lastAsteroidTime >= Asteroid.timeBetweenAsteroids){
            asteroids.add(new Asteroid(Asteroid.asteroidLanes[Asteroid.nextAsteroidLane][0] + random.nextInt(200), Asteroid.asteroidLanes[Asteroid.nextAsteroidLane][1], random.nextInt((5 - 2) + 1) +2, Images.asteroidImage));
            Asteroid.lastAsteroidTime = System.nanoTime();
            
            Asteroid.nextAsteroidLane = random.nextInt(Asteroid.asteroidLanes.length);
            
//            Asteroid.nextAsteroidLane++;
//            
//            if(Asteroid.nextAsteroidLane >= Asteroid.asteroidLanes.length){
//                Asteroid.nextAsteroidLane = 0;
//            }
            
        }
    }
    
    public void tickAsteroids(){
        for(int i = 0; i<asteroids.size();i++){
                asteroids.get(i).tick();
            }
    }
    
    public void shootLaser(){
        if(System.nanoTime() - Laserbeam.lastLaserTime >= Laserbeam.laserReloadTime){
            lasers.add(new Laserbeam(player.getX()+50, player.getY()+50));
            Laserbeam.lastLaserTime = System.nanoTime();
        }
    }
    
    public void tickLasers(){
        for(int i = 0; i<lasers.size();i++){
                lasers.get(i).tick();
            }
    }
    
    
    /**
     *Method para sa pagdisplay ng graphics - i.e. lahat lahat ng nakikita mo sa monitor. 
     */
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs==null){
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        //draw scrolling background image ng game
        g.drawImage(Images.bgMaikee, bgX, 0, null);
        g.drawImage(Images.bgMaikee, bgX + WIDTH, 0, null);
        if(bgX<= -1 * WIDTH){
            bgX = bgX + WIDTH;
        }
//        g.setColor(Color.GREEN);
//        g.fillRect(20,20, Reference.percentHealth, 16);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("Score: "+score, 20, 20);
        
        ///////////////////////////////
        
        gfx.renderBackground(g);
        gfx.renderForeground(g);
        
        
        
        g.dispose();
        bs.show();
    }
    
    /**
     *Heto ang MAIN thread ng game. Ano ang thread? Well ang thread ay sub-instance ng isang process...siguro.
     */
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
     * Alam naman na siguro kung ano ang main method, 'no? :P
     * Dito natin idodrowing yung frame - yung size neto, atbp. pati yung cursor na may mukha ni Maikee. :P
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
    
    /**
     * Ito yung mag-iistart ng Game thread natin.
     */
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
    
    /**
     * Ito yung... well... mag-iistop ng thread. Duh.
     */
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
