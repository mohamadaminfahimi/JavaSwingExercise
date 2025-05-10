package part3;

import javax.swing.SwingUtilities;

import part3.app.components.MainFrame;

public class Launcher {
     public static void main(String[] args){
    SwingUtilities.invokeLater(() -> {
           new MainFrame(); 
    });
 }
}
