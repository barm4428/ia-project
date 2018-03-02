import javax.swing.*;

/**
 * Created by Bryson Armstrong (HL2) on 3/1/2018.
 */
public class ShowTester {

    public static void main(String[] args) {
        Show s = new Show();
        s.addBeam(new Beam(1, 100));
        Beam e1 = new Beam(2, 100);
        e1.add(new Dimmer(1, 10));
        e1.add(new Dimmer(2, 50));
        e1.add(new Instrument(10, "A"));
        s.addBeam(e1);


        JFrame frame = new JFrame();
        frame.setSize(1000, 500);
        frame.setTitle("Hello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new ShowComponent(s));

        frame.setVisible(true);
    }

}
