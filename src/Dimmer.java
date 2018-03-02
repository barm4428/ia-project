import java.awt.*;

/**
 * Created by Bryson Armstrong (HL2) on 2/28/2018.
 */
public class Dimmer implements Comparable<Dimmer>, Paintable {

    private int number;
    private double beamPos;

    public Dimmer(int number, double beamPos) {
        this.number = number;
        this.beamPos = beamPos;
    }

    @Override
    public int compareTo(Dimmer o) {
        return (int) Math.ceil(beamPos - o.beamPos);
    }

    @Override
    public String toString() {
        return number + "";
    }

    public double getBeamPos() {
        return beamPos;
    }

    @Override
    public void paintComponent(Graphics graphics, int x, int y, int width, int height) {
        graphics.drawString(toString(), x, y);

    }
}
