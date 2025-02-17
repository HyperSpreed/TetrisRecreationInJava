package main;

import java.awt.*;
import java.util.Random;

import mino.*;

public class PlayManager {
    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    Mino currentMino;
    final int MINO_START_X;
    final int MINO_START_Y;

    public static int dropInterval = 60; // 1 second = 60 frames

    public PlayManager() {

        left_x = (GamePanel.WIDTH/2) - (WIDTH/2);
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;

        MINO_START_X = left_x + (WIDTH/2) - Block.SIZE;
        MINO_START_Y = top_y + Block.SIZE;

        currentMino = randomMino();
        currentMino.setXY(MINO_START_X, MINO_START_Y);

    }

    private Mino randomMino(){
        Mino mino = null;
        int i = new Random().nextInt(7);
        switch (i){
            case 0: mino = new Mino_L1(); break;
            case 1: mino = new Mino_L2(); break;
            case 2: mino = new Mino_T(); break;
            case 3: mino = new Mino_Square(); break;
            case 4: mino = new Mino_Z1(); break;
            case 5: mino = new Mino_Z2(); break;
            case 6: mino = new Mino_Bar(); break;
        }
        return mino;
    }

    public void update() {
        currentMino.update();
    }

    public void draw(Graphics2D g2){
        //Draw Outline of the play area
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4));
        g2.drawRect(left_x-4,top_y-4,WIDTH+8,HEIGHT+8);

        //Draw Outline of the next piece area
        int x = right_x + 100;
        int y = bottom_y - 200;
        g2.drawRect(x,y,200,200);
        g2.setFont(new Font("Roboto", Font.PLAIN, 20));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("Next Piece", x + 55, y + 60);

        if (currentMino != null){
            currentMino.draw(g2);
        }

        g2.setColor(Color.orange);
        g2.setFont(new Font("Roboto", Font.BOLD, 42));
        if (KeyHandler.pausePressed) {
            x = left_x + 45;
            y = top_y + 320;
            g2.drawString("Game Paused",x,y);
        }

    }

}
