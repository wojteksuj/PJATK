import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main extends JFrame {

    static {
        System.loadLibrary("Project1_C");
    }

    public static void main(String[] args) {

        Board board = new Board();
        MyFrame frame = new MyFrame();
        board.drawBoard(frame);


    }
}