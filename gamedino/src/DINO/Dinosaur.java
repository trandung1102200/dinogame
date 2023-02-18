
package DINO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;


public class Dinosaur extends GameScreen {
    
    private BufferedImage dinos;
    
    private  Animation dino_anim;
    
    private Dino dino; // khai bao doi tuong
    
    private Ground ground; // khai bao doi tuong
    
    private ObstaclesGroup obstaclesgroup;
    
    private int BEGIN_SCREEN = 0;
    
    private int GAMEPLAY_SCREEN = 1;
    
    private int GAMEOVER_SCREEN = 2;
    
    private int CurrentScreen = BEGIN_SCREEN;
    
    public static int posx = 10,posy = 320;
    
    
    public Dinosaur() throws IOException{
        super(780,500);
        
        try {
            dinos = ImageIO.read(new File("images/ha.jpg"));
            
        
        } catch (IOException ex) {}
        
        dino_anim = new Animation(100);
        AFrameOnImage f ;
        
        f = new AFrameOnImage(0,0,104,91);
        dino_anim.AddFrame(f);
        f = new AFrameOnImage(104,0,104,91);
        dino_anim.AddFrame(f);
        f = new AFrameOnImage(208,0,104,91);
        dino_anim.AddFrame(f);
        f = new AFrameOnImage(0,0,104,91);
        dino_anim.AddFrame(f);
        
        dino = new Dino(10,320,80,80);
        
        ground = new Ground();
        
        obstaclesgroup = new ObstaclesGroup();
        
        BeginGame();
    }
    
    
    
    
    public static void main(String args[]) throws IOException {
        Dinosaur mh = new Dinosaur();
        
    }
    
    /**
     *
     * @param g2
     */
    public void paint(Graphics2D g2){
        dino_anim.PaintAnims((int)dino.getPosX(), (int)dino.getPosY() , dinos, g2, 0, 0);
        ground.Paint(g2);
        
    }
    
    private void resetGame(){
        dino.setPos(10, 320);
    }
    

    @Override
    public void GAME_UPDATE(long deltaTime){
        
        if(CurrentScreen == BEGIN_SCREEN){
            resetGame();
            
        }else if(CurrentScreen == GAMEPLAY_SCREEN){
            dino_anim.Update_Me(deltaTime);
            dino.update(deltaTime);
            ground.Update();
            obstaclesgroup.update();
            
            if(dino.getPosY() == 170 ) CurrentScreen = GAMEPLAY_SCREEN;
        }else{
            
        }
       
        
        
        
        
        
    }

    @Override 
    public void GAME_PAINT(Graphics2D g2) {
        
        paint(g2);
        
        obstaclesgroup.paint(g2);
        
        if(CurrentScreen == BEGIN_SCREEN){
            g2.setColor(Color.red);
            g2.drawString("Press space to play game", 100, 100);
        }
        if(CurrentScreen == GAMEOVER_SCREEN){
            g2.setColor(Color.BLACK);
            g2.drawString("Press space to continue", 100, 100);
        }
        
    }

    
    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if(KEY_PRESSED == Event){ 
            
            if(CurrentScreen == BEGIN_SCREEN){
                CurrentScreen = GAMEPLAY_SCREEN;
            }else if(CurrentScreen == GAMEPLAY_SCREEN){
                dino.setIsJumping(true);
                dino.setisdrop(false);
            }else if(CurrentScreen == GAMEOVER_SCREEN){
                CurrentScreen = BEGIN_SCREEN;
            }
            
            
        }  
    }
}

