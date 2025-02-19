package mino;


import main.GamePanel;
import main.KeyHandler;
import main.PlayManager;

import java.awt.*;

public class Mino {
    public Block b[] = new Block[4];
    public Block tempB[] = new Block[4];
    public int direction = 1; // There are a total of 4 directions (1/2/3/4)
    boolean leftCollision, rightCollision, downCollision;
    public boolean isActive = true;
    public boolean deactivating;
    int deactivationCounter = 0;

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

    public void checkMovementCollisions(){

        leftCollision = false;
        rightCollision = false;
        downCollision = false;

        checkStaticBlockCollision();

        //LeftCollision
        for (int i=0;i<b.length;i++){
            if (b[i].x == PlayManager.left_x){
                leftCollision = true;
            }
        }

        //RightCollision
        for (int i=0;i<b.length;i++){
            if (b[i].x + Block.SIZE == PlayManager.right_x){
                rightCollision = true;
            }
        }

        //BottomCollision
        for (int i=0;i<b.length;i++){
            if (b[i].y + Block.SIZE == PlayManager.bottom_y){
                downCollision = true;
            }
        }
    }

    public void checkRotationCollisions(){
        leftCollision = false;
        rightCollision = false;
        downCollision = false;

        checkStaticBlockCollision();

        //LeftCollision
        for (int i=0;i<b.length;i++){
            if (tempB[i].x < PlayManager.left_x){
                leftCollision = true;
            }
        }

        //RightCollision
        for (int i=0;i<b.length;i++){
            if (tempB[i].x + Block.SIZE > PlayManager.right_x){
                rightCollision = true;
            }
        }

        //BottomCollision
        for (int i=0;i<b.length;i++){
            if (tempB[i].y + Block.SIZE > PlayManager.bottom_y){
                downCollision = true;
            }
        }
    }

    public void updateXY(int direction){

        checkRotationCollisions();

        if (leftCollision == false && rightCollision == false && downCollision == false){
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

    }

    public void update(){

        if (deactivating) {
            deactivatingM();
        }

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
            GamePanel.soundEffect.play(3,false);

        }

        checkMovementCollisions();

        if (KeyHandler.downPressed) {
            if (downCollision == false){
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;

                autoDropCounter = 0;
            }


            KeyHandler.downPressed = false;
        }
        if (KeyHandler.leftPressed) {

            if (leftCollision == false){
                b[0].x -= Block.SIZE;
                b[1].x -= Block.SIZE;
                b[2].x -= Block.SIZE;
                b[3].x -= Block.SIZE;
            }

            KeyHandler.leftPressed = false;
        }
        if (KeyHandler.rightPressed) {

            if (rightCollision == false){
                b[0].x += Block.SIZE;
                b[1].x += Block.SIZE;
                b[2].x += Block.SIZE;
                b[3].x += Block.SIZE;
            }


            KeyHandler.rightPressed = false;
        }

        if (downCollision){
            if (deactivating == false){
                GamePanel.soundEffect.play(4,false);

            }
            deactivating = true;
        } else {
            autoDropCounter++;

            if(autoDropCounter == PlayManager.dropInterval){
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;
                autoDropCounter = 0;
            }
        }
    }

    private void checkStaticBlockCollision(){
        for (int i=0;i<PlayManager.staticBlocks.size();i++){

            int targetX = PlayManager.staticBlocks.get(i).x;
            int targetY = PlayManager.staticBlocks.get(i).y;

            //check down
            for (int j =0; j<b.length;j++){
                if (b[j].y + Block.SIZE == targetY && b[j].x == targetX){
                    downCollision = true;
                }
            }

            //check left
            for (int j = 0; j < b.length; j++) {
                if (b[j].x - Block.SIZE == targetX && b[j].y == targetY) {
                    leftCollision = true;
                }
            }

            //check left
            for (int j = 0; j < b.length; j++) {
                if (b[j].x + Block.SIZE == targetX && b[j].y == targetY) {
                    rightCollision = true;
                }
            }

        }
    }

    private void deactivatingM(){
        deactivationCounter++;
        if (deactivationCounter == 45){
            deactivationCounter = 0;
            checkMovementCollisions();

            if (downCollision) {
                isActive = false;
            }
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
