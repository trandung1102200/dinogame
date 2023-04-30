
package DINO;

import java.awt.Rectangle;
import java.io.File;
import pkg2dgamesframework.Objects;
import pkg2dgamesframework.SoundPlayer;

/**
 *
 * @author user
 */
public class Dinosaur extends Objects {   // 90:100
    
    
    public int vt = 0 ;
    
    public int nhaycao = 200;
    
    public boolean isJumping = false;
    
    public boolean isdrop = false;
    
    private final Rectangle rect;
    
    private SoundPlayer jump,getdia,die,begingame,night;
   
    
    public boolean isLive = true;
    
    public Dinosaur(int x, int y, int w, int h){
        super(x,y,w,h);
        rect = new Rectangle(x,y,w,h);
        jump = new SoundPlayer(new File("sound/jump.wav"));
        getdia = new SoundPlayer(new File("sound/getdia.wav"));
        die = new SoundPlayer(new File("sound/die.wav"));
        begingame = new SoundPlayer(new File("sound/begingame.wav"));
        night = new SoundPlayer(new File("sound/night.wav"));
    }
    
    public void setLive(boolean b){
        this.isLive = b;
    }
    
    
    
    public void update(long deltaTime){ 
        
        
        this.rect.setLocation((int)this.getPosX(),(int)this.getPosY());
        
        if(this.getPosY() <= Main.posy - nhaycao  ){  //nhay xuong   posx = 50,posy = 320;
            this.setIsJumping(false);
            this.setisdrop(true); 
        }   
        
        if(this.getisdrop()){  // roi xuong
            vt = 4;
            this.setPosY(this.getPosY() + vt); 
        }

        if(this.getIsJumping()){//nhay len
            this.setisdrop(false);
            vt = -8;
            this.setPosY(this.getPosY() + vt);  
        }
        
        else if(this.getPosY() >= Main.posy ) { // dung im 
            this.setIsJumping(false);
            this.setisdrop(false);
            this.setPosY(Main.posy);
        }
    }
    
    public boolean getisdrop(){
        return isdrop;
    }
    
    public void setisdrop(boolean isdrop){
        this.isdrop = isdrop;
    }
    
    public boolean getIsJumping(){
        return isJumping;
    }
    
    public void setIsJumping(boolean isJumping){
        this.isJumping = isJumping;
    }
    
    public Rectangle getRect(){
        return rect;
    }
    public void soundjump(){
        jump.play();  
    }
    public void sounddie(){
        die.play();
    }
    public void soundgetdia(){
        getdia.play();
    }
    public void soundbegingame(){
        begingame.play();
    }
    public void soundnight(){
        night.play();
    }
    
}
