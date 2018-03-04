import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Models a Show, with a number of Beams stored within it.
 */
public class Show implements Serializable {

    private ArrayList<Beam> beams;

    /**
     * Constructs a new Show and initializes the ArrayList<Beam>.
     */
    public Show() {
        beams = new ArrayList<>();
    }

    /**
     * Adds a Beam to the Show.
     * @param b The beam to be added.
     */
    public void addBeam(Beam b) {
        for (Beam beam:beams) {
            if (beam.getId()>=b.getId()) {
                beam.setId(beam.getId()+1);
            }
        }
        beams.add(b);
    }

    /**
     * Returns the Show's beams.
     * @return The Show's Beams as an ArrayList.
     */
    public ArrayList<Beam> getBeams() {
        return beams;
    }

    /**
     * Paints the show on graphics.
     * @param graphics The graphics on which to paint the show.
     */
    public void paintComponent(Graphics graphics) {
        int y = 20;
        for (Beam beam:beams) {
            beam.paintComponent(graphics, 0, y, 1000, 500);
            y+=100;
        }
    }

    /**
     * Returns the Show as a string.
     * @return A String representing the Show, equal to its Beams separated by commas.
     */
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
