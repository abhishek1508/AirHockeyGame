package com.software.game.airhockeyandroid.LoginManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.software.game.airhockeyandroid.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abhishek on 10/16/2015.
 */
public class ManageProfile extends AppCompatActivity implements View.OnClickListener {

    CardView mDeleteProfile;
    CheckBox mCheckUsername;
    CheckBox mCheckPassword;
    EditText mOldUsername;
    EditText mNewUsername;
    EditText mUsername;
    EditText mOldPassword;
    EditText mNewPassword;
    Button mUpdate;
    LinearLayout mUsernameLayout;
    LinearLayout mPasswordLayout;

    private boolean usernameChecked = false;
    private boolean passwordChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_manage_profile);

        initialize();
    }

    private void initialize() {
        mDeleteProfile = (CardView) findViewById(R.id.delete_profile_card);
        mCheckUsername = (CheckBox) findViewById(R.id.update_username_checkbox);
        mCheckPassword = (CheckBox) findViewById(R.id.update_password_checkbox);
        mOldUsername = (EditText) findViewById(R.id.update_old_username_edit_text);
        mNewUsername = (EditText) findViewById(R.id.update_new_username_edit_text);
        mUsername = (EditText) findViewById(R.id.enter_username);
        mOldPassword = (EditText) findViewById(R.id.update_old_password_edit_text);
        mNewPassword = (EditText) findViewById(R.id.update_new_password_edit_text);
        mUpdate = (Button) findViewById(R.id.update);
        mUsernameLayout = (LinearLayout) findViewById(R.id.layout_update_username);
        mPasswordLayout = (LinearLayout) findViewById(R.id.layout_update_password);
        mCheckUsername.setOnClickListener(this);
        mCheckPassword.setOnClickListener(this);
        mDeleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ManageProfile.this, R.string.update_both_fields_or_any_one, Toast.LENGTH_SHORT).show();
            }
        });
        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyInformationEntered();
            }
        });
    }

    private void verifyInformationEntered() {
        Map<String,String> params = new HashMap<>();
        if (usernameChecked && passwordChecked) {
            if ((mOldUsername.getText().toString()).equalsIgnoreCase("") || (mNewUsername.getText().toString()).equalsIgnoreCase("") || (mUsername.getText().toString()).equalsIgnoreCase("") || (mOldPassword.getText().toString()).equalsIgnoreCase("") || (mNewPassword.getText().toString()).equalsIgnoreCase("") )
                Toast.makeText(ManageProfile.this,R.string.enter_all_info,Toast.LENGTH_LONG).show();
            else{
                params.put("oldUsername",mOldUsername.getText().toString());
                params.put("newUsername",mNewUsername.getText().toString());
                params.put("username",mUsername.getText().toString());
                params.put("oldPassword",mOldPassword.getText().toString());
                params.put("newPassword",mNewPassword.getText().toString());
                updateProfile(params);
            }
        } else if (usernameChecked) {
            if ((mOldUsername.getText().toString()).equalsIgnoreCase("") || (mNewUsername.getText().toString()).equalsIgnoreCase(""))
                Toast.makeText(ManageProfile.this,R.string.enter_all_info,Toast.LENGTH_LONG).show();
            else{
                params.put("oldUsername",mOldUsername.getText().toString());
                params.put("newUsername",mNewUsername.getText().toString());
                updateProfile(params);
            }
        } else if (passwordChecked) {
            if ((mUsername.getText().toString()).equalsIgnoreCase("") || (mOldPassword.getText().toString()).equalsIgnoreCase("") || (mNewPassword.getText().toString()).equalsIgnoreCase("") )
                Toast.makeText(ManageProfile.this,R.string.enter_all_info,Toast.LENGTH_LONG).show();
            else{
                params.put("username",mUsername.getText().toString());
                params.put("oldPassword",mOldPassword.getText().toString());
                params.put("newPassword",mNewPassword.getText().toString());
                updateProfile(params);
            }
        } else
            Toast.makeText(ManageProfile.this, R.string.update_both_fields_or_any_one, Toast.LENGTH_SHORT).show();
    }

    private void deleteProfile() {

    }

    private void updateProfile(Map<String,String> params) {

    }

    @Override
    public void onClick(View v) {
        boolean ifChecked = ((CheckBox) v).isChecked();
        switch (v.getId()) {

            case R.id.update_username_checkbox:
                if (ifChecked) {
                    mUsernameLayout.setVisibility(View.VISIBLE);
                    usernameChecked = true;
                } else {
                    mUsernameLayout.setVisibility(View.GONE);
                    usernameChecked = false;
                }
                break;

            case R.id.update_password_checkbox:
                if (ifChecked) {
                    mPasswordLayout.setVisibility(View.VISIBLE);
                    passwordChecked = true;
                } else {
                    mPasswordLayout.setVisibility(View.GONE);
                    passwordChecked = false;
                }
                break;
        }
    }
}
