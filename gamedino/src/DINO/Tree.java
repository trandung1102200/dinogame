
package DINO;
import pkg2dgamesframework.Objects;

public class Tree extends Objects{
    public Tree(int x,int y, int w,int h){
        super(x,y,w,h);
    }
    public void update(){
        this.setPosX(this.getPosX() - Dinosaur.gamespeed);
    }
}
