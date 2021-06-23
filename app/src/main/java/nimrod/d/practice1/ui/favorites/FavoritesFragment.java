package nimrod.d.practice1.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import nimrod.d.practice1.R;
import nimrod.d.practice1.models.Movie;
import nimrod.d.practice1.ui.SharedViewModel;


public class FavoritesFragment extends Fragment {

    private SharedViewModel sharedViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sharedViewModel =
                ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        final RecyclerView rvFavMovies = root.findViewById(R.id.rvFavMovies);


        if (sharedViewModel.sharedMovies != null){
            rvFavMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
            sharedViewModel.sharedMovies.removeIf(movie -> !movie.getFavorite());
            FavMovieAdapter adapter = new FavMovieAdapter(sharedViewModel.sharedMovies,getActivity());
            rvFavMovies.setAdapter(adapter);
        }

        return root;
    }


}