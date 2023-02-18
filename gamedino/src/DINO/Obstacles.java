
package DINO;

import pkg2dgamesframework.Objects;

/**
 *
 * @author user
 */
public class Obstacles extends Objects{
    
    public Obstacles(int x,int y, int w, int h){
        super(x,y,w,h);
    }
    
    public void update(){
        this.setPosX(this.getPosX() - 2);
    }
}
