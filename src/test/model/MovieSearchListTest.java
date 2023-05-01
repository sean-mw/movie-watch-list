package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MovieSearchListTest {
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
    void testSearchMovie() {
        Movie movie = movieSearchList.searchMovie("inception").getMovieByIdx(0);
        assertEquals(movie.getTitle(), "Inception");
        assertEquals(movie.getDirector(), "Christopher Nolan");
        MovieList searchResults = movieSearchList.searchMovie("i");
        assertEquals(searchResults.getSize(), 10);
    }
}
