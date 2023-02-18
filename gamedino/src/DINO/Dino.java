
package DINO;

import pkg2dgamesframework.Objects;

/**
 *
 * @author user
 */
public class Dino extends Objects {
    
    
    public int vt = 0 ;
    
    public int nhaycao = 300;
    
    public boolean isJumping = false;
    
    public boolean isdrop = false;
    
    public Dino(float x, float y, float w, float h){
        super(x,y,w,h);
    }
    
    public void update(long deltaTime){
        
        if(this.getPosY() == Dinosaur.posy - nhaycao  ){  //nhay xuong
            this.setIsJumping(false);
            this.setisdrop(true); 
        }   
        
        if(this.getisdrop()){
            vt = 5;
            this.setPosY(this.getPosY() + vt); 
        }

        if(this.getIsJumping()){//nhay len
            this.setisdrop(false);
            vt = -5;
            this.setPosY(this.getPosY() + vt);  
        }
        
        if(this.getPosY() == Dinosaur.posy ) { // dung im 
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
    
}
