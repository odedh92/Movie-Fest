package nimrod.d.practice1.models;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieDataSource {

    String address = "https://api.themoviedb.org/3/discover/movie?api_key=f8fe584ea5843ac43c630b78addadbd8&language=he-IL&sort_by=popularity.desc&include_adult=false&include_video=false&year=2020";
    String screen1 = "מסך 1";
    String screen2 = "מסך 2";
    String screen3 = "מסך 3";
    String screen4 = "מסך 4";
    String[] screens = new String[]{screen1, screen2, screen3, screen4};
    String hour1 = "16:00";
    String hour2 = "18:00";
    String hour3 = "20:00";
    String hour4 = "22:00";
    String hour5 = "00:00";
    String[] hours = new String[]{hour1, hour2, hour3, hour4, hour5};
    public void jsonToAndroid(final MutableLiveData<ArrayList<Movie>> callback){

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();


        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("NOT CONNECTED TO THE INTERNET");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {


                ArrayList<Movie> movies = new ArrayList<>();

                String json = response.body().string();

                try {

                    JSONObject rootObject = new JSONObject(json);
                    JSONArray moviesJsonArray = rootObject.getJSONArray("results");

                    for (int i = 0; i < screens.length; i++) {
                        for (int j = 0; j < hours.length; j++) {

                            JSONObject movieJsonObject = moviesJsonArray.getJSONObject(i*hours.length+j);
                            String title = movieJsonObject.getString("title");
                            String average = movieJsonObject.getString("vote_average");
                            String date = movieJsonObject.getString("release_date");
                            String poster = movieJsonObject.getString("poster_path");
                            String overview = movieJsonObject.getString("overview");


                            Movie m = new Movie(title, average, date, poster, overview, hours[j],screens[i] );
                            movies.add(m);
                        }
                    }

                    callback.postValue(movies);

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });


    }




}
