import javax.swing.*;
import java.awt.*;

/**
 * Wrapper class for a show, allowing it to be represented as a JComponent.
 */
public class ShowComponent extends JComponent {

    private Show show;

    /**
     * Constructs a new ShowComponent.
     * @param show The show the Component is modeling.
     */
    public ShowComponent(Show show) {
        this.show = show;
    }

    /**
     * Paints the show on g.
     * @param g The graphics on which to paint the show.
     */
    @Override
    protected void paintComponent(Graphics g) {
        show.paintComponent(g);
    }
}
