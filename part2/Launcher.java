package part2;

import javax.swing.SwingUtilities;
import part2.app.MainFrame;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
