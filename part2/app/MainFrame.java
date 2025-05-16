package part2.app;

import javax.swing.JFrame;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Main Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BorderLayout());
        setSize(800, 600);

        add(new ImagePanel(), BorderLayout.CENTER);
        add(new ControlPanel(), BorderLayout.SOUTH);


        setVisible(true);
    }
    
}

