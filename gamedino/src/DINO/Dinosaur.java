
package DINO;
import java.io.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;


public class Dinosaur extends GameScreen {
    
    private BufferedImage dinos;
    
    private  Animation dino_anim;
    
    private Dino dino; // khai bao doi tuong
    
    private Ground ground; // khai bao doi tuong
    
    private final ObstaclesGroup obstaclesgroup;
    
    private final CloudGroup cloudgroup;
    
    private final TreeGroup treegroup;
    
    private final DiamondGroup diagroup;
    
    private FileWriter write;
    
    private File read ;
    
    private int point;
    
    private int BEGIN_SCREEN = 0;
    
    private int GAMEPLAY_SCREEN = 1;
    
    private int GAMEOVER_SCREEN = 2;
    
    private int CurrentScreen = BEGIN_SCREEN;
    
    public static int posx = 50,posy = 320;
    
    public static float gamespeed = (float) 2.0;
    
    public String data;
    
    public Dinosaur() throws IOException{
        super(780,500);
        
        try {
            dinos = ImageIO.read(new File("images/ha.png"));
            
            write = new FileWriter("images/maxp.txt");
            /*
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            */
            
            
        
        } catch (IOException ex) {}
        
        
    
        
        dino_anim = new Animation(50);
        AFrameOnImage f ;
        
        f = new AFrameOnImage(0,0,104,91);
        dino_anim.AddFrame(f);
        f = new AFrameOnImage(104,0,104,91);
        dino_anim.AddFrame(f);
        f = new AFrameOnImage(208,0,104,91);
        dino_anim.AddFrame(f);
        f = new AFrameOnImage(0,0,104,91);
        dino_anim.AddFrame(f);
        
        dino = new Dino(posx,posy,80,90);
        
        ground = new Ground();
        
        obstaclesgroup = new ObstaclesGroup();
        cloudgroup = new CloudGroup();
        treegroup = new TreeGroup();
        diagroup = new DiamondGroup();
        BeginGame();
    }
    
    
    
    
    public static void main(String args[]) throws IOException {
        Dinosaur mh = new Dinosaur();
        
    }
    
    /**
     *
     * @param g2
     */

    
    private void resetGame(){
        dino.setPos(posx, posy);
        dino.setLive(true);
    }
    

    @Override
    public void GAME_UPDATE(long deltaTime){
        
        
        if(CurrentScreen == BEGIN_SCREEN){
            resetGame();
            
        }else if(CurrentScreen == GAMEPLAY_SCREEN){
            if(dino.isLive == true) {
                dino_anim.Update_Me(deltaTime);
                dino.update(deltaTime);
                ground.Update();
                obstaclesgroup.update();
                cloudgroup.update();
                treegroup.update();
                diagroup.update();
            }
            
 
            // Print the string
            
            
            
            for(int i = 0 ;i<7;i++){      // xu ly va cham voi vat can
                if(dino.getRect().intersects(obstaclesgroup.getxrs(i).getRect())){
                    dino.setLive(false);
                    CurrentScreen = GAMEOVER_SCREEN;
                    
                }
            }
            
            for(int i = 0 ;i< 7 ;i++){          // xu ly tang diem 
                if(dino.getPosX()>obstaclesgroup.getxrs(i).getPosX() && !obstaclesgroup.getxrs(i).getisbehind() ){
                    point+=00;
                    obstaclesgroup.getxrs(i).setisbehind(true);
                
            }
            }    
            for(int i = 0 ;i<10 ;i++){
                if(dino.getRect().intersects(diagroup.getdia(i).getRect())){
                    diagroup.getdia(i).setisvc(true);point+=1;
                }  
            }
        }
        else{
            CurrentScreen = BEGIN_SCREEN;
            resetGame(); 
        } 
    }

    public void paint(Graphics2D g2){
        g2.setColor(Color.decode("#b8daef"));
        g2.fillRect(0, 0, 780, 500);
        
        dino_anim.PaintAnims((int)dino.getPosX(), (int)dino.getPosY() , dinos, g2, 0, 0);
        ground.Paint(g2);
        obstaclesgroup.paint(g2);
        cloudgroup.paint(g2);
        treegroup.paint(g2);
        diagroup.paint(g2);
    }
    
    
    @Override 
    public void GAME_PAINT(Graphics2D g2) {
        
        paint(g2);
        
        
        
        if(CurrentScreen == BEGIN_SCREEN){
            g2.setColor(Color.red);
            g2.drawString("Press space to play game", 100, 100);
        }
        if(CurrentScreen == GAMEOVER_SCREEN){
            g2.setColor(Color.BLACK);
            g2.drawString("Press space to continue", 100, 100);
        }
        g2.setColor(Color.red);
        g2.drawString( " " + point, 40,30);
    }

    
    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if(KEY_PRESSED == Event){ 
            
            if(CurrentScreen == BEGIN_SCREEN){
                CurrentScreen = GAMEPLAY_SCREEN;
            }else if(CurrentScreen == GAMEPLAY_SCREEN){
                if(dino.isLive == true ){
                    dino.setIsJumping(true);
                    dino.setisdrop(false);
                }
            }else if(CurrentScreen == GAMEOVER_SCREEN){
                CurrentScreen = BEGIN_SCREEN;
                resetGame();
            }   
        }  
    }
        
}
