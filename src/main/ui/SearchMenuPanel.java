package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a panel that contains buttons for adding a movie or canceling
public class SearchMenuPanel extends JPanel implements ActionListener {
    private static final String ADD_MOVIE_STRING = "Add Selected";
    private static final String CANCEL_STRING = "Cancel";
    private final JButton addMovieButton;
    private final JButton cancelButton;
    private final SearchFrame searchFrame;

    // EFFECTS: sets up the panel
    public SearchMenuPanel(SearchFrame searchFrame) {
        this.searchFrame = searchFrame;
        this.addMovieButton = new JButton(ADD_MOVIE_STRING);
        this.addMovieButton.addActionListener(this);
        this.cancelButton = new JButton(CANCEL_STRING);
        this.cancelButton.addActionListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.add(addMovieButton);
        this.add(cancelButton);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds movie to watch list and closes search frame if action source is add movie button, closes search
    // frame if cancel button is action source
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.addMovieButton) {
            if (this.searchFrame.addSelectedMovie()) {
                this.searchFrame.dispose();
            }
        } else if (e.getSource() == this.cancelButton) {
            this.searchFrame.dispose();
        }
    }
}
