import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Models a Beam, with a number of Dimmers and Instruments on it.
 */
public class Beam implements Serializable {

    private int id;
    private int length;
    private ArrayList<Dimmer> dimmers;
    private ArrayList<Instrument> instruments;

    /**
     * Constructs a new Beam.
     * @param id The Beam's unique ID.
     * @param length The Beam's length.
     */
    public Beam(int id, int length) {
        this.id = id;
        this.length = length;
        dimmers = new ArrayList<>();
        instruments = new ArrayList<>();
    }

    /**
     * Adds a new Dimmer to the Beam.
     * @param d The Dimmer to be added.
     */
    public void add(Dimmer d) {
        dimmers.add(d);
    }

    /**
     * Adds a new Instrument to the Beam.
     * @param i The Instrument to be added.
     */
    public void add(Instrument i) {
        instruments.add(i);
    }

    /**
     * Sets the Beam's ID.
     * @param id The beams new ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Paints the Beam on Graphics g.
     * @param graphics The graphics object on which to paint the Beam.
     * @param x The x-coordinate of the top-left corner.
     * @param y The y-coordinate of the top-left corner.
     * @param width The width of the container.
     * @param height The height of the container.
     */
    public void paintComponent(Graphics graphics, int x, int y, int width, int height) {
        graphics.drawString("Beam " + id, x+5, y+5);
        graphics.drawLine(x+50, y, width-25, y);
        for (Dimmer d:dimmers) {
            int pos = (int) Math.ceil(x + 50 + (d.getBeamPosition()/length*(width-75)));
            d.paintComponent(graphics, pos, y-2, width, height);
        }
        for (Instrument i: instruments) {
            int pos = (int) Math.ceil(x + (i.getBeamPos()/length*width));
            i.paintComponent(graphics, pos, y+2, 20, 20);
        }
    }

    /**
     * Prints the Beam as a String.
     * @return A String containing all the information of the Beam and its Dimmers and Instruments.
     */
    @Override
    public String toString() {
        String output = "Beam(id=" + id + ",length=" + length;
        for (Dimmer d:dimmers) {
            output += "," + d;
        }
        for (Instrument i: instruments) {
            output += "," + i;
        }
        return output + ")";
    }

    /**
     * Returns the Beam's ID.
     * @return teh Beam's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the Beam's dimmer list.
     * @return An ArrayList containing the Beam's Dimmers.
     */
    public ArrayList<Dimmer> getDimmers() {
        return dimmers;
    }

    /**
     * Returns the Beam's instrument list.
     * @return An ArrayList containing the Beam's Instruments.
     */
    public ArrayList<Instrument> getInstruments() {
        return instruments;
    }

    /**
     * Removes a Dimmer from the Beam.
     * @param number The number of the dimmers to remove.
     * @return True if the action was performed.
     */
    public boolean removeDimmer(int number) {
        boolean found = false;
        for (int i=0; i<dimmers.size(); i++) {
            if (dimmers.get(i).getNumber() == number) {
                dimmers.remove(i);
                i--;
                found = true;
            }
        }
        return found;
    }

    /**
     * Removes an Instrument from the Beam.
     * @param name The name of the Instruments to remove.
     * @return True if the action was performed.
     */
    public boolean removeInstrument(String name) {
        boolean found = false;
        for (int i=0; i<instruments.size(); i++) {
            if (instruments.get(i).getName().equals(name)) {
                instruments.remove(i);
                i--;
                found = true;
            }
        }
        return found;
    }
}
