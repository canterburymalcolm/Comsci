package Tetris;
import javax.swing.*;
import java.awt.*;
public class Block {
    private Image image;
    public Block (){
    }
    private void initBlock(){
        ImageIcon i = new ImageIcon("C:\\Users\\APCS\\Downloads\\block.png");
        image = i.getImage();
        Board.cords = new int [][]{{5,0},{5,1},{5,2},{5,3}};
    }

    public Image getImage(){
        return image;
    }
}
