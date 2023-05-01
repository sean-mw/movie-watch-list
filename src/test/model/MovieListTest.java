package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MovieListTest {
    private MovieSearchList movieSearchList;

    @BeforeAll
    void setup() {
        JsonReader jsonReader = new JsonReader();
        try {
            movieSearchList = jsonReader.readMovieSearchList();
        } catch (IOException e) {
            fail("Unable to read movie list");
        }
    }

    @Test
    void testGetMovies() {
        MovieList movieList = new MovieList();
        Movie movie = movieSearchList.searchMovie("inception").getMovieByIdx(0);
        movieList.addMovie(movie);
        ArrayList<Movie> testList = new ArrayList<>();
        testList.add(movie);
        assertEquals(movieList.getMovies(), testList);
    }

    @Test
    void testGetSize() {
        MovieList movieList = new MovieList();
        Movie movie = movieSearchList.searchMovie("inception").getMovieByIdx(0);
        movieList.addMovie(movie);
        assertEquals(movieList.getSize(), 1);
        movieList.addMovie(movie);
        movieList.addMovie(movie);
        assertEquals(movieList.getSize(), 3);
    }

    @Test
    void testGetMovieByIdx() {
        MovieList movieList = new MovieList();
        Movie movie = movieSearchList.searchMovie("inception").getMovieByIdx(0);
        movieList.addMovie(movie);
        assertEquals(movieList.getMovieByIdx(0), movie);
    }

    @Test
    public void testClear() {
        MovieList movieList = new MovieList();
        Movie movie = movieSearchList.searchMovie("inception").getMovieByIdx(0);
        movieList.addMovie(movie);
        assertEquals(movieList.getSize(), 1);
        movieList.clear();
        assertEquals(movieList.getSize(), 0);
    }

    @Test
    void testAddMovie() {
        MovieList movieList = new MovieList();
        Movie movie = movieSearchList.searchMovie("inception").getMovieByIdx(0);
        movieList.addMovie(movie);
        assertEquals(movieList.getMovieByIdx(0), movie);
    }

    @Test
    void testRemoveMovieByIdx() {
        MovieList movieList = new MovieList();
        Movie movie = movieSearchList.searchMovie("inception").getMovieByIdx(0);
        movieList.addMovie(movie);
        movieList.removeMovieByIdx(0);
        assertEquals(movieList.getSize(), 0);
    }

    @Test
    void testToJson() {
        Movie movie = movieSearchList.searchMovie("inception").getMovieByIdx(0);
        MovieList movieList = new MovieList();
        movieList.addMovie(movie);
        JSONObject watchListObject = movieList.toJson();
        JSONArray moviesArray = watchListObject.getJSONArray("movies");
        JSONObject movieObject = (JSONObject) moviesArray.get(0);
        String title = movieObject.getString("title");
        String overview = movieObject.getString("overview");
        assertEquals(movie.getTitle(), title);
        assertEquals(movie.getOverview(), overview);
    }

    @Test
    void testIterator() {
        MovieList movieList = new MovieList();
        Movie movie = movieSearchList.searchMovie("inception").getMovieByIdx(0);
        movieList.addMovie(movie);
        for (Movie m : movieList) {
            assertEquals(m.getTitle(), movie.getTitle());
        }
    }
}
