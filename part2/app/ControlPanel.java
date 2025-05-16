package part2.app;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static part2.app.ImagePanel.*;

public class ControlPanel extends JPanel {

    public final static JTextField widthSuggested = new JTextField();
    public final static JTextField heightSuggested = new JTextField();


    ControlPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        widthSuggested.setEditable(true);
        heightSuggested.setEditable(true);

        widthSuggested.setBorder(new LineBorder(Color.black));
        heightSuggested.setBorder(new LineBorder(Color.black));

        widthSuggested.setBorder(new LineBorder(Color.black));
        heightSuggested.setBorder(new LineBorder(Color.black));

        widthSuggested.setPreferredSize(new Dimension(100, 20));
        heightSuggested.setPreferredSize(new Dimension(100, 20));

        JLabel widthLabel = new JLabel("Width:");
        JLabel heightLabel = new JLabel("Height:");

        Button scaleImageButton = new Button("scale Image");
        scaleImageButton.addActionListener(_ ->
            editImageSize(Integer.parseInt(widthSuggested.getText()) , Integer.parseInt(heightSuggested.getText())));

        add(widthLabel);
        add(widthSuggested);
        add(heightLabel);
        add(heightSuggested);
        add(scaleImageButton);

    }
}
