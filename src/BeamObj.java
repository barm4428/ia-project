import java.io.Serializable;

/**
 * Models an object that is contained on a beam (such as a Dimmer or an Instrument).
 */
public abstract class BeamObj implements Comparable<BeamObj>, Serializable {

    protected double beamPos;

    /**
     * Constructs a new BeamObj.
     * @param beamPos The objects position on the beam.
     */
    public BeamObj(double beamPos) {
        this.beamPos = beamPos;
    }

    /**
     * Returns the objects position on the beam.
     * @return The Objects position on the beam.
     */
    public double getBeamPosition() {
        return beamPos;
    }

    /**
     * Changes the objects position on the beam.
     * @param beamPos The new position on the beam.
     */
    public void setBeamPos(double beamPos) {
        this.beamPos = beamPos;
    }

    /**
     * Compares the BeamObj to a different BeamObj of any type, based on position on the beam.
     * @param o The BeamObj to be compared to.
     * @return Their relative position on the beam (neg if this object is closer to point 0).
     */
    @Override
    public int compareTo(BeamObj o) {
        return (int) Math.ceil(beamPos - o.beamPos);
    }

}
