package com.svalero.globalfeed.view;

import static com.svalero.globalfeed.util.Constants.DATABASE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.globalfeed.R;
import com.svalero.globalfeed.contract.post.EditPostContract;
import com.svalero.globalfeed.contract.post.RegisterPostContract;
import com.svalero.globalfeed.db.FeedAppDatabase;
import com.svalero.globalfeed.domain.PersistData;
import com.svalero.globalfeed.domain.Post;
import com.svalero.globalfeed.presenter.post.EditPostPresenter;
import com.svalero.globalfeed.presenter.post.RegisterPostPresenter;

import java.time.LocalDateTime;

public class AddPostView extends AppCompatActivity  implements RegisterPostContract.View, EditPostContract.View{

    private EditText etMessage;
    private RegisterPostPresenter registerPostPresenter;
    private EditPostPresenter editPostPresenter;
    private PersistData persistData;
    private Button button;
    String username;
    String userId;
    Post postEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post_view);

        final FeedAppDatabase db = Room.databaseBuilder(this, FeedAppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        persistData = new PersistData(0, "", "", "", false, false, false);
        try {
            persistData = db.getPersistDataDAO().getPersistData();
        } catch (SQLiteConstraintException sce) {
            Log.i("RegisterPostView", "onCreate - Error");
            sce.printStackTrace();
        } finally {
            db.close();
        }

        etMessage = findViewById(R.id.registerPostMessage);


        Intent intentFrom = getIntent();
        username = intentFrom.getStringExtra("username");
        userId = intentFrom.getStringExtra("userId");
        postEdit = (Post) intentFrom.getSerializableExtra("editPost");
        Log.i("RegisterPostView", "onCreate - Intent Username: " + username);
        Log.i("RegisterPostView", "onCreate - Intent Post: " + postEdit);

        if (postEdit != null) {
            etMessage.setText(postEdit.getMessage());
            button.setText(R.string.editButton);
        }
        registerPostPresenter = new RegisterPostPresenter(this);
        editPostPresenter = new EditPostPresenter(this);
    }


    @Override
    public void showError(String error) {
        Snackbar.make(((EditText) findViewById(R.id.registerPostMessage)), error,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(((EditText) findViewById(R.id.registerPostMessage)), message,
                BaseTransientBottomBar.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void register(View view) {
        String message = etMessage.getText().toString();
        String postDate= LocalDateTime.now().toString();

        if (postEdit != null) {
            Post post = new Post(postEdit.getId(), message, postDate, 0, postEdit.getUserPost());
            editPostPresenter.editPost(post, persistData.getToken());
        } else {
            Post post = new Post(message, postDate, 0, Integer.parseInt(userId));
            registerPostPresenter.registerPost(post, persistData.getToken());
        }
    }
}