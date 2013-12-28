package app.philm.in.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.List;

import app.philm.in.R;
import app.philm.in.adapters.MovieGridAdapter;
import app.philm.in.controllers.MovieController;
import app.philm.in.fragments.base.PhilmMovieListFragment;
import app.philm.in.model.PhilmMovie;

public class MovieGridFragment extends PhilmMovieListFragment<GridView> {

    private static final String KEY_QUERY_TYPE = "query_type";

    private MovieGridAdapter mMovieGridAdapter;

    public static MovieGridFragment create(MovieController.MovieQueryType type) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_QUERY_TYPE, type.ordinal());

        MovieGridFragment fragment = new MovieGridFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMovieGridAdapter = new MovieGridAdapter(getActivity());
        setListAdapter(mMovieGridAdapter);
    }

    @Override
    public void onListItemClick(GridView l, View v, int position, long id) {
        if (hasCallbacks()) {
            PhilmMovie movie = (PhilmMovie) l.getItemAtPosition(position);
            getCallbacks().showMovieDetail(movie);
        }
    }

    @Override
    public void setItems(List<PhilmMovie> items) {
        mMovieGridAdapter.setItems(items);
    }

    @Override
    public void setItemsWithSections(List<PhilmMovie> items,
            List<MovieController.Filter> sections) {
        mMovieGridAdapter.setItems(items);
    }

    @Override
    public MovieController.MovieQueryType getMovieQueryType() {
        final int queryType = getArguments().getInt(KEY_QUERY_TYPE);
        return MovieController.MovieQueryType.values()[queryType];
    }

    @Override
    public String getRequestParameter() {
        return null;
    }

    @Override
    protected GridView createListView(Context context) {
        GridView gridView = new GridView(context);
        Resources res = getResources();

        gridView.setNumColumns(GridView.AUTO_FIT);
        gridView.setColumnWidth(res.getDimensionPixelSize(R.dimen.movie_grid_item_width));
        gridView.setHorizontalSpacing(res.getDimensionPixelSize(R.dimen.movie_grid_spacing));
        gridView.setVerticalSpacing(res.getDimensionPixelSize(R.dimen.movie_grid_spacing));
        gridView.setFastScrollEnabled(true);

        return gridView;
    }

}