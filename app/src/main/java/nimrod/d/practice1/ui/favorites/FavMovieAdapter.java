package nimrod.d.practice1.ui.favorites;

import android.graphics.Color;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nimrod.d.practice1.R;
import nimrod.d.practice1.models.Movie;
import nimrod.d.practice1.ui.SharedViewModel;

public class FavMovieAdapter extends RecyclerView.Adapter<FavMovieAdapter.favMovieViewHolder> {

    private ArrayList<Movie> favMovies;
    private FragmentActivity fragmentActivity;
    SharedViewModel viewModel;

    public FavMovieAdapter(ArrayList<Movie> favMovies, FragmentActivity fragmentActivity) {
        this.favMovies = favMovies;
        this.fragmentActivity = fragmentActivity;
        this.viewModel =  ViewModelProviders.of(fragmentActivity).get(SharedViewModel.class);
    }

    @NonNull
    @Override
    public favMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.fav_item,parent,false);


        return new favMovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull favMovieViewHolder holder, int position) {

        Movie fm = favMovies.get(position);

        holder.tvFavTitle.setText(fm.getTitle());
        Picasso.get().load(fm.getPosterPath()).into(holder.ivFavPoster);
        holder.tvScreens.setText(fm.getScreen());
        holder.tvHours.setText(fm.getHour());

        holder.ivDelete.setOnClickListener(v -> {

            Toast.makeText(fragmentActivity, "הוסר מרשימת המועדפים שלך", Toast.LENGTH_SHORT).show();
            favMovies.get(position).setFavorite(false);
            favMovies.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,getItemCount());

        });


        if (fm.getScreen().equals("מסך 2")){
            holder.tvScreens.setTextColor(Color.rgb(141,0,141));
            holder.tvHours.setTextColor(Color.rgb(141,0,141));
            holder.ivCircle.setColorFilter(Color.rgb(141,0,141));

        }if (fm.getScreen().equals("מסך 3")){
            holder.tvScreens.setTextColor(Color.rgb(0,141,0));
            holder.tvHours.setTextColor(Color.rgb(0,141,0));
            holder.ivCircle.setColorFilter(Color.rgb(0,141,0));

        }if (fm.getScreen().equals("מסך 4")){
            holder.tvScreens.setTextColor(Color.rgb(0,141,141));
            holder.tvHours.setTextColor(Color.rgb(0,141,141));
            holder.ivCircle.setColorFilter(Color.rgb(0 ,141,141));;
        }

    }

    @Override
    public int getItemCount() {
        return favMovies.size();
    }

    class  favMovieViewHolder extends RecyclerView.ViewHolder{
        ImageView ivFavPoster;
        TextView tvFavTitle;
        TextView tvScreens;
        TextView tvHours;
        ImageView ivDelete;
        ImageView ivCircle;
        ImageView ivLike;

        public favMovieViewHolder(@NonNull View itemView) {
            super(itemView);

            ivFavPoster = itemView.findViewById(R.id.ivFavPoster);
            tvFavTitle = itemView.findViewById(R.id.tvFavTitle);
            tvScreens = itemView.findViewById(R.id.tvScreens);
            tvHours = itemView.findViewById(R.id.tvHours);
            ivDelete = itemView.findViewById(R.id.ivDelete);
            ivCircle = itemView.findViewById(R.id.ivCircle);
            ivLike =itemView.findViewById(R.id.ivLike);

        }
    }
}
