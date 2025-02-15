package mino;


import main.KeyHandler;
import main.PlayManager;

import java.awt.*;

public class Mino {
    public Block b[] = new Block[4];
    public Block tempB[] = new Block[4];
    public int direction = 1; // There are a total of 4 directions (1/2/3/4)

    int autoDropCounter = 0;

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

    public void getDirection1(){}
    public void getDirection2(){}
    public void getDirection3(){}
    public void getDirection4(){}

    public void updateXY(int direction){
        this.direction = direction;
        b[0].x = tempB[0].x;
        b[0].y = tempB[0].y;
        b[1].x = tempB[1].x;
        b[1].y = tempB[1].y;
        b[2].x = tempB[2].x;
        b[2].y = tempB[2].y;
        b[3].x = tempB[3].x;
        b[3].y = tempB[3].y;
    }
    public void update(){

        if (KeyHandler.upPressed) {

            switch (direction) {
                case 1:
                    getDirection2();
                    break;
                case 2:
                    getDirection3();
                    break;
                case 3:
                    getDirection4();
                    break;
                case 4:
                    getDirection1();
                    break;
            }

            KeyHandler.upPressed = false;

        }
        if (KeyHandler.downPressed) {
            b[0].y += Block.SIZE;
            b[1].y += Block.SIZE;
            b[2].y += Block.SIZE;
            b[3].y += Block.SIZE;

            autoDropCounter = 0;

            KeyHandler.downPressed = false;
        }
        if (KeyHandler.leftPressed) {
            b[0].x -= Block.SIZE;
            b[1].x -= Block.SIZE;
            b[2].x -= Block.SIZE;
            b[3].x -= Block.SIZE;

            KeyHandler.leftPressed = false;
        }
        if (KeyHandler.rightPressed) {
            b[0].x += Block.SIZE;
            b[1].x += Block.SIZE;
            b[2].x += Block.SIZE;
            b[3].x += Block.SIZE;

            KeyHandler.rightPressed = false;
        }

        autoDropCounter++;

        if(autoDropCounter == PlayManager.dropInterval){
            b[0].y += Block.SIZE;
            b[1].y += Block.SIZE;
            b[2].y += Block.SIZE;
            b[3].y += Block.SIZE;
            autoDropCounter = 0;
        }

    }

    public void draw(Graphics2D g2){
        int margin = 2;
        g2.setColor(b[0].c);
        g2.fillRect(b[0].x + margin,b[0].y + margin,Block.SIZE - (margin*2),Block.SIZE - (margin*2));
        g2.fillRect(b[1].x + margin,b[1].y + margin,Block.SIZE - (margin*2),Block.SIZE - (margin*2));
        g2.fillRect(b[2].x + margin,b[2].y + margin,Block.SIZE - (margin*2),Block.SIZE - (margin*2));
        g2.fillRect(b[3].x + margin,b[3].y + margin,Block.SIZE - (margin*2),Block.SIZE - (margin*2));
    }

}
