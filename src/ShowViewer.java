import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * Viewer class for a Show. Also contains the main menu and all navigation functions.
 */
public class ShowViewer {

    private static JFrame frame;
    private static Show currentShow;

    /**
     * Entry point of program
     */
    public static void main(String[] args) {
        setFrame(new Show());
    }

    /**
     * Displays a new JFrame with a show drawn inside.
     * @param s The show to display.
     */
    private static void setFrame(Show s) {
        frame = new JFrame();
        frame.setSize(1000, 500);
        frame.setTitle("Hello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(menuBar());
        if (s.getBeams().size()>0) {
            frame.add(new ShowComponent(s));
        } else {
            JPanel panel = new JPanel();
            JButton loadFile = new JButton("Load show from File");
            loadFile.addActionListener((ActionEvent event) -> {
                Show loadedShow = loadShow();
                frame.setVisible(false);
                if (loadedShow!=null) {
                    setFrame(loadedShow);
                } else {
                    setFrame(s);
                }
            });
            panel.add(loadFile);
            JButton addBeamButton = new JButton("Create new show");
            addBeamButton.addActionListener((ActionEvent event) -> {addBeam();});
            panel.add(addBeamButton);

            frame.add(panel);
        }

        currentShow = s;
        frame.setVisible(true);
    }

    /**
     * Called by the main menu to run functionality to add a beam to the current show.
     */
    private static void addBeam() {
        JComboBox<Integer> idField = new JComboBox<>();
        for (int i=0; i<=currentShow.getBeams().size(); i++) {
            idField.addItem(i+1);
        }

        JTextField lengthField = new JTextField("100", 5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("ID#:"));
        myPanel.add(idField);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Length:"));
        myPanel.add(lengthField);
        myPanel.add(new JLabel("ft"));

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Beam setup", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = idField.getItemAt(idField.getSelectedIndex());
                int length = Integer.parseInt(lengthField.getText());
                Beam beam = new Beam(id, length);
                editBeam(beam);
                currentShow.addBeam(beam);

                frame.getContentPane().removeAll();
                frame.add(new ShowComponent(currentShow));
                frame.getContentPane().validate();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error", "Error. Length must be an integer", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Calls a dialog box with options for the user to select a beam.
     * @return The Beam that the user selects.
     */
    private static Beam getBeamInput() {
        JComboBox<Integer> idField = new JComboBox<>();
        for (Beam beam:currentShow.getBeams()) {
            idField.addItem(beam.getId());
        }

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Select Beam ID: "));
        myPanel.add(idField);

        Beam found = null;

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Beam setup", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int id = idField.getItemAt(idField.getSelectedIndex());
            for (Beam beam:currentShow.getBeams()) {
                if (beam.getId()==id) {
                    found = beam;
                }
            }
        }
        return found;
    }

    /**
     * GUI allowing a user to edit a beam.
     * @param beam The beam to be edited.
     */
    private static void editBeam(Beam beam) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 20;

        c.gridx = 0;
        c.gridy = 0;
        panel.add(new JLabel("Dimmers"), c);

        c.gridx = 2;
        panel.add(new JLabel("Instruments"), c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(new JLabel("Number"), c);

        c.gridx = 1;
        panel.add(new JLabel("Position"), c);

        c.gridx = 2;
        panel.add(new JLabel("Name"), c);

        c.gridx = 3;
        panel.add(new JLabel("Position"), c);

        int dy = c.gridy+1;
        int iy = c.gridy+1;

        for (Dimmer d:beam.getDimmers()) {
            JLabel numField = new JLabel(d.getNumber()+"");
            JLabel posField = new JLabel(d.getBeamPos()+"");
            c.gridx = 0;
            c.gridy = dy;
            panel.add(numField, c);
            c.gridx = 1;
            panel.add(posField, c);
            dy++;
        }

        for (Instrument i:beam.getInstruments()) {
            JLabel nameField = new JLabel(i.getName());
            JLabel posField = new JLabel(i.getBeamPos()+"");
            c.gridx = 2;
            c.gridy = iy;
            panel.add(nameField, c);
            c.gridx = 3;
            panel.add(posField, c);
            iy++;
        }

        String[] options = {"Add Dimmer", "Add Instrument", "Done"};
        boolean running = true;
        while (running) {
            int result = JOptionPane.showOptionDialog(null,
                    panel,
                    "Edit Beam" + beam.getId(),
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    null);
            if (result == 0) {
                String[] input = TwoPromptDialog.newBox("Dimmer number: ", "Beam Position: ", "New Dimmer");
                try {
                    int number = Integer.parseInt(input[0]);
                    double position = Double.parseDouble(input[1]);
                    Dimmer dim = new Dimmer(number, position);
                    beam.add(dim);

                    c.gridy = dy;
                    c.gridx = 0;
                    panel.add(new JLabel(input[0]), c);
                    c.gridx = 1;
                    panel.add(new JLabel(input[1]), c);
                    dy++;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error", "Error. Incompatible types", JOptionPane.WARNING_MESSAGE);
                } catch (Exception e) {

                }
            } else if (result == 1) {
                String[] input = TwoPromptDialog.newBox("Instrument Name: ", "Beam Position: ", "New Instrument");
                try {
                    String name = input[0];
                    double position = Double.parseDouble(input[1]);
                    Instrument instrument = new Instrument(position, name);
                    beam.add(instrument);

                    c.gridy = iy;
                    c.gridx = 2;
                    panel.add(new JLabel(input[0]), c);
                    c.gridx = 3;
                    panel.add(new JLabel(input[1]), c);
                    iy++;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error", "Error. Incompatible types", JOptionPane.WARNING_MESSAGE);
                } catch (Exception e) {

                }
            } else if (result == 2 || result==JOptionPane.CLOSED_OPTION) {
                running = false;
            }
        }
    }

    /**
     * A method to create GUI for user to remove dimmers and instruments from a Beam.
     */
    private static void remove() {
        String[] options = {"Remove Dimmer", "Remove Instrument", "Done"};
        boolean running = true;
        while (running) {
            Beam beam = getBeamInput();
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipadx = 20;

            c.gridx = 0;
            c.gridy = 0;
            panel.add(new JLabel("Dimmers"), c);

            c.gridx = 2;
            panel.add(new JLabel("Instruments"), c);

            c.gridx = 0;
            c.gridy = 1;
            panel.add(new JLabel("Number"), c);

            c.gridx = 1;
            panel.add(new JLabel("Position"), c);

            c.gridx = 2;
            panel.add(new JLabel("Name"), c);

            c.gridx = 3;
            panel.add(new JLabel("Position"), c);

            int dy = c.gridy+1;
            int iy = c.gridy+1;

            for (Dimmer d:beam.getDimmers()) {
                JLabel numField = new JLabel(d.getNumber()+"");
                JLabel posField = new JLabel(d.getBeamPos()+"");
                c.gridx = 0;
                c.gridy = dy;
                panel.add(numField, c);
                c.gridx = 1;
                panel.add(posField, c);
                dy++;
            }

            for (Instrument i:beam.getInstruments()) {
                JLabel nameField = new JLabel(i.getName());
                JLabel posField = new JLabel(i.getBeamPos()+"");
                c.gridx = 2;
                c.gridy = iy;
                panel.add(nameField, c);
                c.gridx = 3;
                panel.add(posField, c);
                iy++;
            }

            int result = JOptionPane.showOptionDialog(null,
                    panel,
                    "A",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    null);
            if (result == 0) {
                String input = JOptionPane.showInputDialog("Enter Dimmer number to remove: ");
                try {
                    int number = Integer.parseInt(input);
                    if (beam.removeDimmer(number)) {
                        JOptionPane.showMessageDialog(null, "Dimmer removed!");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error", "Error. Incompatible types", JOptionPane.WARNING_MESSAGE);
                }
            } else if (result == 1) {
                String input = JOptionPane.showInputDialog("Enter Instrument name to remove: ");
                if (beam.removeInstrument(input)) {
                    JOptionPane.showMessageDialog(null, "Instrument removed!");
                }
            } else if (result == 2 || result==JOptionPane.CLOSED_OPTION) {
                running = false;
            }
        }
        frame.getContentPane().validate();
    }

    /**
     * Generates the JMenuBar for the JFrame.
     * @return The JMenuBar to be added to the JFrame.
     */
    private static JMenuBar menuBar() {
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

        JMenuItem addBeam = new JMenuItem("Add beam");
        addBeam.addActionListener((ActionEvent Event) -> {addBeam();});
        beam.add(addBeam);

        JMenuItem editBeam = new JMenuItem("Edit beam");
        editBeam.addActionListener((ActionEvent event) -> {editBeam(getBeamInput());});
        beam.add(editBeam);

        JMenuItem remove = new JMenuItem("Remove Dimmers/Instruments");
        remove.addActionListener((ActionEvent event) -> {remove();});
        beam.add(remove);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(file);
        menuBar.add(beam);

        return menuBar;
    }

    /**
     * GUI allowing user to choose a show to load from file.
     * @return The Show loaded by the user.
     */
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

    /**
     * GUI allowing user to save the current show.
     */
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

    /**
     * Data processing allowing program to serialize a show to a file. Used in saving the current show.
     * @param s The show to be serialized.
     * @param file The destination file.
     */
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

    /**
     * Data processing allowing program to read a show from a file.
     * @param file The file containing a serialized Show object.
     * @return The Show contained in the file.
     */
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
