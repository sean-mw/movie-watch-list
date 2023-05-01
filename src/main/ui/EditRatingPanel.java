package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a panel that allows the user to edit a movie rating
public class EditRatingPanel extends JPanel implements ActionListener {
    private static final String EDIT_RATING_STRING = "Change Rating";
    private static final String CANCEL_STRING = "Cancel";
    private static final String[] RATING_OPTIONS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private final JComboBox<String> ratingOptions;
    private final JButton editRatingButton;
    private final JButton cancelButton;
    private final EditFrame editFrame;

    // EFFECTS: sets up the panel
    public EditRatingPanel(EditFrame editFrame) {
        this.editFrame = editFrame;
        this.ratingOptions = new JComboBox<>(RATING_OPTIONS);
        this.ratingOptions.setSelectedIndex(0);
        this.editRatingButton = new JButton(EDIT_RATING_STRING);
        this.editRatingButton.addActionListener(this);
        this.cancelButton = new JButton(CANCEL_STRING);
        this.cancelButton.addActionListener(this);
        this.add(ratingOptions);
        this.add(editRatingButton);
        this.add(cancelButton);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: changes movie rating and closes frame if the action source is the edit button, closes frame if action
    // source is cancel button
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.editRatingButton) {
            Object selectedObject = ratingOptions.getSelectedItem();
            if (selectedObject != null) {
                int rating = Integer.parseInt(selectedObject.toString());
                this.editFrame.setUserRating(rating);
                this.editFrame.dispose();
            }
        } else if (e.getSource() == this.cancelButton) {
            this.editFrame.dispose();
        }
    }
}
