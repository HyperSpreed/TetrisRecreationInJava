package mino;

import java.awt.*;

public class Mino_L1 extends Mino{

    public Mino_L1(){
        create(Color.orange);
    }
    public void setXY(int x, int y){

        //■ -> b[1]
        //■ -> b[0]
        //■ ■ -> b[2] & b[3]

        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x;
        b[1].y = b[0].y - Block.SIZE;
        b[2].x = b[0].x;
        b[2].y = b[0].y + Block.SIZE;
        b[3].x = b[0].x + Block.SIZE;
        b[3].y = b[0].y + Block.SIZE;
    }

}
