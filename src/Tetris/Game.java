package Tetris;

import javax.swing.*;
import java.awt.*;

/**
 * Created by drnutsoda on 5/17/2016.
 */
public class Game extends JFrame {
    public Game (){
        initUI();
    }
    private void initUI(){
        add(new Board());

        setSize(330, 800);
        setResizable(false);

        setTitle("TETRIS");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main (String[] args){
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                Game tetris = new Game();
                tetris.setVisible(true);
            }
        });
    }
}