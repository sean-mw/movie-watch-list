package ui;

import model.Movie;
import model.MovieList;
import model.MovieSearchList;
import model.WatchList;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// Represents a frame that allows the user to search for a movie
public class SearchFrame extends JFrame {
    private final MainFrame mainFrame;
    private MovieSearchList movieSearchList;
    private final WatchList watchList;
    private final ListPanel movieSearchListPanel;

    // EFFECTS: sets up the frame and loads movie list
    public SearchFrame(MainFrame mainFrame, WatchList watchList) {
        super("Add Movie");
        this.mainFrame = mainFrame;
        this.setLayout(new BorderLayout());
        this.loadMovieSearchList();
        this.watchList = watchList;
        this.movieSearchListPanel = new ListPanel();
        this.add(new SearchPanel(this), BorderLayout.NORTH);
        this.add(movieSearchListPanel, BorderLayout.CENTER);
        this.add(new SearchMenuPanel(this), BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds selected movie to watchlist and updates the watch list panel if selected movie is not null
    public boolean addSelectedMovie() {
        Movie movie = this.movieSearchListPanel.getSelectedMovie();
        if (movie != null) {
            this.watchList.addMovieToWatchList(movie);
            this.mainFrame.updateWatchListPanel();
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: searches movie list based on query and updates movie list panel with the results
    public void updateMovieSearchListPanel(String query) {
        MovieList results = this.movieSearchList.searchMovie(query);
        this.movieSearchListPanel.updateList(results);
    }

    // MODIFIES: this
    // EFFECTS: loads movie list from file
    public void loadMovieSearchList() {
        try {
            this.movieSearchList = new JsonReader().readMovieSearchList();
        } catch (IOException e) {
            System.out.println("Unable to load movie list...");
            this.movieSearchList = new MovieSearchList();
        }
    }
}
