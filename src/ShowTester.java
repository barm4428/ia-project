import javax.swing.*;
import java.io.*;

/**
 * Created by Bryson Armstrong (HL2) on 3/1/2018.
 */
public class ShowTester {

    public static void main(String[] args) {
        Show s = getShow();
        saveShow(s, "venue");
        Show s2 = readShow("venue");
        setFrame(s2);
    }

    private static void setFrame(Show s) {
        JFrame frame = new JFrame();
        frame.setSize(1000, 500);
        frame.setTitle("Hello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(menuBar());
        frame.add(new ShowComponent(s));

        frame.setVisible(true);
    }

    private static JMenuBar menuBar() {
        JMenu file = new JMenu("File");
        file.add(new JMenuItem("Quit"));
        file.add(new JMenuItem("Save Show"));
        file.add(new JMenuItem("Save Venue"));
        file.add(new JMenuItem("Load Show"));
        file.add(new JMenuItem("Load Venue"));

        JMenu beam = new JMenu("Beam");
        beam.add(new JMenuItem("Add beam"));
        beam.add(new JMenuItem("Assign dimmers"));

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

    private static void saveShow(Show s, String fileName) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("venues/"+ fileName + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(s);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static Show readShow(String fileName) {
        Show s = null;
        try {
            FileInputStream fileIn = new FileInputStream("venues/" + fileName + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            s = (Show) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Show class not found");
            c.printStackTrace();
        }
        return s;
    }

}
