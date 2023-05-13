package com.svalero.globalfeed.view;


import static com.svalero.globalfeed.util.Constants.DATABASE_NAME;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.globalfeed.R;
import com.svalero.globalfeed.adapter.PostAdapter;
import com.svalero.globalfeed.contract.post.PostListContract;
import com.svalero.globalfeed.contract.user.UserDetailsContract;
import com.svalero.globalfeed.db.FeedAppDatabase;
import com.svalero.globalfeed.domain.PersistData;
import com.svalero.globalfeed.domain.Post;
import com.svalero.globalfeed.domain.User;
import com.svalero.globalfeed.presenter.post.PostListPresenter;
import com.svalero.globalfeed.presenter.user.UserDetailsPresenter;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsView extends AppCompatActivity implements PostListContract.View {

    private List<Post> postList;
    private PostAdapter adapter;
    private PostListPresenter postListPresenter;
    private UserDetailsPresenter presenter;
    private User user;
    private TextView tvName;
    private TextView tvPhone;
    private TextView tvBio;
    private TextView tvUsername;
    private PersistData persistData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_view);
        Intent intentFrom = getIntent();
        final FeedAppDatabase db = Room.databaseBuilder(this, FeedAppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        persistData = new PersistData(0, "", "", "", false, false, false);
        try {
            persistData = db.getPersistDataDAO().getPersistData();
        } catch (SQLiteConstraintException sce) {
            Log.i("RegisterProductView", "onCreate - Error");
            sce.printStackTrace();
        } finally {
            db.close();
        }
        user = (User) intentFrom.getSerializableExtra("user");


        tvUsername = findViewById(R.id.registerTextUsername);
        tvName = findViewById(R.id.registerName);
        tvBio = findViewById(R.id.registerBio);
        tvPhone = findViewById(R.id.registerPhone);
        tvUsername.setText(user.getUsername());
        tvName.setText(user.getName());
        tvBio.setText(user.getBiography());
        tvPhone.setText(user.getPhoneNumber().toString());
        postListPresenter = new PostListPresenter(this);
        initializeRecyclerView(intentFrom);
    }


    private void initializeRecyclerView(Intent intentFrom) {
        postList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rvListPosts);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PostAdapter(this, postList, intentFrom, persistData.getToken());
        recyclerView.setAdapter(adapter);
    }




    @Override
    public void showPosts(List<Post> postList) {
        this.postList.clear();
        this.postList.addAll(postList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        postListPresenter.loadAllPostsByUser(user.getUsername());
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    public void returnNav(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}