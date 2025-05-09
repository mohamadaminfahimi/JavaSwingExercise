package part1;

import javax.swing.SwingUtilities;

import part1.app.CalculatorApp;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
           new CalculatorApp(); 
        });
    }
    
}
