import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Models a lighting instrument hung on a beam.
 * The Instrument has a name and beamPos (gotten from parent BeamObj class.
 * The Instrument is graphically displayed as an Image found in resources/etc.jpg.
 */
public class Instrument extends BeamObj  {

    private String name;
    private static final String IMG_LOC = "resources/etc.jpg";

    /**
     * Creates a new Instrument.
     * @param beamPos The Instrument's absolute position on the beam.
     * @param name All Instruments of the same name will be considered equal.
     */
    public Instrument(double beamPos, String name) {
        super(beamPos);
        this.name = name;
    }

    /**
     * Prints the Instrument as a String.
     * @return A String of the form "Instrument(name=[name],beamPos=[beamPos])
     */
    @Override
    public String toString() {
        return "Instrument(name=" + name + ",beamPos=" + getBeamPos() + ")";
    }

    /**
     * Paints the instrument as an Image on graphics.
     * @param graphics The graphics object to paint the image on.
     * @param x The x-coordinate of the top-left corner of the image.
     * @param y The y-coordinate of the top-left corner of the image.
     * @param width The width of the Image's container.
     * @param height The height of the Image's container.
     */
    public void paintComponent(Graphics graphics, int x, int y, int width, int height) {
        if (graphics instanceof Graphics2D) {
            try {
                Image img = ImageIO.read(new File(IMG_LOC));
                Graphics2D g2 = (Graphics2D) graphics;
                g2.drawImage(img, x, y, 30, 30, null);
            } catch (IOException e) {
                graphics.drawString(name, x, y);
            }
        }
    }

    /**
     * Returns the Instrument's position of the beam.
     * @return beamPos
     */
    public double getBeamPos() {
        return beamPos;
    }

    /**
     * Returns the Instrument's name.
     * @return name
     */
    public String getName() {
        return name;
    }
}
