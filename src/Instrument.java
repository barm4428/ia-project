import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Bryson Armstrong (HL2) on 2/28/2018.
 */
public class Instrument implements Comparable<Instrument>, Serializable {

    private double beamPos;
    private String name;
    public static final int CHORD_LENGTH = 2;
    private static final String IMG_LOC = "resources/etc.jpg";

    public Instrument(double beamPos, String name) {
        this.beamPos = beamPos;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Instrument(name=" + name + ",beamPos=" + beamPos + ")";
    }

    @Override
    public int compareTo(Instrument o) {
        return (int) Math.ceil(beamPos - o.beamPos);
    }

    public void paintComponent(Graphics graphics, int x, int y, int width, int height) {
        if (graphics instanceof Graphics2D) {
            Image img = null;
            try {
                img = ImageIO.read(new File(IMG_LOC));
            } catch (IOException e) {
            }
            Graphics2D g2 = (Graphics2D) graphics;
            g2.drawImage(img, x, y, 30, 30, null);
        }
    }

    public double getBeamPos() {
        return beamPos;
    }
}
