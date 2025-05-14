package part1.app;

import javax.swing.*;
import java.awt.*;

public class CalculatorApp extends JFrame{

    JPanel panel = new JPanel(new BorderLayout());

    public CalculatorApp() {
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        DisplayPanel displayPanel = new DisplayPanel();
        ButtonPanel buttonPanel = new ButtonPanel();

        panel.add(displayPanel , BorderLayout.NORTH);
        panel.add(buttonPanel , BorderLayout.CENTER);

        this.add(panel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }
    
}
