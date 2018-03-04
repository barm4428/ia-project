import java.awt.*;

/**
 * Models a dimmer on a beam.
 * The Dimmer has a number and beamPos (gotten from parent BeamObj class.
 * The Dimmer is graphically displayed as its number.
 */
public class Dimmer extends BeamObj {

    private int number;

    /**
     * Constructs a new Dimmer object.
     * @param number The Dimmer number.
     * @param beamPos The Dimmers absolute position along the Beam.
     */
    public Dimmer(int number, double beamPos) {
        super(beamPos);
        this.number = number;
    }

    /**
     * Prints the Dimmer as a String.
     * @return A String of the form "Dimmer(number=[number],beamPos=[beamPos])
     */
    @Override
    public String toString() {
        return "Dimmer(number=" + number + ",beamPos=" + beamPos + ")";
    }

    /**
     * Paints the dimmer on graphics.
     * @param graphics The graphics object to paint the dimmer on.
     * @param x The x-coordinate of the top-left corner of the dimmer.
     * @param y The y-coordinate of the top-left corner of the dimmer.
     * @param width The width of the parent container.
     * @param height The height of the parent container.
     */
    public void paintComponent(Graphics graphics, int x, int y, int width, int height) {
        graphics.drawString("" + number, x, y);
    }

    /**
     * Checks if the Dimmer is equal to another Dimmer.
     * @param obj The Dimmer to be compared to.
     * @return True if they have the same number, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Dimmer) {
            Dimmer d = (Dimmer) obj;
            return number == d.number;
        } else {
            return false;
        }
    }

    /**
     * Returns the Dimmer's number.
     * @return the Dimmer's number.
     */
    public int getNumber() {
        return number;
    }
}
