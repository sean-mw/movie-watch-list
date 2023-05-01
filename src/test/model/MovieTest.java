package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MovieTest {
    private MovieSearchList movieList;

    @BeforeAll
    void setup() {
        JsonReader jsonReader = new JsonReader();
        try {
            movieList = jsonReader.readMovieSearchList();
        } catch (IOException e) {
            fail("Unable to read movie list");
        }
    }

    @Test
    void testGetId() {
        Movie movie = movieList.searchMovie("inception").getMovieByIdx(0);
        assertEquals(movie.getId(), 8);
    }

    @Test
    void testGetTitle() {
        Movie movie = movieList.searchMovie("inception").getMovieByIdx(0);
        assertEquals(movie.getTitle(), "Inception");
    }

    @Test
    void testGetYear() {
        Movie movie = movieList.searchMovie("inception").getMovieByIdx(0);
        assertEquals(movie.getYear(), 2010);
    }

    @Test
    void testGetRuntime() {
        Movie movie = movieList.searchMovie("inception").getMovieByIdx(0);
        assertEquals(movie.getRuntime(), 148);
    }

    @Test
    void testGetGenre() {
        Movie movie = movieList.searchMovie("inception").getMovieByIdx(0);
        assertEquals(movie.getGenre(), "Action, Adventure, Sci-Fi");
    }

    @Test
    void testGetImdbRating() {
        Movie movie = movieList.searchMovie("inception").getMovieByIdx(0);
        float actualRating = (float) 8.8;
        assertEquals(movie.getImdbRating(), actualRating);
    }

    @Test
    void testGetOverview() {
        Movie movie = movieList.searchMovie("inception").getMovieByIdx(0);
        assertEquals(movie.getOverview(), "A thief who steals corporate secrets through the use of dream-sharing"
                     + " technology is given the inverse task of planting an idea into the mind of a C.E.O.");
    }

    @Test
    void testGetDirector() {
        Movie movie = movieList.searchMovie("inception").getMovieByIdx(0);
        assertEquals(movie.getDirector(), "Christopher Nolan");
    }

    @Test
    void testGetStars() {
        Movie movie = movieList.searchMovie("inception").getMovieByIdx(0);
        ArrayList<String> stars = new ArrayList<>();
        stars.add("Leonardo DiCaprio");
        stars.add("Joseph Gordon-Levitt");
        stars.add("Elliot Page");
        stars.add("Ken Watanabe");
        assertEquals(movie.getStars(), stars);
    }

    @Test
    void testGetImdbVoteCount() {
        Movie movie = movieList.searchMovie("inception").getMovieByIdx(0);
        assertEquals(movie.getImdbVoteCount(), 2067042);
    }

    @Test
    void testGetUserRating() {
        Movie movie = movieList.searchMovie("inception").getMovieByIdx(0);
        movie.setUserRating(8);
        assertEquals(movie.getUserRating(), 8);
    }

    @Test
    void testSetUserRating() {
        Movie movie = movieList.searchMovie("inception").getMovieByIdx(0);
        movie.setUserRating(8);
        assertEquals(movie.getUserRating(), 8);
    }

    @Test
    void testToString() {
       Movie movie = movieList.searchMovie("inception").getMovieByIdx(0);
       String movieString = movie.toString();
       String expectedString = movie.getTitle() + " [Rating: " + movie.getUserRating() + "]";
       assertEquals(expectedString, movieString);
    }

    @Test
    void testToStringFull() {
        Movie movie = movieList.searchMovie("inception").getMovieByIdx(0);
        String movieString = movie.toStringFull();
        assertTrue(movieString.contains(movie.getTitle()));
        assertTrue(movieString.contains(String.valueOf(movie.getYear())));
        for (String star : movie.getStars()) {
            assertTrue(movieString.contains(star));
        }
        assertTrue(movieString.contains(movie.getOverview()));
    }

    @Test
    void testToJson() {
        Movie movie = movieList.searchMovie("inception").getMovieByIdx(0);
        JSONObject object = movie.toJson();
        String title = object.getString("title");
        String overview = object.getString("overview");
        assertEquals(movie.getTitle(), title);
        assertEquals(movie.getOverview(), overview);
    }
}