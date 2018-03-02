import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Bryson Armstrong (HL2) on 2/28/2018.
 */
public class Show {

    private ArrayList<Beam> beams;

    public Show() {
        beams = new ArrayList<>();
    }

    public void addBeam(Beam b) {
        beams.add(b);
    }

    public void assignDimmers() {
        for (Beam beam:beams) {
            beam.assignDimmers();
        }
    }

    public void paintComponent(Graphics graphics) {
        int y = 20;
        for (Beam beam:beams) {
            beam.paintComponent(graphics, 0, y, 1000, 500);
            y+=100;
        }
    }
}
