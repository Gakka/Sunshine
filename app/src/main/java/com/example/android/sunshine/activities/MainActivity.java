package com.example.android.sunshine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.example.android.sunshine.models.MoviesResponse;
import com.example.android.sunshine.models.Movie;
import com.example.android.sunshine.rest.ApiInterface;
import com.example.android.sunshine.rest.ApiClient;
import com.example.android.sunshine.R;
import com.example.android.sunshine.adapters.MoviesAdapter;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final static String API_KEY = "35b03473a8df50fe6679b936e5e7a80f";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                final List<Movie> movies = response.body().getResults();

                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext(),
                        new MoviesAdapter.MovieClickListener() {
                    @Override
                    public void onMovieClicked(int position) {
                        Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                        intent.putExtra(DetailsActivity.EXTRA_KEY_1,movies.get(position).getOverview());
                        intent.putExtra(DetailsActivity.EXTRA_KEY_2,movies.get(position).getOriginalTitle());
                        intent.putExtra(DetailsActivity.EXTRA_KEY_3,movies.get(position).getPosterPath());
                                // parcelable android

                        startActivity(intent);
                    }
                }));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }

        });
    }


}
