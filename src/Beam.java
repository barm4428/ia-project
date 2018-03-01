import java.util.ArrayList;

/**
 * Created by Bryson Armstrong (HL2) on 2/28/2018.
 */
public class Beam {

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
}
