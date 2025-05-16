package part2.app;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ImagePanel extends JPanel {

    public static ImageIcon image;
    public static JLabel imageLabel;

    ImagePanel() {
        setLayout(new BorderLayout());

        imageLabel = new JLabel();
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/img.png")));
        imageLabel.setIcon(image);

        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(imageLabel , BorderLayout.CENTER);
    }

    public static void editImageSize(int width, int height) {
        Image newImage = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(newImage));
    }

}


