import java.awt.*;
import java.io.Serializable;

/**
 * Created by Bryson Armstrong (HL2) on 2/28/2018.
 */
public class Dimmer extends BeamObj implements Comparable<Dimmer>, Serializable {

    private int number;

    public Dimmer(int number, double beamPos) {
        super(beamPos);
        this.number = number;
    }

    @Override
    public int compareTo(Dimmer o) {
        return (int) Math.ceil(beamPos - o.beamPos);
    }

    @Override
    public String toString() {
        return "Dimmer(number=" + number + ",beamPos=" + beamPos + ")";
    }

    public double getBeamPos() {
        return beamPos;
    }

    public void paintComponent(Graphics graphics, int x, int y, int width, int height) {
        graphics.drawString("" + number, x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Dimmer) {
            Dimmer d = (Dimmer) obj;
            return number == d.number;
        } else {
            return false;
        }
    }

    public int getNumber() {
        return number;
    }
}
