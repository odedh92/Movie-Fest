package nimrod.d.practice1.ui.home;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import nimrod.d.practice1.R;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;

    String wineURI = "http://www.dadah.co.il/";
    String foodURI = "http://www.pizzalinga.com/";
    String storeURI ="http://www.third-ear.com/";
    String twdaURI = "https://togetherwedancealone.com/";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        root.findViewById(R.id.ivWine).setOnClickListener(v -> {
            myIntent(wineURI);
        });

        root.findViewById(R.id.ivFood).setOnClickListener(v -> {
            myIntent(foodURI);
        });

        root.findViewById(R.id.ivStore).setOnClickListener(v -> {
            myIntent(storeURI);
        });
        root.findViewById(R.id.ivtwda).setOnClickListener(v -> {
            myIntent(twdaURI);
        });

        return root;
    }

    public void myIntent(String uri){
        try {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), "No application can handle this request."
                    + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}