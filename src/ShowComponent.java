import javax.swing.*;
import java.awt.*;

/**
 * Created by Bryson Armstrong (HL2) on 3/1/2018.
 */
public class ShowComponent extends JComponent {

    private Show show;

    public ShowComponent(Show show) {
        this.show = show;
    }

    @Override
    protected void paintComponent(Graphics g) {
        show.paintComponent(g);
    }
}
