# Memory Picture Game (Java Swing)

## Introduction

This is a beginner-friendly Java Swing project to implement a **Memory Picture Game**, also known as a matching game. The goal is for students to reinforce GUI programming concepts by working with buttons, event listeners, timers, and image loading.

This project uses a modular component-based design. Each component is placed in its own file to ensure clean separation and better maintainability.

---

## Learning Objectives

* Build GUIs with **Java Swing** using `JFrame`, `JPanel`, and `JButton`
* Work with **GridLayout** to create a grid of cards
* Manage image resources
* Implement game logic with event handling and `Timer`
* Structure code using **components in separate classes**

---

## Project Structure

```
MemoryPictureGame/
├── app/
│   ├── components/
│   │   ├── CardButton.java
│   │   ├── GameBoard.java
│   │   ├── ScorePanel.java
│   │   └── MainFrame.java
│   ├── images/
│   │   ├── img1.png
│   │   ├── img2.png
│   │   ├── .
│   │   ├── .
│   │   ├── .
│   │   └── back.png
│   └── Launcher.java
└── readme-part-2.md
```

---

## How to Run

1. Open a terminal.
2. Change the directory of the terminal where the Launcher.java is.
3. Run this command:
```bash
java Launcher.java
```

---

## Main Components

### 1. MainFrame.java

**This is the entry point of the game. It creates the frame and adds the board and score panel.**

```java
// MainFrame.java
package part2.app.components;

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
```

---

### 2. CardButton.java

**Represents an individual card in the game. Handles flipping and matching.**

```java
// CardButton.java
package part2.app.components;

import java.awt.Dimension;
import javax.swing.*;

public class CardButton extends JButton {
    private final Icon front;
    private final Icon back;
    private boolean flipped = false;
    private boolean matched = false;
    private int width, height;

    public CardButton(Icon front, Icon back, int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        this.front = front;
        this.back = back;
        setIcon(back);
    }

    public void flip() {
        if (!matched) {
            flipped = !flipped;
            setIcon(flipped ? front : back);
        }
    }

    public boolean isFlipped() { return flipped; }

    public void setMatched(boolean matched) {
        this.matched = matched;
        if (matched) setEnabled(false);
    }

    public boolean isMatched() { return matched; }

    public Icon getFrontIcon() { return front; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }
}
```

---

### 3. GameBoard.java

**Creates the grid layout, holds game logic (flip, match, end check).**

```java
// GameBoard.java
package part2.app.components;

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
```

---

### 4. ScorePanel.java

**Displays the number of moves. Can be extended to show time.**

```java
// ScorePanel.java
package part2.app.components;

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
```

---
### 5. Launcher.java

**A class to launch the app.**

```java
// ScorePanel.java
package part2;

import app.components.MainFrame;

public class Launcher {
 public static void main(String[] args){
    SwingUtilities.invokeLater(() -> {
           new MainFrame(); 
    });
 }
}

```
---

### 6. Output of the code:
<img src="./readme-resources/outputGif.gif" alt="..." width="637" />


