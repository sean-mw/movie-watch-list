package persistence;

import model.MovieSearchList;
import model.WatchList;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


// This class was adapted from JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader();
        try {
            WatchList watchList = reader.readWatchList("./data/noSuchFile.json");
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWatchList() {
        JsonReader reader = new JsonReader();
        try {
            WatchList watchList = reader.readWatchList("./data/testReaderEmptyWatchList.json");
            assertEquals(0, watchList.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWatchList() {
        JsonReader reader = new JsonReader();
        try {
            WatchList defaultWatchList = reader.readWatchList();
            WatchList sourceWatchList = reader.readWatchList("./data/testReaderGeneralWatchList.json");
            assertEquals(2, sourceWatchList.getSize());
            assertEquals(sourceWatchList.getMovieByIdx(0).getTitle(), "Star Wars");
            assertEquals(sourceWatchList.getMovieByIdx(1).getTitle(), "Inception");
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderMovieList() {
        JsonReader reader = new JsonReader();
        try {
            MovieSearchList defaultMovieSearchList = reader.readMovieSearchList();
            assertEquals(defaultMovieSearchList.searchMovie("inception").getMovieByIdx(0).getTitle(), "Inception");
            MovieSearchList sourceMovieList = reader.readMovieSearchList("./data/imdbTop1000.json");
            assertEquals(sourceMovieList.searchMovie("inception").getMovieByIdx(0).getTitle(), "Inception");
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}