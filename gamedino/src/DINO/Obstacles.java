
package DINO;

import java.awt.Rectangle;
import pkg2dgamesframework.Objects;

/**
 *
 * @author user
 */
public class Obstacles extends Objects{
    
    public Rectangle rect;
    
    public boolean isbehind = false;
    
    public Obstacles(int x,int y, int w, int h){
        super(x,y,w,h);
        rect = new Rectangle(x,y,w,h);
    }
    
    public void update(){
        rect.setLocation((int)this.getPosX(), (int)this.getPosY());
        this.setPosX(this.getPosX() - (Dinosaur.gamespeed));
    }
    
    public Rectangle getRect(){
        return rect;
    }
    
    public void setisbehind(boolean b){
        this.isbehind = b;
    }
    public boolean getisbehind(){
        return this.isbehind;
    }
    
}
