package model;


// Represents a movie list that can be searched
public class MovieSearchList extends MovieList {
    private static final int SEARCH_RESULT_LIMIT = 10;

    // EFFECTS: calls superclass constructor
    public MovieSearchList() {
        super();
    }

    // EFFECTS: returns all movies that contain searchTerm in their title up to SEARCH_RESULT_LIMIT
    public MovieList searchMovie(String searchTerm) {
        MovieList searchResults = new MovieList();
        for (Movie movie : this) {
            if (movie.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                searchResults.addMovie(movie);
                if (searchResults.getSize() == SEARCH_RESULT_LIMIT) {
                    break;
                }
            }
        }
        return searchResults;
    }
}
