package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


// Represents a list of movies
public class MovieList implements Iterable<Movie> {
    private final ArrayList<Movie> movies;

    // Effects: movies is set to empty list
    public MovieList() {
        this.movies = new ArrayList<>();
    }

    public ArrayList<Movie> getMovies() {
        return this.movies;
    }

    public int getSize() {
        return this.movies.size();
    }

    public Movie getMovieByIdx(int idx) {
        return this.movies.get(idx);
    }

    // MODIFIES: this
    // EFFECTS: clears movies
    public void clear() {
        this.movies.clear();
    }

    // MODIFIES: this
    // EFFECTS: movie is added to movies
    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    // MODIFIES: this
    // EFFECTS: movie is added to movies
    public void removeMovieByIdx(int idx) {
        this.movies.remove(idx);
    }

    // EFFECTS: returns a json object representation of watch list
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("movies", moviesToJson());
        return json;
    }

    // EFFECTS: returns a json array representation of watch list
    public JSONArray moviesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Movie movie : this.movies) {
            jsonArray.put(movie.toJson());
        }
        return jsonArray;
    }

    @Override
    // EFFECTS: returns an iterator
    public Iterator<Movie> iterator() {
        return this.movies.iterator();
    }
}
