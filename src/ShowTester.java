import javax.swing.*;
import java.io.*;

/**
 * Created by Bryson Armstrong (HL2) on 3/1/2018.
 */
public class ShowTester {

    public static void main(String[] args) {
        Show s = getShow();
        saveShow("venues/output", s);
    }

    private static void setFrame() {
        JFrame frame = new JFrame();
        frame.setSize(1000, 500);
        frame.setTitle("Hello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(menuBar());
        frame.add(new ShowComponent(getShow()));

        frame.setVisible(true);
    }

    private static JMenuBar menuBar() {
        JMenu file = new JMenu("File");
        file.add(new JMenuItem("Quit"));
        file.add(new JMenuItem("B"));

        JMenu beam = new JMenu("Beam");
        beam.add(new JMenuItem("Add beam"));

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(file);
        menuBar.add(beam);

        return menuBar;
    }

    private static Show getShow() {
        Show s = new Show();
        Beam e1 = new Beam(2, 100);
        e1.add(new Dimmer(1, 10));
        e1.add(new Dimmer(2, 50));
        e1.add(new Instrument(10, "A"));
        Beam e2 = new Beam(2, 100);
        e2.add(new Dimmer(3, 10));
        e2.add(new Dimmer(4, 50));
        e2.add(new Instrument(10, "B"));

        s.addBeam(e1);
        s.addBeam(e2);

        return s;
    }

    private static void saveShow(String name, Show s) {
        try {
            PrintWriter writer = new PrintWriter(name + ".txt");
            writer.print(s.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
