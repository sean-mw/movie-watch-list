package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WatchListTest {
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
    void testSetMovieUserRating() {
        WatchList watchList = new WatchList();
        Movie movie = movieSearchList.searchMovie("inception").getMovieByIdx(0);
        watchList.addMovieToWatchList(movie);
        watchList.setMovieUserRating(0, 5);
        assertEquals(watchList.getMovieByIdx(0).getUserRating(), 5);
    }

    @Test
    void testAddMovieToWatchList() {
        WatchList watchList = new WatchList();
        Movie movie = movieSearchList.searchMovie("inception").getMovieByIdx(0);
        watchList.addMovieToWatchList(movie);
        assertEquals(watchList.getMovieByIdx(0), movie);
    }

    @Test
    void testRemoveMovieFromWatchListByIdx() {
        WatchList watchList = new WatchList();
        Movie movie = movieSearchList.searchMovie("inception").getMovieByIdx(0);
        watchList.addMovieToWatchList(movie);
        watchList.removeMovieFromWatchListByIdx(0);
        assertEquals(watchList.getSize(), 0);
    }

    @Test
    void testClearWatchList() {
        WatchList watchList = new WatchList();
        Movie movie = movieSearchList.searchMovie("inception").getMovieByIdx(0);
        watchList.addMovieToWatchList(movie);
        assertEquals(watchList.getSize(), 1);
        watchList.clearWatchList();
        assertEquals(watchList.getSize(), 0);
    }

    @Test
    void testReplaceWatchList() {
        WatchList watchList1 = new WatchList();
        WatchList watchList2 = new WatchList();
        Movie movie = movieSearchList.searchMovie("inception").getMovieByIdx(0);
        watchList1.addMovieToWatchList(movie);
        assertNotEquals(watchList1.getSize(), watchList2.getSize());
        watchList2.replaceWatchList(watchList1);
        assertEquals(watchList1.getSize(), watchList2.getSize());
    }
}