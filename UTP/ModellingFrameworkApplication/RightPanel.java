import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    JTextArea textArea = new JTextArea(15, 100);
    public RightPanel() {
        this.setLayout(new BorderLayout());




        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);


        JScrollPane textPanel = new JScrollPane(textArea);

        this.add(textPanel, BorderLayout.CENTER);
    }
    public void appendToTextArea(String text){
        textArea.setText("");
        textArea.append(text);
    }


}
