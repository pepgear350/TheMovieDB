package com.pepgear350.tmdb.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pepgear350.tmdb.R;
import com.pepgear350.tmdb.di.Injectable;
import com.pepgear350.tmdb.ui.details.DetailsActivity;
import com.pepgear350.tmdb.viewmodel.main.MainViewModel;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity implements Injectable,
        SearchView.OnQueryTextListener,
        BottomNavigationView.OnNavigationItemSelectedListener {


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private MainViewModel viewModel;
    private RecyclerViewAdapterMain adapter;
    private SearchView searchMoviesView;
    private int category = 1;
    private MenuItem searchMoviesMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        BottomNavigationView navigationView = findViewById(R.id.navigation_movies_main);
        navigationView.setOnNavigationItemSelectedListener(this);


        OnListenerRecyclerView mListener = (id) -> {
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("id_movie", id);
            startActivity(intent);
        };

        RecyclerView recyclerView = findViewById(R.id.list_movies_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapterMain(mListener, this);
        recyclerView.setAdapter(adapter);

        viewModel.getLiveDataError().observe(this, message ->{
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });

        observerMovies(category);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_view, menu);
        searchMoviesMenu = menu.findItem(R.id.search_movies);
        searchMoviesView = (SearchView) searchMoviesMenu.getActionView();
        searchMoviesView.setOnQueryTextListener(this);
        searchMoviesView.setQueryHint(getString(R.string.search_popular));
        return super.onCreateOptionsMenu(menu);
    }

    private void observerMovies(int category) {
        viewModel.getLiveDataMovies().removeObservers(Objects.requireNonNull(this));
        viewModel.getAllMovies(category).observe(this, list -> {
            adapter.setList(list);
        });
    }

    private void observerSearchMovies(String query, int category) {
        viewModel.getLiveDataMovies().removeObservers(Objects.requireNonNull(this));
        viewModel.getSearchMovies(query, category).observe(this, list -> {
            adapter.setList(list);
        });
    }

    private void observerSearchOnLine(String query) {
        viewModel.getLiveDataMovies().removeObservers(Objects.requireNonNull(this));
        viewModel.getSearchMoviesOnline(query).observe(this, list -> {
            adapter.setList(list);
        });
    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        if (category == 0) {
            observerSearchOnLine(query);
            return true;
        }

        if (!query.isEmpty()) {
            observerSearchMovies(query, category);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if (category == 0) {
            adapter.setList(new ArrayList<>());
            return true;
        }

        if (newText.isEmpty()) {
            observerMovies(category);
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.item_popular:
                category = 1;
                searchMoviesView.setQueryHint(getString(R.string.search_popular));
                observerMovies(category);
                break;

            case R.id.item_top_rated:
                category = 2;
                searchMoviesView.setQueryHint(getString(R.string.search_top_rated));
                observerMovies(category);
                break;

            case R.id.item_upcoming:
                category = 3;
                searchMoviesView.setQueryHint(getString(R.string.search_upcoming));
                observerMovies(category);
                break;

            case R.id.item_online:
                category = 0;
                searchMoviesView.setQueryHint(getString(R.string.search_online));
                searchMoviesMenu.expandActionView();
                adapter.setList(new ArrayList<>());
                break;
        }
        searchMoviesView.setQuery("", false);
        return true;
    }


    public interface OnListenerRecyclerView {

        void onClickMovie(int id);
    }
}
