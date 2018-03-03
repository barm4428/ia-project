import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Bryson Armstrong (HL2) on 2/28/2018.
 */
public class Show implements Serializable {

    private ArrayList<Beam> beams;

    public Show() {
        beams = new ArrayList<>();
    }

    public void addBeam(Beam b) {
        for (Beam beam:beams) {
            if (beam.getId()>=b.getId()) {
                beam.setId(beam.getId()+1);
            }
        }
        beams.add(b);
    }

    public ArrayList<Beam> getBeams() {
        return beams;
    }

    public void paintComponent(Graphics graphics) {
        int y = 20;
        for (Beam beam:beams) {
            beam.paintComponent(graphics, 0, y, 1000, 500);
            y+=100;
        }
    }

    @Override
    public String toString() {
        String output = "";
        for (int i=0; i<beams.size(); i++) {
            output += beams.get(i);
            if (i<beams.size()-1) {
                output += ",";
            }
        }
        return output;
    }
}
