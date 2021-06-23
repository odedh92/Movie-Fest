package nimrod.d.practice1.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import nimrod.d.practice1.models.Movie;
import nimrod.d.practice1.models.MovieDataSource;

import nimrod.d.practice1.ui.movies.MoviesFragment;

public class SharedViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Movie>> movies;
    public ArrayList<Movie> sharedMovies = null;
    boolean isDeleted;
    boolean isFav;

    public SharedViewModel() {

        this.isDeleted = false;
        this.isFav = false;
        movies = new MutableLiveData<>();

            MovieDataSource ds = new MovieDataSource();
            ds.jsonToAndroid(movies);

    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return movies;
    }
}