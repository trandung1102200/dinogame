
package DINO;

import java.awt.Rectangle;
import pkg2dgamesframework.Objects;

/**
 *
 * @author user
 */
public class Dino extends Objects {
    
    
    public int vt = 0 ;
    
    public int nhaycao = 200;
    
    public boolean isJumping = false;
    
    public boolean isdrop = false;
    
    private Rectangle rect;
    
    public boolean isLive = true;
    
    public Dino(int x, int y, int w, int h){
        super(x,y,w,h);
        rect = new Rectangle(x,y,w,h);
    }
    
    public void setLive(boolean b){
        this.isLive = b;
    }
    
    
    
    public void update(long deltaTime){
        
        this.rect.setLocation((int)this.getPosX(),(int)this.getPosY());
        
        if(this.getPosY() <= Dinosaur.posy - nhaycao  ){  //nhay xuong
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
        
        else if(this.getPosY() >= Dinosaur.posy ) { // dung im 
            this.setIsJumping(false);
            this.setisdrop(false);
            this.setPosY(Dinosaur.posy);
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
    
}
