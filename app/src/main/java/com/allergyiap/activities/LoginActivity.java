package com.allergyiap.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.allergyiap.R;
import com.allergyiap.service.UserService;
import com.allergyiap.utils.CommonServices;
import com.allergyiap.utils.D;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";

    private Button loginButton;
    private EditText usernameText;
    private EditText passwordText;
    private EditText emailText;
    private TextView signupLink;
    private Switch terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.btn_login);
        //usernameText = (EditText) findViewById(R.id.text_username);
        passwordText = (EditText) findViewById(R.id.input_password);
        emailText = (EditText) findViewById(R.id.input_email);
        signupLink = (TextView) findViewById(R.id.link_signup);
        terms = (Switch) findViewById(R.id.switch_terms);
        ((TextView)findViewById(R.id.text_terms2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, TermsActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, CommonServices.REQUEST_SIGNUP);
            }
        });

        D.showSimpleDialog(this, getString(R.string.app_name), getString(R.string.about_login));

    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");


        final String email = emailText.getText().toString();
        final String password = passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.show();
            }

            @Override
            protected Boolean doInBackground(Void... voids) {
                return UserService.login(email, password);
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                if (result) {
                    onLoginSuccess();
                } else {
                    onLoginFailed();
                }
                progressDialog.dismiss();
            }
        }.execute();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CommonServices.REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
                startActivity(new Intent(this, LaunchScreenActivity.class));
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        finish();
        startActivity(new Intent(this, LaunchScreenActivity.class));
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        /*if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid emailText address");
            valid = false;
        } else {
            emailText.setError(null);
        }*/
        if (email.isEmpty() || email.length() < 3) {
            emailText.setError("at least 3 characters");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        if (!terms.isChecked()) {
            //terms.setError("Agree the terms");
            valid = false;
        } else {
            //terms.setError(null);
        }

        return valid;
    }
}
