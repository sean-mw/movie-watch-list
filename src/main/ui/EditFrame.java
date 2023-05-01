package ui;

import model.WatchList;

import javax.swing.*;
import java.awt.*;

// Represents a frame that allows the user to edit movie information
public class EditFrame extends JFrame {
    private final int movieIdx;
    private final WatchList watchList;

    // EFFECTS: sets up frame and displays it
    public EditFrame(WatchList watchList, int movieIdx) {
        super("Edit Movie");
        this.watchList = watchList;
        this.movieIdx = movieIdx;
        this.setLayout(new BorderLayout());
        this.add(new MovieInfoPanel(watchList.getMovieByIdx(movieIdx)), BorderLayout.NORTH);
        this.add(new EditRatingPanel(this), BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets the user rating for movie to the given parameter
    public void setUserRating(int rating) {
        this.watchList.setMovieUserRating(this.movieIdx, rating);
    }
}