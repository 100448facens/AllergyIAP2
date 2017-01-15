package com.allergyiap.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.allergyiap.R;
import com.allergyiap.beans.User;
import com.allergyiap.service.UserService;
import com.allergyiap.utils.C;
import com.allergyiap.utils.D;
import com.allergyiap.utils.Util;

public class SignupActivity extends BaseActivity {

    private static final String TAG = "SignupActivity";

    private EditText nameText;
    //    private EditText emailText;
    private EditText passwordText;
    private Button signupButton;
    private TextView loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        nameText = (EditText) findViewById(R.id.input_name);
        //emailText = (EditText) findViewById(R.id.input_email);
        passwordText = (EditText) findViewById(R.id.input_password);
        signupButton = (Button) findViewById(R.id.btn_signup);
        loginLink = (TextView) findViewById(R.id.link_login);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signup();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = nameText.getText().toString();
        //String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String msg = "";
        try {
            msg = Util.getUrlAsync(C.Network.WS_URL + "signup/" + name + "/" + password);
            msg = msg.replace("\n", "");
            msg = msg.replace("\r", "");
            long l = Long.parseLong(msg);
            if (l > 0) {
                onSignupSuccess();
                progressDialog.dismiss();
                UserService.setUserId(l);
                User u = UserService.getCurrentUser();
                u.setUser_name(name);
                u.setUser_password(password);
                u.setUser_mail(name);
                UserService.update(u);
            } else if (l == -1) {
                onSignupFailed();
                progressDialog.dismiss();
                D.showSimpleDialog(this, "Signup", "Please select another name");
            } else {
                onSignupFailed();
                progressDialog.dismiss();
                D.showSimpleDialog(this, "Signup", msg);
            }
        } catch (Exception e) {
            onSignupFailed();
            progressDialog.dismiss();
            D.showSimpleDialog(this, "Signup", e.getMessage());
            Log.e("TESTE", msg, e);
        }
        // TODO: Implement your own signup logic here.

        /*new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);*/
    }


    public void onSignupSuccess() {
        signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        //String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameText.setError("at least 3 characters");
            valid = false;
        } else {
            nameText.setError(null);
        }
/*
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }
*/
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }

}
