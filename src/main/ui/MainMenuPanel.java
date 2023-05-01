package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a panel that contains buttons for modifying a watch list
public class MainMenuPanel extends JPanel implements ActionListener {
    private final MainFrame mainFrame;
    private static final String ADD_MOVIE_STRING = "Add Movie";
    private static final String DELETE_MOVIE_STRING = "Delete Movie";
    private static final String EDIT_MOVIE_STRING = "Edit Movie";
    private static final String LOAD_WATCHLIST_STRING = "Load Watch List";
    private static final String SAVE_WATCHLIST_STRING = "Save Watch List";
    private final JButton addMovieButton;
    private final JButton deleteMovieButton;
    private final JButton editMovieButton;
    private final JButton loadWatchListButton;
    private final JButton saveWatchListButton;

    // EFFECTS: sets up the panel
    public MainMenuPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.addMovieButton = new JButton(ADD_MOVIE_STRING);
        this.addMovieButton.addActionListener(this);
        this.deleteMovieButton = new JButton(DELETE_MOVIE_STRING);
        this.deleteMovieButton.addActionListener(this);
        this.editMovieButton = new JButton(EDIT_MOVIE_STRING);
        this.editMovieButton.addActionListener(this);
        this.loadWatchListButton = new JButton(LOAD_WATCHLIST_STRING);
        this.loadWatchListButton.addActionListener(this);
        this.saveWatchListButton = new JButton(SAVE_WATCHLIST_STRING);
        this.saveWatchListButton.addActionListener(this);
        this.add(addMovieButton);
        this.add(deleteMovieButton);
        this.add(editMovieButton);
        this.add(loadWatchListButton);
        this.add(saveWatchListButton);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: handles button presses
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.addMovieButton) {
            this.mainFrame.openSearchFrame();
        } else if (e.getSource() == this.deleteMovieButton) {
            this.mainFrame.deleteSelectedMovie();
        } else if (e.getSource() == this.editMovieButton) {
            this.mainFrame.editSelectedMovie();
        } else if (e.getSource() == this.loadWatchListButton) {
            this.mainFrame.loadWatchList();
        } else if (e.getSource() == this.saveWatchListButton) {
            this.mainFrame.saveWatchList();
        }
    }
}
