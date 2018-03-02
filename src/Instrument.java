import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Bryson Armstrong (HL2) on 2/28/2018.
 */
public class Instrument implements Comparable<Instrument>, Paintable {

    private double beamPos;
    private String name;
    public static final int CHORD_LENGTH = 2;
    Image img;

    public Instrument(double beamPos, String name) {
        this.beamPos = beamPos;
        this.name = name;

        img = null;
        try {
            img = ImageIO.read(new File("resources/etc.jpg"));
        } catch (IOException e) {
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Instrument o) {
        return (int) Math.ceil(beamPos - o.beamPos);
    }

    @Override
    public void paintComponent(Graphics graphics, int x, int y, int width, int height) {
        if (graphics instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D) graphics;
            g2.drawImage(img, x, y, 30, 30, null);
        }
    }

    public double getBeamPos() {
        return beamPos;
    }
}
