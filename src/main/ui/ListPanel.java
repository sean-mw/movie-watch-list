package ui;

import model.Movie;
import model.MovieList;

import javax.swing.*;
import java.awt.*;

// Represents a panel that displays a list of movies
public class ListPanel extends JPanel {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;
    private final JList<Movie> list;
    private final DefaultListModel<Movie> listModel;

    // EFFECTS: sets up the panel
    public ListPanel() {
        this.listModel = new DefaultListModel<>();
        this.list = new JList<>(this.listModel);
        this.list.setCellRenderer(new MovieCellRenderer());
        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(this.list);
        scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.add(scrollPane);
    }

    // MODIFIES: this
    // EFFECTS: clears current list model and replaces it with movies in movieList
    public void updateList(MovieList movieList) {
        this.clearElements();
        for (Movie movie : movieList) {
            this.listModel.addElement(movie);
        }
    }

    // MODIFIES: this
    // EFFECTS: clears list model
    public void clearElements() {
        this.listModel.clear();
    }

    // EFFECTS: returns true if there is a selection, false otherwise
    public boolean isSelectionNotEmpty() {
        return !this.list.isSelectionEmpty();
    }

    // EFFECTS: returns selected index
    public int getSelectedIndex() {
        return this.list.getSelectedIndex();
    }

    // EFFECTS: returns selected movie
    public Movie getSelectedMovie() {
        return this.list.getSelectedValue();
    }
}