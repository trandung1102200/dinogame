
package DINO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;


public class Main extends GameScreen {
    
    private BufferedImage dinos;
    
    private final  Animation dino_anim;
    
    private final Dinosaur dino; 
    
    private final Background ground,sky; 
    
    public  ObstaclesGroup obstaclesgroup;
    
    private final CloudGroup cloudgroup;
    
    private final TreeGroup treegroup;
    
    private final DiamondGroup diagroup;
    
    private int BEGIN_SCREEN = 0;
    
    private int GAMEPLAY_SCREEN = 1;
    
    private int GAMEOVER_SCREEN = 2;
    
    private int CurrentScreen = BEGIN_SCREEN;
    
    public static int posx = 50,posy = 320;
    
    public static float gamespeed = (float) 2.0;
    
    public int point,maxpoint;
    
    private int nvqvc;private boolean night = false;
    
    Font myFont2 = new Font("Arial", Font.BOLD, 14);
    
// ------------------------------------------------------------------------------------------------------------------------------------------------    
    
    public Main() throws IOException{
        super(780,500);
        ReadFile rf = new ReadFile();
        maxpoint = Integer.parseInt(rf.data); 
        nvqvc = 1;
        try {
            dinos = ImageIO.read(new File("images/ha.png"));
        } catch (IOException ex) {}
 
        dino_anim = new Animation(100);
        AFrameOnImage f ;
        
        f = new AFrameOnImage(0,0,104,81);
        dino_anim.AddFrame(f);
        f = new AFrameOnImage(104,0,104,81);
        dino_anim.AddFrame(f);
        f = new AFrameOnImage(208,0,104,81);
        dino_anim.AddFrame(f);
        f = new AFrameOnImage(0,0,104,81);
        dino_anim.AddFrame(f);
        
        dino = new Dinosaur(posx,posy,80,80);
        ground = new Ground(); sky = new Sky();
        obstaclesgroup = new ObstaclesGroup();
        cloudgroup = new CloudGroup();
        treegroup = new TreeGroup(); 
        diagroup = new DiamondGroup();
        dino.soundbegingame();
        BeginGame();
    }
// ------------------------------------------------------------------------------------------------------------------------------------------------    
    public static void main(String args[]) throws IOException {       
        Main newgame = new Main();   
    }
// ------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void GAME_UPDATE(long deltaTime){
        if(CurrentScreen == BEGIN_SCREEN){    
            resetGame(); 
        }else if(CurrentScreen == GAMEPLAY_SCREEN){
            if(dino.isLive == true) {
                dino_anim.Update_Me(deltaTime);
                dino.update(deltaTime);
                sky.Update();
                ground.Update();
                obstaclesgroup.update();
                cloudgroup.update();
                treegroup.update();
                diagroup.update();
            }
            for(int i = 0 ;i<7;i++){      // xu ly die
                if(dino.getRect().intersects(obstaclesgroup.getxrs(i).getRect())){
                    dino.setLive(false);
                    dino.sounddie();
                    CurrentScreen = GAMEOVER_SCREEN;   
                }
            }
            for(int i = 0 ;i< 7 ;i++){          // xu ly ngay va dem 
                if(dino.getPosX()>obstaclesgroup.getxrs(i).getPosX() && !obstaclesgroup.getxrs(i).getisbehind() ){
                    nvqvc++;
                    obstaclesgroup.getxrs(i).setisbehind(true);
                }
                if(nvqvc>=4){
                    nvqvc = 0;
                    night = !night; 
                    if(night) dino.soundnight();
                } 
            }    
            for(int i = 0 ;i<10 ;i++){          // xu ly tang diem
                if(dino.getRect().intersects(diagroup.getdia(i).getRect())){
                    diagroup.getdia(i).setisvc(true);point+=1;
                    dino.soundgetdia();
                }  
            }
        }
        else{
            savepoint(); 
        } 
    }
// ------------------------------------------------------------------------------------------------------------------------------------------------
    @Override 
    public void GAME_PAINT(Graphics2D g2) {      
        paint(g2);  
    }
// ------------------------------------------------------------------------------------------------------------------------------------------------    
    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if(KEY_PRESSED == Event){ 
            
            if(CurrentScreen == BEGIN_SCREEN){
                CurrentScreen = GAMEPLAY_SCREEN;
            }else if(CurrentScreen == GAMEPLAY_SCREEN){
                if(dino.isLive == true ){
                    dino.setIsJumping(true);dino.soundjump();
                    dino.setisdrop(false);
                }
            }else if(CurrentScreen == GAMEOVER_SCREEN){
                CurrentScreen = BEGIN_SCREEN;
                dino.soundbegingame();
            }   
        }
        
    }
// ------------------------------------------------------------------------------------------------------------------------------------------------    
    public void paint(Graphics2D g2){
        if(night){
            sky.Paint(g2);
        }
        else{
            g2.setColor(Color.decode("#b8daef"));
            g2.fillRect(0, 0, 780, 500);
        }
        dino_anim.PaintAnims((int)dino.getPosX(), (int)dino.getPosY() , dinos, g2, 0, 0);
        ground.Paint(g2);
        obstaclesgroup.paint(g2);
        cloudgroup.paint(g2);
        treegroup.paint(g2);
        diagroup.paint(g2);
        if(CurrentScreen == BEGIN_SCREEN){
            g2.setColor(Color.red);
            g2.setFont(myFont2);
            g2.drawString("PRESS SPACE TO PLAY", 150, 50);
            String pstr = String.valueOf(maxpoint);
            g2.drawString("Highest score: " + pstr , 150, 30);
        }
        if(CurrentScreen == GAMEOVER_SCREEN){
            g2.setFont(myFont2);
            g2.setColor(Color.red);
            g2.drawString("PLAY AGAIN?", 100, 100);
        }
        g2.setColor(Color.red);
        g2.setFont(myFont2);
        g2.drawString( " " + point, 40,30);
    }
    public void savepoint(){
        if(point > maxpoint){
            maxpoint = point;
            WriteFile wff = new WriteFile();
            String pstr = String.valueOf(maxpoint);  
            wff.write(pstr);
        }
    }
    private void resetGame() {
        dino.setPos(posx, posy);
        dino.setLive(true);
        point = 0;
        obstaclesgroup = new ObstaclesGroup();
    }    
}
