import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    public GUI() {
        this.setTitle("Modelling framework sample");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        RightPanel rightPanel = new RightPanel();
        JPanel leftPanel = new LeftPanel(rightPanel);


        this.add(rightPanel, BorderLayout.CENTER);
        this.add(leftPanel, BorderLayout.WEST);

        this.setSize(1250, 500);
        this.setVisible(true);

    }


}
