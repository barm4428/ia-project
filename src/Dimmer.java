/**
 * Created by Bryson Armstrong (HL2) on 2/28/2018.
 */
public class Dimmer implements Comparable<Dimmer> {

    private int number;
    private int beamPos;

    public Dimmer(int number, int beamPos) {
        this.number = number;
        this.beamPos = beamPos;
    }

    @Override
    public int compareTo(Dimmer o) {
        return beamPos - o.beamPos;
    }

    @Override
    public String toString() {
        return number + "";
    }
}
