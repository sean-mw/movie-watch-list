package persistence;

import model.MovieSearchList;
import model.WatchList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


// This class was adapted from JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JsonWriterTest {
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
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWatchList() {
        try {
            WatchList watchList = new WatchList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWatchList.json");
            writer.open();
            writer.writeWatchList(watchList);
            writer.close();

            JsonReader reader = new JsonReader();
            watchList = reader.readWatchList("./data/testWriterEmptyWatchList.json");
            assertEquals(0, watchList.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWatchList() {
        try {
            WatchList watchList = new WatchList();
            watchList.addMovieToWatchList(movieList.searchMovie("pulp fiction").getMovieByIdx(0));
            watchList.addMovieToWatchList(movieList.searchMovie("inception").getMovieByIdx(0));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWatchList.json");
            writer.open();
            writer.writeWatchList(watchList);
            writer.close();

            JsonReader reader = new JsonReader();
            watchList = reader.readWatchList("./data/testWriterGeneralWatchList.json");
            assertEquals(2, watchList.getSize());
            assertEquals(watchList.getMovieByIdx(0).getTitle(), "Pulp Fiction");
            assertEquals(watchList.getMovieByIdx(1).getTitle(), "Inception");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}