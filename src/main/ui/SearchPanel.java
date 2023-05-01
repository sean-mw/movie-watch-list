package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a panel that allows the user to search for a movie by title
public class SearchPanel extends JPanel implements ActionListener {
    private static final String SEARCH_STRING = "Search Movie";
    private final SearchFrame searchFrame;
    private final JButton searchButton;
    private final JTextField movieTitle;

    // EFFECTS: sets up the panel
    public SearchPanel(SearchFrame searchFrame) {
        this.searchFrame = searchFrame;
        this.movieTitle = new JTextField(10);
        this.searchButton = new JButton(SEARCH_STRING);
        this.searchButton.addActionListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.add(this.movieTitle);
        this.add(this.searchButton);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds search results to movie list panel based on user input in movie title field if action source is
    // search button
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.searchButton) {
            this.searchFrame.updateMovieSearchListPanel(this.movieTitle.getText());
        }
    }
}
