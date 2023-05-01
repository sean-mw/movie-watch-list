package persistence;

import model.MovieSearchList;
import model.WatchList;
import model.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;


// Represents a reader that reads from JSON data stored in file
// This class was adapted from JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private static final String WATCH_LIST_PATH = "./data/watchList.json";
    private static final String MOVIE_SEARCH_LIST_PATH = "./data/imdbTop1000.json";

    // EFFECTS: reads watch list from default file and returns it;
    // throws IOException if an error occurs reading data from file
    public WatchList readWatchList() throws IOException {
        String jsonData = readFile(WATCH_LIST_PATH);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWatchList(jsonObject);
    }

    // EFFECTS: reads watch list from source file and returns it;
    // throws IOException if an error occurs reading data from file
    public WatchList readWatchList(String source) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWatchList(jsonObject);
    }

    // EFFECTS: reads movie list from default file and returns it;
    // throws IOException if an error occurs reading data from file
    public MovieSearchList readMovieSearchList() throws IOException {
        String jsonData = readFile(MOVIE_SEARCH_LIST_PATH);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMovieSearchList(jsonObject);
    }

    // EFFECTS: reads movie list from source file and returns it;
    // throws IOException if an error occurs reading data from file
    public MovieSearchList readMovieSearchList(String source) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMovieSearchList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }


    // EFFECTS: parses watch list from JSON object and returns it
    private WatchList parseWatchList(JSONObject jsonObject) {
        ArrayList<Movie> movies = new ArrayList<>();
        addMovies(movies, jsonObject);
        WatchList watchList = new WatchList();
        for (Movie movie : movies) {
            watchList.addMovieToWatchList(movie);
        }
        return watchList;
    }

    // EFFECTS: parses movie list from JSON object and returns it
    private MovieSearchList parseMovieSearchList(JSONObject jsonObject) {
        ArrayList<Movie> movies = new ArrayList<>();
        addMovies(movies, jsonObject);
        MovieSearchList movieSearchList = new MovieSearchList();
        for (Movie movie : movies) {
            movieSearchList.addMovie(movie);
        }
        return movieSearchList;
    }

    // MODIFIES: movies
    // EFFECTS: parses movies from JSON object and adds them to movies
    private void addMovies(ArrayList<Movie> movies, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("movies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(movies, nextMovie);
        }
    }

    // MODIFIES: movies
    // EFFECTS: parses movie from JSON object and adds it to movies
    private void addMovie(ArrayList<Movie> movies, JSONObject jsonObject) {
        int userRating;
        String title = jsonObject.getString("title");
        int year = jsonObject.getInt("year");
        int runtime = jsonObject.getInt("runtime");
        String genre = jsonObject.getString("genre");
        float imdbRating = jsonObject.getFloat("imdbRating");
        String overview = jsonObject.getString("overview");
        String director = jsonObject.getString("director");
        ArrayList<String> stars = new ArrayList<>();
        stars.add(jsonObject.getString("star1"));
        stars.add(jsonObject.getString("star2"));
        stars.add(jsonObject.getString("star3"));
        stars.add(jsonObject.getString("star4"));
        int imdbVoteCount = jsonObject.getInt("imdbVoteCount");
        int id = jsonObject.getInt("id");
        if (jsonObject.has("userRating")) {
            userRating = jsonObject.getInt("userRating");
        } else {
            userRating = -1;
        }
        Movie movie = new Movie(title, year, runtime, genre, imdbRating, overview, director, stars, imdbVoteCount,
                                userRating, id);
        movies.add(movie);
    }
}
