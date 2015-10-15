package com.software.game.airhockeyandroid.LoginManager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.software.game.airhockeyandroid.GameSettings.Settings;
import com.software.game.airhockeyandroid.NetworkManager.CustomJSONRequest;
import com.software.game.airhockeyandroid.NetworkManager.VolleySingleton;
import com.software.game.airhockeyandroid.R;
import com.software.game.airhockeyandroid.Utilities.Constants;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class User_Login extends AppCompatActivity implements View.OnClickListener {

    EditText mUsername;
    EditText mPassword;
    Button mLogin;
    Button mCreateProfile;
    private RequestQueue queue = null;
    private String mDebug = User_Login.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user_login);

        initialize();
    }

    private void initialize() {
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.login_button);
        mCreateProfile = (Button) findViewById(R.id.create_profile_button);
        mLogin.setOnClickListener(this);
        mCreateProfile.setOnClickListener(this);
        queue = VolleySingleton.getsInstance().getRequestQueue();
    }

    private void verifyLogin(String name, String password) {
        Map<String, String> params = new HashMap<>();
        if (name.equalsIgnoreCase("/") || password.equalsIgnoreCase(""))
            Toast.makeText(User_Login.this, R.string.incomplete_credentials, Toast.LENGTH_SHORT).show();
        else{
            params.put("username", name);
            params.put("password", password);
            CustomJSONRequest request = new CustomJSONRequest(Request.Method.POST, Constants.LOGIN_URL, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(User_Login.this, "Logged in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(User_Login.this, ChooseFromMenu.class);
                    startActivity(intent);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(User_Login.this, "Wrong information", Toast.LENGTH_SHORT).show();
                }
            });
            if (queue != null)
                queue.add(request);
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String name = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        if (id == R.id.login_button) {
            verifyLogin(name, password);
        } else if (id == R.id.create_profile_button) {
            Intent intent = new Intent(User_Login.this, CreateProfile.class);
            startActivity(intent);
        }
    }
}
