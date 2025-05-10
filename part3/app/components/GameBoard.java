package part3.app.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameBoard extends JPanel implements ActionListener {
    private final ArrayList<CardButton> cards = new ArrayList<>();
    private CardButton first, second;
    private final javax.swing.Timer timer;
    private final ScorePanel scorePanel;
    private int moves = 0;
    private int btnWidth = 145, btnHeight = 145;

    public GameBoard(ScorePanel scorePanel) {
        this.setPreferredSize(new Dimension(590, 590));
        this.scorePanel = scorePanel;
        setLayout(new GridLayout(4, 4, 0, 0));

        ArrayList<Icon> icons = loadIcons();
        for (Icon icon : icons) {
            CardButton btn = new CardButton(icon, getBackIcon(), btnWidth,
                    btnHeight);
            btn.addActionListener(this);
            cards.add(btn);
            add(btn);
        }

        timer = new javax.swing.Timer(1000, _ -> {
            first.flip();
            second.flip();
            first = second = null;
        });
        timer.setRepeats(false);
    }

    private ArrayList<Icon> loadIcons() {
        ArrayList<Icon> icons = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            Image img = new ImageIcon(getClass().getResource("../images/img" + i + ".png")).getImage();
            Image scaledImg = img.getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);
            Icon icon = new ImageIcon(scaledImg);
            icons.add(icon);
            icons.add(icon);
        }
        Collections.shuffle(icons);
        return icons;
    }

    private Icon getBackIcon() {
        Image img = new ImageIcon(getClass().getResource("../images/back.png")).getImage();
        Image scaledImg = img.getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardButton clicked = (CardButton) e.getSource();
        if (clicked.isFlipped() || clicked.isMatched() || timer.isRunning())
            return;

        clicked.flip();

        if (first == null) {
            first = clicked;
        } else {
            second = clicked;
            moves++;
            scorePanel.updateMoves(moves);

            if (first.getFrontIcon().equals(second.getFrontIcon())) {
                first.setMatched(true);
                second.setMatched(true);
                first = second = null;
                checkWin();
            } else {
                timer.start();
            }
        }
    }

    private void checkWin() {
        boolean allMatched = cards.stream().allMatch(CardButton::isMatched);
        if (allMatched) {
            JOptionPane.showMessageDialog(this, "🎉 You won in " + moves + " moves!");
        }
    }
}