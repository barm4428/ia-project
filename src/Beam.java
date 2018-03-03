import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Bryson Armstrong (HL2) on 2/28/2018.
 */
public class Beam implements Serializable {

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

    public void paintComponent(Graphics graphics, int x, int y, int width, int height) {
        graphics.drawString("Beam " + id, x+5, y+5);
        graphics.drawLine(x+50, y, width-25, y);
        for (Dimmer d:dimmers) {
            int pos = (int) Math.ceil(x + 50 + (d.getBeamPos()/length*(width-75)));
            d.paintComponent(graphics, pos, y-2, width, height);
        }
        for (Instrument i: instruments) {
            int pos = (int) Math.ceil(x + (i.getBeamPos()/length*width));
            i.paintComponent(graphics, pos, y+2, 20, 20);
        }
    }

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

    public int getId() {
        return id;
    }

    public ArrayList<Dimmer> getDimmers() {
        return dimmers;
    }

    public ArrayList<Instrument> getInstruments() {
        return instruments;
    }

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
