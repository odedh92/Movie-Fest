package nimrod.d.practice1.ui.movies;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nimrod.d.practice1.R;
import nimrod.d.practice1.models.Movie;

import nimrod.d.practice1.ui.SharedViewModel;
import nimrod.d.practice1.ui.home.HomeFragment;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.movieViewHolder>{



    SharedViewModel viewModel;
    private FragmentActivity fragmentActivity;
    private ArrayList<Movie> movies;
    Movie m;



    public MovieAdapter(ArrayList<Movie> movies, FragmentActivity fragmentActivity) {
        this.movies = movies;
        this.viewModel =  ViewModelProviders.of(fragmentActivity).get(SharedViewModel.class);
        this.fragmentActivity = fragmentActivity;
    }


    @NonNull
    @Override
    public movieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.movie_item,parent,false);

        return new movieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final movieViewHolder holder, int position) {

        m = movies.get(position);
        holder.tvTitle.setText(m.getTitle());
        holder.tvDate.setText(m.getRelease_date());
        holder.tvAverage.setText(m.getVote_average());
        holder.tvOverview.setText(m.getOverview());
        Picasso.get().load(m.getPosterPath()).into(holder.ivPoster);

            if (movies.get(position).getFavorite()) {
                holder.ivLike.setImageResource(R.drawable.ic_full_heart); //תמלא לב אם מועדף
            } else {
                holder.ivLike.setImageResource(R.drawable.ic_empty_heart);//תרוקן לב אם לא מועדף
            }


        holder.ivLike.setOnClickListener(v -> {

            if (movies.get(position).getFavorite() == true){ //אם הוא כבר מועדף
                System.out.println(m.getFavorite() + " if isFav");
                movies.get(position).setFavorite(false); //תהפוך ללא מועדף

            }else { //אם הוא לא מועדף
                System.out.println(m.getFavorite() + " else isNOTFav");
                movies.get(position).setFavorite(true); //תהפוך למועדף

                Toast.makeText(fragmentActivity, "הועבר לרשימת המועדפים שלך!", Toast.LENGTH_SHORT).show();
            }

            notifyItemChanged(position);
        });



    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    class movieViewHolder extends RecyclerView.ViewHolder{

        ImageView ivPoster;
        TextView tvTitle;
        TextView tvDate;
        TextView tvAverage;
        TextView tvOverview;
        ImageView ivLike;
        TextView tvScreens;
        TextView tvHours;


        public movieViewHolder(@NonNull View itemView) {
            super(itemView);


            ivPoster = itemView.findViewById(R.id.ivPoster);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvAverage = itemView.findViewById(R.id.tvAverage);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivLike = itemView.findViewById(R.id.ivLike);
            tvScreens = itemView.findViewById(R.id.tvScreens);
            tvHours = itemView.findViewById(R.id.tvHours);


        }
    }

}
