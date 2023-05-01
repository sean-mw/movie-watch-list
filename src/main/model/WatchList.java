package model;


// Represents a movie list that contains watched movies
public class WatchList extends MovieList {

    // EFFECTS: calls superclass constructor
    public WatchList() {
        super();
    }

    // REQUIRES: movieIdx < getSize() and movieIdx > 0
    // MODIFIES: this
    // EFFECTS: changes a movie's user rating
    public void setMovieUserRating(int movieIdx, int userRating) {
        Movie movie = this.getMovieByIdx(movieIdx);
        movie.setUserRating(userRating);
        EventLog.getInstance().logEvent(new Event("Changed user rating for " + movie.getTitle() + " to: "
                                                  + userRating));
    }

    // MODIFIES: this
    // EFFECTS: movie is added to watch list
    public void addMovieToWatchList(Movie movie) {
        this.addMovie(movie);
        EventLog.getInstance().logEvent(new Event("Added movie: " + movie.getTitle()));
    }

    // REQUIRES: movieIdx < getSize() and getSize() != 0
    // MODIFIES: this
    // EFFECTS: movie at idx is removed from watch list
    public void removeMovieFromWatchListByIdx(int idx) {
        EventLog.getInstance().logEvent(new Event("Removed movie: " + this.getMovieByIdx(idx).getTitle()));
        this.removeMovieByIdx(idx);
    }

    // MODIFIES: this
    // EFFECTS: clears watch list
    public void clearWatchList() {
        this.clear();
        EventLog.getInstance().logEvent(new Event("Cleared watch list"));
    }

    // MODIFIES: this
    // EFFECTS: clears watch list and adds movies from the watch list passed as a parameter
    public void replaceWatchList(WatchList watchList) {
        this.clearWatchList();
        for (Movie movie : watchList) {
            this.addMovieToWatchList(movie);
        }
        EventLog.getInstance().logEvent(new Event("Replaced current watch list with other watch list"));
    }
}
