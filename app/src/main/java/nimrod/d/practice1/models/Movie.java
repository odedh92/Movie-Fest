package nimrod.d.practice1.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

public class Movie {
    private String title;
    private String vote_average;
    private String release_date;
    private String poster_path;
    private String overview;
    private String hour;
    private String screen;
    private Boolean isFavorite;


    public Movie(String title, String vote_average, String release_date, String poster_path, String overview, String hour, String screen) {
        this.title = title;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.overview = overview;
        this.isFavorite = false;
        this.hour = hour;
        this.screen = screen;

    }
    public String getHour() {
        return hour;
    }

    public String getScreen() {
        return screen;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public String getTitle() {
        return title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOverview() {
        return overview;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", vote_average='" + vote_average + '\'' +
                ", release_date='" + release_date + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }

    public  String getPosterPath(){
        return "https://image.tmdb.org/t/p/w500"+poster_path;
    }

    public  void changeFavorite(){
        if (isFavorite == true){
            isFavorite = false;
        }else {
            isFavorite = true;
        }
    }

}
