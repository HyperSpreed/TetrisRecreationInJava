package mino;

import java.awt.*;

public class Mino {
    public Block b[] = new Block[4];
    public Block tempB[] = new Block[4];

    public void create(Color c){
        b[0] = new Block(c);
        b[1] = new Block(c);
        b[2] = new Block(c);
        b[3] = new Block(c);
        tempB[0] = new Block(c);
        tempB[1] = new Block(c);
        tempB[2] = new Block(c);
        tempB[3] = new Block(c);
    }

    public void setXY(int x, int y){

    }
    public void updateXY(int direction){

    }
    public void update(){

    }

    public void draw(Graphics2D g2){
        g2.setColor(b[0].c);
        g2.fillRect(b[0].x,b[0].y,Block.SIZE,Block.SIZE);
        g2.fillRect(b[1].x,b[1].y,Block.SIZE,Block.SIZE);
        g2.fillRect(b[2].x,b[2].y,Block.SIZE,Block.SIZE);
        g2.fillRect(b[3].x,b[3].y,Block.SIZE,Block.SIZE);
    }

}
