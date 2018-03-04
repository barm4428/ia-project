import javax.swing.*;

/**
 * Creates a dual textBox dialog box asking for user input, and returns the entered user input.
 */
public class TwoPromptDialog {

    /**
     * Creates a new JOptionPanel with the given prompts and title, and returns the user input.
     * @param m1 The prompt for the first text box.
     * @param m2 The prompt for the second text box.
     * @param title The title for the dialog box.
     * @return A String array containing the two user inputs.
     */
    public static String[] newBox(String m1, String m2, String title) {
        final int NUM_COLUMNS = 5;

        JTextField t1 = new JTextField(NUM_COLUMNS);
        JTextField t2 = new JTextField(NUM_COLUMNS);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel(m1));
        myPanel.add(t1);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel(m2));
        myPanel.add(t2);

        String[] input = null;
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                title, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            input = new String[2];
            input[0] = t1.getText();
            input[1] = t2.getText();
        }
        return input;
    }

}
