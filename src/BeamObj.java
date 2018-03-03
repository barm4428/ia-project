/**
 * Created by Bryson Armstrong (HL2) on 3/3/2018.
 */
public abstract class BeamObj {

    protected double beamPos;

    public BeamObj(double beamPos) {
        this.beamPos = beamPos;
    }

    public double getBeamPosition() {
        return beamPos;
    }

    public void setBeamPos(double beamPos) {
        this.beamPos = beamPos;
    }
}
