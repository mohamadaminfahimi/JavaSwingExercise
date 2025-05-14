package part1.app;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel{

    private final static JTextField textField = new JTextField();

    public DisplayPanel() {
        this.setLayout(new BorderLayout());

        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(0, 120));
        textField.setHorizontalAlignment(SwingConstants.LEFT);
        textField.setFont(new Font("Segoe UI", Font.BOLD, 50));
        textField.setForeground(Color.BLACK);
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setOpaque(true);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.BLACK , 2)));

        this.add(textField);
    }


    public static String getText() {
        return textField.getText();
    }

    public static void setText(String text) {
        textField.setText(text);
    }

    public static void addCharacter(char character) {
        if (!textField.getText().isEmpty()){
            char lastChar = DisplayPanel.getText().charAt(DisplayPanel.getText().length()-1);
            if ((lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/') &&
                    (character == '+' || character == '-' || character == '*' || character == '/')) {

                textField.setText(textField.getText().substring(0 , textField.getText().length() - 1));

            }
        }
        else if (character == '*' || character == '/') {
            return;
        }
        textField.setText(textField.getText() + character);
    }
}
