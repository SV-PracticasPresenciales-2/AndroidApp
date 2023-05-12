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
import com.svalero.globalfeed.contract.user.RegisterUserContract;
import com.svalero.globalfeed.db.FeedAppDatabase;
import com.svalero.globalfeed.domain.PersistData;
import com.svalero.globalfeed.domain.Token;
import com.svalero.globalfeed.domain.dto.UserDTO;
import com.svalero.globalfeed.presenter.token.RegisterUserPresenter;
import com.svalero.globalfeed.presenter.token.TokenPresenter;

public class AddUserView extends AppCompatActivity implements RegisterUserContract.View {

    private PersistData persistData;
    private EditText etUsernmae;
    private EditText etPassword;

    private EditText etBio;

    private EditText etName;

    private EditText etPhone;
    private RegisterUserPresenter presenter;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);

        etUsernmae = findViewById(R.id.etRegisterUsername);
        etPassword = findViewById(R.id.etRegisterPassword);
        etBio = findViewById(R.id.etRegisterBio);
        etName = findViewById(R.id.etRegisterName);
        etPhone = findViewById(R.id.etRegisterPhone);
        registerButton = findViewById(R.id.button);

        presenter = new RegisterUserPresenter(this);


    }

    @Override
    public void showError(String ErrorMessage) {
        Toast.makeText(this, ErrorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginView.class);
        intent.putExtra("username", etUsernmae.getText().toString());
        startActivity(intent);
    }


    public void register(View view) {
        String username = etUsernmae.getText().toString();
        String password = etPassword.getText().toString();
        String name = etName.getText().toString();
        String biography = etBio.getText().toString();
        int phoneNumber = Integer.parseInt(etPhone.getText().toString());

        UserDTO user = new UserDTO(username, password, name, biography, phoneNumber);
        presenter.registerUser(user);
    }

    public void returnNav(View view) {
        Intent intent = new Intent(this, LoginView.class);
        startActivity(intent);
    }
}