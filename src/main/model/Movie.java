package model;

import org.json.JSONObject;

import java.util.ArrayList;

// Represents a movie
public class Movie {
    private final String title;
    private final int year;
    private final int runtime;
    private final String genre;
    private final float imdbRating;
    private final String overview;
    private final String director;
    private final ArrayList<String> stars;
    private final int imdbVoteCount;
    private int userRating;
    private final int id;

    // EFFECTS: sets movie fields to parameter values passed
    public Movie(String title, int year, int runtime, String genre, float imdbRating, String overview,
                 String director, ArrayList<String> stars, int imdbVoteCount, int userRating, int id) {
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.genre = genre;
        this.imdbRating = imdbRating;
        this.overview = overview;
        this.director = director;
        this.stars = stars;
        this.imdbVoteCount = imdbVoteCount;
        this.userRating = userRating;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public float getImdbRating() {
        return imdbRating;
    }

    public String getOverview() {
        return overview;
    }

    public String getDirector() {
        return director;
    }

    public ArrayList<String> getStars() {
        return stars;
    }

    public int getImdbVoteCount() {
        return imdbVoteCount;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    @Override
    // EFFECTS: returns a string representation of movie with title and userRating
    public String toString() {
        return title + " [Rating: " + userRating + "]";
    }

    // EFFECTS: returns a string representation of movie with all information
    public String toStringFull() {
        StringBuilder starsBuilder = new StringBuilder();
        int i = 0;
        for (String star : stars) {
            i++;
            starsBuilder.append(star);
            if (i != stars.size()) {
                starsBuilder.append(", ");
            }
        }
        String starsString = starsBuilder.toString();
        return String.format("%s [%d - %d minutes]\nGenre: %s\nDirector: %s\nStars: %s\nIMDb Rating: %f\n"
                             + "IMDb Vote Count: %d\nOverview: %s\nUser Rating: %d\nID: %d", title, year, runtime,
                             genre, director, starsString, imdbRating, imdbVoteCount, overview, userRating, id);
    }

    // EFFECTS: returns a json object representation of movie
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("year", year);
        json.put("runtime", runtime);
        json.put("genre", genre);
        json.put("imdbRating", imdbRating);
        json.put("overview", overview);
        json.put("director", director);
        int i = 1;
        for (String star : stars) {
            json.put("star" + i, star);
            i++;
        }
        json.put("imdbVoteCount", imdbVoteCount);
        json.put("userRating", userRating);
        json.put("id", id);
        return json;
    }
}