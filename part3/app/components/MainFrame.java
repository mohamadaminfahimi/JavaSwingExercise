package part3.app.components;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    public MainFrame() {
        SwingUtilities.invokeLater(() -> {

            Image img = new ImageIcon(getClass().getResource("../images/frameIcon.png")).getImage();
            setIconImage(img);
            setTitle("Memory Picture Game");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridBagLayout());
            setSize(600, 650);
            setResizable(false);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;

            ScorePanel scorePanel = new ScorePanel();
            GameBoard gameBoard = new GameBoard(scorePanel);

            add(scorePanel, gbc);
            gbc.gridy = 1;
            add(gameBoard, gbc);
            pack();

            setLocationRelativeTo(null);
            setVisible(true);
        });
    }
}
