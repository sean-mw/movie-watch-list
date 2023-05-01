package ui;

import model.Movie;

import javax.swing.*;
import java.awt.*;

// Adapted from ListCellRenderer example at this link:
// http://www.java2s.com/Code/Java/Swing-JFC/UseJListcomponenttodisplaycustomobjectswithListCellRenderer.htm
// Represents a cell renderer that renders movie cells for a list
class MovieCellRenderer extends JLabel implements ListCellRenderer<Object> {
    private static final String IMAGES_PATH = "./data/icons/";
    private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

    // EFFECTS: sets up renderer
    public MovieCellRenderer() {
        setOpaque(true);
        setIconTextGap(12);
    }

    // MODIFIES: this
    // EFFECTS: renders a movie cell with the correct text, image, background color, and foreground color
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                                                  boolean cellHasFocus) {
        Movie movie = (Movie) value;
        setText(movie.toString());
        String path = IMAGES_PATH + movie.getId() + ".jpg";
        ImageIcon image = new ImageIcon(path);
        setIcon(image);
        if (isSelected) {
            setBackground(HIGHLIGHT_COLOR);
            setForeground(Color.white);
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
        }
        return this;
    }
}
