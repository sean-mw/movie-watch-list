package ui;

import model.Event;
import model.EventLog;
import model.WatchList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

// Represents a frame that displays a watch list and a menu
public class MainFrame extends JFrame {
    private static final String WATCH_LIST_PATH = "./data/watchList.json";
    private final WatchList watchList;
    private final ListPanel watchListPanel;

    // EFFECTS: sets up and displays the frame and creates a new empty watchlist
    public MainFrame() {
        super("Watchlist");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event);
                }
            }
        });
        this.watchList = new WatchList();
        this.watchListPanel = new ListPanel();
        this.watchListPanel.updateList(watchList);
        this.add(this.watchListPanel, BorderLayout.CENTER);
        this.add(new MainMenuPanel(this), BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: updates watch list panel to display current watch list
    public void updateWatchListPanel() {
        this.watchListPanel.updateList(this.watchList);
    }

    // MODIFIES: this
    // EFFECTS: deletes selected movie and updates display
    public void deleteSelectedMovie() {
        if (this.watchListPanel.isSelectionNotEmpty()) {
            this.watchList.removeMovieFromWatchListByIdx(this.watchListPanel.getSelectedIndex());
            this.updateWatchListPanel();
        }
    }

    // EFFECTS: opens new frame that allows user to edit selected movie
    public void editSelectedMovie() {
        if (this.watchListPanel.isSelectionNotEmpty()) {
            int index = this.watchListPanel.getSelectedIndex();
            new EditFrame(this.watchList, index);
        }
    }

    // EFFECTS: opens new frame that allows user to search for movies
    public void openSearchFrame() {
        new SearchFrame(this, this.watchList);
    }

    // MODIFIES: this
    // EFFECTS: loads watch list from file
    public void loadWatchList() {
        try {
            this.watchList.replaceWatchList(new JsonReader().readWatchList());
            this.updateWatchListPanel();
        } catch (IOException exception) {
            System.out.println("Unable to load Watch List...");
        }
    }

    // EFFECTS: saves watch list to file
    public void saveWatchList() {
        try {
            JsonWriter writer = new JsonWriter(WATCH_LIST_PATH);
            writer.open();
            writer.writeWatchList(this.watchList);
            writer.close();
        } catch (IOException exception) {
            System.out.println("Unable to save Watch List...");
        }
    }
}