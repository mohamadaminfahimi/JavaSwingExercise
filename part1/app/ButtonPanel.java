package part1.app;

import javax.swing.*;
import java.awt.*;

import static part1.app.DisplayPanel.*;

public class ButtonPanel extends JPanel{

    private  Button num0 , num1 , num2 , num3 , num4 , num5 , num6 , num7 , num8 , num9 , mul ,add ,
            sub , div  ,digit , clear , equal ,  openParenthesis , closeParenthesis , backspace ;
    boolean isResultShown = false;

    private Button[] buttons ;

    public ButtonPanel() {
        this.setLayout(new GridLayout(5, 4 , 3 , 3));
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(600, 500));


        initializeButtons();

        addButtonToPanel();

        setActionListener();

        styleButton();

    }

    private void setActionListener() {
        for (Button button : buttons) {

            switch (button.getLabel()) {
                case "b":
                    button.addActionListener(_ -> {
                        removeLastCharacter();
                        isResultShown = false;
                    });
                    break;
                case "=":
                    button.addActionListener(_ -> {
                        finalEquals();
                        isResultShown = true;
                    });
                    break;
                case "C":
                    button.addActionListener(_ -> {
                        clearTextField();
                        isResultShown = false;
                    });
                    break;
                default:
                    button.addActionListener(_ -> {
                        if (isResultShown)
                            if (!buttonISOperator(button))
                                clearTextField();
                        addCharacter(button.getLabel().charAt(0));
                        isResultShown = false;
                    });
            }
        }
    }

    private boolean buttonISOperator(Button button) {
        return button.getLabel().equalsIgnoreCase("-") ||
                button.getLabel().equalsIgnoreCase("+") ||
                button.getLabel().equalsIgnoreCase("/") ||
                button.getLabel().equalsIgnoreCase("*");
    }

    private void styleButton() {
        for (Button button : buttons) {
            button.setFont(new Font("Tahoma", Font.PLAIN, 25));
            button.setForeground(Color.WHITE);
            button.setBackground(Color.darkGray);
        }
    }

    private void addButtonToPanel() {
        this.add(num7);this.add(num8);this.add(num9);this.add(div);
        this.add(num4);this.add(num5);this.add(num6);this.add(mul);
        this.add(num1);this.add(num2);this.add(num3);this.add(sub);
        this.add(digit);this.add(num0);this.add(equal);this.add(add);
        this.add(clear);this.add(openParenthesis);this.add(closeParenthesis);this.add(backspace);
    }

    private void initializeButtons() {
        num0 = new Button("0");
        num1 = new Button("1");
        num2 = new Button("2");
        num3 = new Button("3");
        num4 = new Button("4");
        num5 = new Button("5");
        num6 = new Button("6");
        num7 = new Button("7");
        num8 = new Button("8");
        num9 = new Button("9");
        div = new Button("/");
        mul = new Button("*");
        sub = new Button("-");
        digit = new Button(".");
        add = new Button("+");
        equal = new Button("=");
        clear = new Button("C");
        openParenthesis = new Button("(");
        closeParenthesis = new Button(")");
        backspace = new Button("b");

        buttons = new Button[]{num0, num1, num2, num3, num4, num5, num6, num7, num8, num9, mul, add,
                sub, div, digit, clear, equal, openParenthesis, closeParenthesis, backspace};
    }

    public void removeLastCharacter() {
        if (!(DisplayPanel.getText().isEmpty())) {
            DisplayPanel.setText(DisplayPanel.getText().substring(0, DisplayPanel.getText().length() - 1));
        }
    }

    private void clearTextField() {
        DisplayPanel.setText("");
    }


    private void finalEquals() {
        DisplayPanel.setText(EvalUtil.eval(DisplayPanel.getText()));
    }
}
