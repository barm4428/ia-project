import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Bryson Armstrong (HL2) on 2/28/2018.
 */
public class Beam implements Paintable {

    private int id;
    private int length;
    private ArrayList<Dimmer> dimmers;
    private ArrayList<Instrument> instruments;

    public Beam(int id, int length) {
        this.id = id;
        this.length = length;
        dimmers = new ArrayList<>();
        instruments = new ArrayList<>();
    }

    public void assignDimmers() {
        //Does something
    }

    public void add(Dimmer d) {
        dimmers.add(d);
    }

    public void add(Instrument i) {
        instruments.add(i);
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void paintComponent(Graphics graphics, int x, int y, int width, int height) {
        graphics.drawLine(x+5, y, width-25, y);
        for (Dimmer d:dimmers) {
            int pos = (int) Math.ceil(x + (d.getBeamPos()/length*width));
            d.paintComponent(graphics, pos, y-2, width, height);
        }
        for (Instrument i: instruments) {
            int pos = (int) Math.ceil(x + (i.getBeamPos()/length*width));
            i.paintComponent(graphics, pos, y+2, 20, 20);
        }
    }
}
