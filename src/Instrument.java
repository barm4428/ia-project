/**
 * Created by Bryson Armstrong (HL2) on 2/28/2018.
 */
public class Instrument implements Comparable<Instrument> {

    private int beamPos;
    private String name;
    public static final int CHORD_LENGTH = 2;

    public Instrument(int beamPos, String name) {
        this.beamPos = beamPos;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Instrument o) {
        return beamPos - o.beamPos;
    }
}
