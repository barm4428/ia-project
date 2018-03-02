import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * Created by Bryson Armstrong (HL2) on 3/1/2018.
 */
public class ShowTester {

    private static JFrame frame;
    private static Show currentShow;

    public static void main(String[] args) {
        setFrame(new Show());
    }

    private static void setFrame(Show s) {
        frame = new JFrame();
        frame.setSize(1000, 500);
        frame.setTitle("Hello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(menuBar(s));
        if (s.getBeams().size()>0) {
            frame.add(new ShowComponent(s));
        } else {
            JPanel panel = new JPanel();
            JButton loadFile = new JButton("Load show from File");
            loadFile.addActionListener((ActionEvent event) -> {loadShow();});
            panel.add(loadFile);
            JButton addBeamButton = new JButton("Create new show");
            addBeamButton.addActionListener((ActionEvent event) -> {addBeam();});
            panel.add(addBeamButton);

            frame.add(panel);
        }

        currentShow = s;
        frame.setVisible(true);
    }

    private static void addBeam() {
        JComboBox<Integer> idField = new JComboBox<>();
        for (int i=0; i<=currentShow.getBeams().size(); i++) {
            idField.addItem(i+1);
        }

        JTextField lengthField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("ID#:"));
        myPanel.add(idField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Length:"));
        myPanel.add(lengthField);
        myPanel.add(new JLabel("ft"));

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Beam setup", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = idField.getItemAt(idField.getSelectedIndex());
                int length = Integer.parseInt(lengthField.getText());
                currentShow.addBeam(new Beam(id, length));

                frame.getContentPane().removeAll();
                frame.add(new ShowComponent(currentShow));
                frame.getContentPane().validate();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private static JMenuBar menuBar(Show s) {
        JMenu file = new JMenu("File");

        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener((ActionEvent event) -> {System.exit(0);});
        file.add(quit);

        JMenuItem saveShow = new JMenuItem("Save Show");
        saveShow.addActionListener((ActionEvent event) -> {saveShow();});
        file.add(saveShow);

        JMenuItem loadShow = new JMenuItem("Load Show");
        loadShow.addActionListener((ActionEvent event) -> {
            Show newShow = loadShow();
            if (currentShow == null) {
                frame.setVisible(false);
                setFrame(newShow);
            } else {
                switch(JOptionPane.showConfirmDialog(null, "Save current show first?")) {
                    case JOptionPane.YES_OPTION:
                        saveShow();
                        frame.setVisible(false);
                        setFrame(newShow);
                        break;
                    case JOptionPane.NO_OPTION:
                        frame.setVisible(false);
                        setFrame(newShow);
                        break;
                }
            }
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

    private static void saveShow() {
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
                serialize(currentShow, file);
                JOptionPane.showMessageDialog(null, "Show saved!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static Show getShow() {
        JFrame showFrame = new JFrame();
        showFrame.setSize(1000, 500);
        showFrame.setTitle("Edit Show");
        showFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        showFrame.setVisible(true);

        /*
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
        s.addBeam(e2);*/

        return null;
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return s;
    }

}
