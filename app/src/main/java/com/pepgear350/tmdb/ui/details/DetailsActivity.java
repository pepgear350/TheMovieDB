package com.pepgear350.tmdb.ui.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.snackbar.Snackbar;
import com.pepgear350.tmdb.R;
import com.pepgear350.tmdb.db.entity.MoviesEntity;
import com.pepgear350.tmdb.db.entity.VideoEntity;
import com.pepgear350.tmdb.di.Injectable;
import com.pepgear350.tmdb.ui.main.RecyclerViewAdapterMain;
import com.pepgear350.tmdb.viewmodel.details.DetailsViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class DetailsActivity extends AppCompatActivity implements Injectable {


    @Inject
    ViewModelProvider.Factory viewModelFactory;


    private DetailsViewModel viewModel;
    private TextView title;
    private TextView vote;
    private TextView overview;
    private TextView releaseDate;
    private ImageView poster;
    private WebView webview;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailsViewModel.class);
        int id_movie = 0;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id_movie = bundle.getInt("id_movie");
        }

        title = findViewById(R.id.title_details);
        vote = findViewById(R.id.vote_details);
        overview = findViewById(R.id.overview_details);
        releaseDate = findViewById(R.id.release_date_details);
        poster = findViewById(R.id.poster_details);
        webview = findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebChromeClient() {

        });

        RecyclerView recyclerView = findViewById(R.id.list_cast);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        RecyclerViewAdapterDetails adapter = new RecyclerViewAdapterDetails(this);
        recyclerView.setAdapter(adapter);

        viewModel.getLiveDataError().observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });

        viewModel.getLiveDataMovie(id_movie).observe(this, this::setDataMovie);

        viewModel.getLiveDataCredits(id_movie).observe(this, adapter::setList);


        viewModel.getLiveDataVideo(id_movie).observe(this, this::loadDataInWebView);

    }

    private void setDataMovie(MoviesEntity movie) {
        if (movie != null) {
            title.setText(movie.getTitle());
            vote.setText(String.valueOf(movie.getVote_average()));
            overview.setText(movie.getOverview());
            releaseDate.setText(movie.getRelease_date());

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(new ColorDrawable(ContextCompat.getColor(this, R.color.colorAccent)));
            requestOptions.error(R.drawable.ic_photo_black_24dp);
            requestOptions.centerCrop();

            Glide.with(this)
                    .setDefaultRequestOptions(requestOptions)
                    .load(movie.getPoster_path())
                    .into(poster);
        }
    }

    private void loadDataInWebView(List<VideoEntity> list) {
        if (list.size() > 0) {
            webview.loadData(
                    "<iframe width=\"100%\" height=\"100%\" src=\""+ list.get(0).getKey()+"\" frameborder=\"1\" allowfullscreen></iframe>",
                    "text/html",
                    "utf-8");
        }
    }

}
