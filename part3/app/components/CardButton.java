// CardButton.java
package part3.app.components;

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