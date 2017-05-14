package id.sch.smktelkom_mlg.privateassignment.xirpl335.privateassigment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl335.privateassigment.adapter.movieadapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl335.privateassigment.model.Movie;
import id.sch.smktelkom_mlg.privateassignment.xirpl335.privateassigment.model.MovieRespone;
import id.sch.smktelkom_mlg.privateassignment.xirpl335.privateassigment.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl335.privateassigment.service.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayingFragment extends Fragment {

    ArrayList<Movie> mlist = new ArrayList<>();
    movieadapter movieadapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playing, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        movieadapter = new movieadapter(this, mlist, getContext());
        recyclerView.setAdapter(movieadapter);

        LinearLayoutManager ly = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(ly);

        downloadDataSource();
        return view;
    }

    private void downloadDataSource() {
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=22b7ccb05a762e98b4d7fbfb12459508&language=en-US&page=1";
        GsonGetRequest<MovieRespone> myRequest = new GsonGetRequest<MovieRespone>
                (url, MovieRespone.class, null, new Response.Listener<MovieRespone>() {
                    @Override
                    public void onResponse(MovieRespone response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        mlist.addAll(response.results);
                        movieadapter.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(myRequest);
    }
}
