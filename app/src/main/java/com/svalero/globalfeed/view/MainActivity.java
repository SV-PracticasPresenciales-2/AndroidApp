package com.svalero.globalfeed.view;

import static com.svalero.globalfeed.util.Constants.DATABASE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.svalero.globalfeed.R;
import com.svalero.globalfeed.adapter.PostAdapter;
import com.svalero.globalfeed.contract.post.PostListContract;
import com.svalero.globalfeed.db.FeedAppDatabase;
import com.svalero.globalfeed.domain.PersistData;
import com.svalero.globalfeed.domain.Post;
import com.svalero.globalfeed.presenter.post.PostListPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity implements PostListContract.View {
    private List<Post> postList;
    private List<Post> postListFull;
    private PostAdapter adapter;
    private PostListPresenter presenter;
    private PersistData persistData;
    private EditText etsearch;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkLocationPermission();

        Intent intentFrom = getIntent();
        username = intentFrom.getStringExtra("username");
        Log.i("MainActivity", "onCreate - " + username);

        final FeedAppDatabase db = Room.databaseBuilder(this, FeedAppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        setUpPreferences(db);


        presenter = new PostListPresenter(this);
        etsearch = findViewById(R.id.etSearch);
        etsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                postList.clear();
                Log.i("clear postList", String.valueOf(postList.size()));
                postList.addAll(postListFull);
                Log.i("postList", String.valueOf(postList.size()));
                Log.i("postListfull", String.valueOf(postListFull.size()));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        etsearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(etsearch.getWindowToken(), 0);
                    etsearch.setText("");
                    return true;
                } else {
                    return false;
                }
            }
        });

        initializeRecyclerView(intentFrom);
    }

    private void setUpPreferences(FeedAppDatabase db) {
        try {
            persistData = db.getPersistDataDAO().getPersistData();
            if (persistData != null) {
                Log.i("MainActivity", "onCreate - Datos cargados!");
                //TODO: Autologin
            } else {
                persistData = new PersistData(0, "", "", "", false, false, false);
                db.getPersistDataDAO().insert(persistData);
                Log.i("MainActivity", "onCreate - Datos creados!");
            }
        } catch (SQLiteConstraintException sce) {
            Log.i("MainActivity", "onCreate - Error");
        } finally {
            db.close();
        }
    }

    //TODO
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        getMenuInflater().inflate(R.menu.user_menu, menu);
        if (username != null) {
            menu.findItem(R.id.userMenu).setVisible(true);
            menu.findItem(R.id.userMenu).setTitle(username);
            menu.findItem(R.id.menuLogin).setVisible(false);
            menu.findItem(R.id.menuLogout).setVisible(true);
            menu.findItem(R.id.menuAddPost).setVisible(true);
            menu.findItem(R.id.menuFavourite).setVisible(true);
            menu.findItem(R.id.menuPreferences).setVisible(true);

        } else {
            menu.findItem(R.id.menuLogout).setVisible(false);
            menu.findItem(R.id.menuAddPost).setVisible(false);
            menu.findItem(R.id.menuFavourite).setVisible(false);
            menu.findItem(R.id.menuPreferences).setVisible(false);


        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       /* if (item.getItemId() == R.id.menuLogin) {
            Intent intent = new Intent(this, LoginView.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.menuAddPost) {
            Intent intent = new Intent(this, RegisterPostView.class);
            intent.putExtra("username", username);
            startActivity(intent);

        } else if (item.getItemId() == R.id.menuPreferences) {
            Intent intent = new Intent(this, Preferences.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }*/
        return false;
    }


    private void initializeRecyclerView(Intent intentFrom) {
        postList = new ArrayList<>();
        postListFull = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rvListPosts);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PostAdapter(this, postList, intentFrom, persistData.getToken());
        recyclerView.setAdapter(adapter);
    }

    protected void onResume() {
        super.onResume();
        presenter.loadAllPosts();
    }

    @Override
    public void showPosts(List<Post> postList) {
        this.postList.clear();
        this.postList.addAll(postList);
        this.postListFull.clear();
        this.postListFull.addAll(postList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String name) {
        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    }

    private void checkLocationPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
            }
        }

    }
}