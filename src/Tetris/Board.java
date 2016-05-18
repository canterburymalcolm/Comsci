package Tetris;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private Block block;
    public static int [][] cords;
    private static double dx, dy;
    private static final int DELAY = 200, WIDTH = 100, FLOOR = 730, SPACING = 32;
    private static ArrayList<int []> positions = new ArrayList<int []>();

    public Board (){
        initBoard();
    }
    private void initBoard(){
        addKeyListener(new TAadapter());
        setFocusable(true);
        setBackground(Color.WHITE);

        block = new Block();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    //@Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < 4; i++) {
            g2d.drawImage(block.getImage(),  cords[i][0] * SPACING, cords[i][1] * SPACING, this);
        }
        for (int i = 0; i < positions.size(); i++){
            g2d.drawImage(block.getImage(), positions.get(i)[0] * SPACING, positions.get(i)[1] * SPACING, this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (atBoundaries(cords)) {
            for (int i = 0; i < 4; i++) {
                positions.add(i, new int [] {cords[i][0], cords[i][1]});
            }
            cords = new int [][]{{5,0},{5,1},{5,2},{5,3}};
        }else{
            move();
            fall();
            deleteLines();
        }
        repaint();
    }
    public void deleteLines(){
        int sum;
        for (int i = 0; i < 20; i++){
            sum = 0;
            for (int[] element: positions){
                if (element[1] == i){
                    sum += element[0];
                }
            }
            if (sum == 55){
                for (int j = 0; j < positions.size(); j++){
                    if (positions.get(j)[1] == i){
                        positions.remove(j);
                    }
                }
                for (int k = 0; k < positions.size(); i++){
                    if (positions.get(k)[1] > i){
                        positions.add(new int[]{positions.get(k)[0], positions.get(k)[1]});
                        positions.remove(k);
                        k--;
                    }
                }
            }
        }

    }
    public void move(){
        for (int i = 0; i < 4; i++){
            cords[i][0] += dx;
            cords[i][1] += dy;
        }
    }
    public void fall(){
        for (int i = 0; i < 4; i++){
            cords[i][1] += + 1;
        }
    }
    public static void rotate() {
        if (cords[0][1] == cords[1][1]){
            for (int i = 0; i < 4; i++) {
                cords [i][0] = cords[2][0];
            }
            for (int i = 0; i < 4; i++){
                cords[i][1] += (2 - i);
            }

        }else {
            for (int i = 0; i < 4; i++) {
                cords[i][1] = cords[2][1];
            }
            for (int i = 0; i < 4; i++) {
                cords[i][0] += (2 - i);
            }
        }
    }
    public static void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT){
            dx = -1;
            for (int i = 0; i < 4; i++) {
                if (cords[i][0] - 1 < 0){
                    dx = 0;
                }
            }
            for (int [] element: positions) {
                for (int i = 0; i < 4; i++) {
                    if (element[0] == cords[i][0] - 1 && element[1] == cords[i][1]) {
                        dx = 0;
                    }
                }
            }
        }
        if (key == KeyEvent.VK_RIGHT){
            dx = 1;
            for (int i = 0; i < 4; i++) {
                if (cords[i][0] + 1 > WIDTH){
                    dx = 0;
                }
            }
            for (int [] element: positions) {
                for (int i = 0; i < 4; i++) {
                    if (element[0] == cords[i][0] + 1 && element[1] == cords[i][1]) {
                        dx = 0;
                    }
                }
            }
        }
        if (key == KeyEvent.VK_DOWN){
            //dy = 1;
        }
        if (key == KeyEvent.VK_UP){
            rotate();
        }

    }
    public static void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
    public boolean atBoundaries(int [][] cords){
        for (int i = 0; i < 4; i++){
            if (cords[i][1] * SPACING > FLOOR)
                return true;
        }
        for (int [] element: positions){
            for (int i = 0; i < 4; i++){
                if (element[0] == cords[i][0] && element[1] == cords[i][1] + 1){
                    return true;
                }
            }
        }
        return false;
    }
    private class TAadapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            Board.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            Board.keyPressed(e);
        }
    }

}
