package com.example.android.sunshine.adapters;

/**
 * Created by DNS-PC on 16.08.2016.
 */

    import android.content.Context;
    import android.support.v7.widget.RecyclerView;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.LinearLayout;
    import android.widget.TextView;
    import com.example.android.sunshine.models.Movie;
    import java.util.List;
    import com.example.android.sunshine.R;

    public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

        private List<Movie> movies;
        private int rowLayout;
        private Context context;
        private static  MovieClickListener movieClickListener;


        public static class MovieViewHolder extends RecyclerView.ViewHolder {
            LinearLayout moviesLayout;
            TextView movieTitle;
            TextView data;
            TextView movieDescription;
            TextView rating;


            public MovieViewHolder(View v) {
                super(v);
                moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);

               moviesLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ( movieClickListener!=null){
                            movieClickListener.onMovieClicked(getAdapterPosition());
                        }
                    }
                });



                movieTitle = (TextView) v.findViewById(R.id.title);
                data = (TextView) v.findViewById(R.id.subtitle);
                movieDescription = (TextView) v.findViewById(R.id.description);
                rating = (TextView) v.findViewById(R.id.rating);
            }
        }

        public MoviesAdapter(List<Movie> movies, int rowLayout, Context context, MovieClickListener listener) {
            this.movies = movies;
            this.rowLayout = rowLayout;
            this.context = context;
            this.movieClickListener = listener;
        }

        @Override
        public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
            return new MovieViewHolder(view);
        }


        @Override
        public void onBindViewHolder(MovieViewHolder holder, final int position) {
            holder.movieTitle.setText(movies.get(position).getTitle());
            holder.data.setText(movies.get(position).getReleaseDate());
            holder.movieDescription.setText(movies.get(position).getOverview());
            holder.rating.setText(movies.get(position).getVoteAverage().toString());
        }

        @Override
        public int getItemCount() {
            return movies.size();
        }

       public interface MovieClickListener {
            void onMovieClicked(int position);
        }
    }
