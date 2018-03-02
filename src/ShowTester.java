import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * Created by Bryson Armstrong (HL2) on 3/1/2018.
 */
public class ShowTester {

    private static JFrame frame;

    public static void main(String[] args) {
        /*String test1= JOptionPane.showInputDialog("Please input mark for test 1: ");
        System.out.println(test1);*/
        /*JFileChooser chooser = new JFileChooser();
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
        }*/

        Show s = getShow();
        setFrame(s);
    }

    private static void setFrame(Show s) {
        frame = new JFrame();
        frame.setSize(1000, 500);
        frame.setTitle("Hello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(menuBar(s));
        frame.add(new ShowComponent(s));

        frame.setVisible(true);
    }

    private static JMenuBar menuBar(Show s) {
        JMenu file = new JMenu("File");

        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener((ActionEvent event) -> {System.exit(0);});
        file.add(quit);

        JMenuItem saveShow = new JMenuItem("Save Show");
        saveShow.addActionListener((ActionEvent event) -> {saveShow(s);});
        file.add(saveShow);

        JMenuItem loadShow = new JMenuItem("Load Show");
        loadShow.addActionListener((ActionEvent event) -> {
            Show newShow = loadShow();
            frame.getContentPane().removeAll();
            frame.add(new ShowComponent(newShow));
            frame.getContentPane().validate();
        });
        file.add(loadShow);

        JMenu beam = new JMenu("Beam");
        beam.add(new JMenuItem("Add beam"));
        beam.add(new JMenuItem("Assign dimmers"));

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(file);
        menuBar.add(beam);

        return menuBar;
    }

    private static Show loadShow() {
        Show s = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("shows"));
        javax.swing.filechooser.FileFilter filter = new FileNameExtensionFilter("Show File (.ser)","ser");
        chooser.setFileFilter(filter);
        int retrieval = chooser.showOpenDialog(null);
        if (retrieval == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();
                s = unserialize(file);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return s;
    }

    private static void saveShow(Show s) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("shows"));
        javax.swing.filechooser.FileFilter filter = new FileNameExtensionFilter("Show File (.ser)","ser");
        chooser.setFileFilter(filter);
        int retrieval = chooser.showSaveDialog(null);
        if (retrieval == JFileChooser.APPROVE_OPTION) {
            try {
                File file;
                String fileName = chooser.getSelectedFile().getName();
                if (fileName.substring(fileName.length()-4).equals(".ser")) {
                    file = chooser.getSelectedFile();
                } else {
                    file = new File(chooser.getCurrentDirectory() + "/" + chooser.getSelectedFile().getName() + ".ser");
                }
                serialize(s, file);
                JOptionPane.showMessageDialog(null, "Show saved!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static Show getShow() {
        Show s = new Show();
        Beam e1 = new Beam(2, 100);
        e1.add(new Dimmer(1, 10));
        e1.add(new Dimmer(2, 50));
        e1.add(new Instrument(10, "A"));
        e1.add(new Instrument(30, "C"));
        Beam e2 = new Beam(2, 100);
        e2.add(new Dimmer(3, 10));
        e2.add(new Dimmer(4, 50));
        e2.add(new Instrument(50, "B"));

        s.addBeam(e1);
        s.addBeam(e2);

        return s;
    }

    private static void serialize(Show s, File file) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(s);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static Show unserialize(File file) {
        Show s = null;
        try {
            FileInputStream fileIn = new FileInputStream(file);
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
