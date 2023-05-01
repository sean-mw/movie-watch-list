package ui;

import model.Movie;

import javax.swing.*;

// Represents a panel that contains information for a movie
public class MovieInfoPanel extends JPanel {

    // EFFECTS: sets up the panel
    public MovieInfoPanel(Movie movie) {
        JTextArea movieInfo = new JTextArea(movie.toStringFull());
        movieInfo.setEditable(false);
        this.add(movieInfo);
    }
}
