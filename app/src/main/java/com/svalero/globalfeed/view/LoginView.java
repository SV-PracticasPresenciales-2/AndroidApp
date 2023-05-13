package com.svalero.globalfeed.view;

import static com.svalero.globalfeed.util.Constants.DATABASE_NAME;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.globalfeed.R;
import com.svalero.globalfeed.contract.token.TokenContract;
import com.svalero.globalfeed.db.FeedAppDatabase;
import com.svalero.globalfeed.domain.PersistData;
import com.svalero.globalfeed.domain.PersonLogin;
import com.svalero.globalfeed.domain.Token;
import com.svalero.globalfeed.presenter.token.TokenPresenter;

public class LoginView extends AppCompatActivity implements TokenContract.View {

    private PersistData persistData;
    private EditText etUsernmae;
    private EditText etPassword;
    private TokenPresenter presenter;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        etUsernmae = findViewById(R.id.etLoginUsername);
        etPassword = findViewById(R.id.etLoginPassword);
        loginButton = findViewById(R.id.button);

        presenter = new TokenPresenter(this);

        final FeedAppDatabase db = Room.databaseBuilder(this, FeedAppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        persistData = new PersistData(0, "", "", "", false, false, false);
        try {
            persistData = db.getPersistDataDAO().getPersistData();
            if (persistData.isRememberMe()) {
                etUsernmae.setText(persistData.getUsername());
                etPassword.setText(persistData.getPassword());
                login(loginButton);
            }
        } catch (SQLiteConstraintException sce) {
            Log.i("InventoryListView", "onCreate - Error");
            sce.printStackTrace();
        } finally {
            db.close();
        }

    }

    @Override
    public void showError(String ErrorMessage) {
        Toast.makeText(this, ErrorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToken(Token token) {
        if (token != null) {
            final FeedAppDatabase db = Room.databaseBuilder(this, FeedAppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
            persistData = new PersistData(0, "", "", "", false, false, false);
            try {

                persistData = db.getPersistDataDAO().getPersistData();
                persistData.setToken(token.getToken());
                persistData.setPassword(etPassword.getText().toString());
                persistData.setUsername(etUsernmae.getText().toString());
                db.getPersistDataDAO().update(persistData);

            } catch (SQLiteConstraintException sce) {
                Log.i("LoginView - showToken", "Algo ha ocurrido malo");
                Snackbar.make(loginButton, "Algo ha ocurrido malo", BaseTransientBottomBar.LENGTH_LONG).show();
            } finally {
                db.close();
            }

            Intent intent = new Intent(LoginView.this, MainActivity.class);
            intent.putExtra("username", persistData.getUsername());
            startActivity(intent);
            Log.i("LoginView - showToken", "Token guardado!");
            Log.i("LoginView - showToken", token.getToken());
            Log.i("LoginView - showToken", persistData.toString());
            Snackbar.make(loginButton, "Login Correcto", BaseTransientBottomBar.LENGTH_LONG).show();

        } else {
            Snackbar.make(loginButton, "Login Incorrecto", BaseTransientBottomBar.LENGTH_LONG).show();
        }
    }

    public void login(View view) {
        String username = etUsernmae.getText().toString();
        String password = etPassword.getText().toString();
        PersonLogin userLogin = new PersonLogin(username, password);
        presenter.loadToken(userLogin);
    }

    public void addUserNav(View view) {
        Intent intent = new Intent(this, AddUserView.class);
        startActivity(intent);

    }
}