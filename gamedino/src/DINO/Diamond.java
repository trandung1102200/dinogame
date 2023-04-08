
package DINO;

import java.awt.Rectangle;
import pkg2dgamesframework.Objects;


public class Diamond extends Objects {
    public Rectangle rect;
    public boolean isvc = false;
    public Diamond(int x, int y, int w, int h) {
        super(x, y, w, h);rect = new Rectangle(x,y,w,h);
    }
    public void update(){
        rect.setLocation((int)this.getPosX(), (int)this.getPosY());
        this.setPosX(this.getPosX() - Dinosaur.gamespeed );
    }
    public Rectangle getRect(){
        return rect;
    }
    public boolean getisvc(){
        return this.isvc;
    }
    public void setisvc(boolean t){
        this.isvc = t;
    }
}
