package part3.app.components;

import java.awt.Dimension;
import javax.swing.*;

public class ScorePanel extends JPanel {
    private final JLabel moveLabel;

    public ScorePanel() {
        setPreferredSize(new Dimension(590, 40));
        moveLabel = new JLabel("Moves: 0", SwingConstants.CENTER);
        moveLabel.setPreferredSize(new Dimension(800, 50));
        add(moveLabel);
    }

    public void updateMoves(int moves) {
        moveLabel.setText("Moves: " + moves);
    }
}