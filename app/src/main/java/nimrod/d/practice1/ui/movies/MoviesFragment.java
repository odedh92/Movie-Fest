package nimrod.d.practice1.ui.movies;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import nimrod.d.practice1.R;
import nimrod.d.practice1.models.Movie;
import nimrod.d.practice1.ui.SharedViewModel;

public class MoviesFragment extends Fragment {

    private SharedViewModel sharedViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sharedViewModel =
                ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        View root = inflater.inflate(R.layout.fragment_movies, container, false);

        final RecyclerView rvMovies = root.findViewById(R.id.rvMovies);


        sharedViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {

                rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
                MovieAdapter adapter = new MovieAdapter(movies,getActivity());
                rvMovies.setAdapter(adapter);

                sharedViewModel.sharedMovies = new ArrayList<>(movies);

            }
        });


        return root;
    }

}

