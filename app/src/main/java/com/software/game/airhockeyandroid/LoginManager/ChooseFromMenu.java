package com.software.game.airhockeyandroid.LoginManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.software.game.airhockeyandroid.GameSettings.Settings;
import com.software.game.airhockeyandroid.R;

/**
 * Created by Abhishek on 10/14/2015.
 */
public class ChooseFromMenu extends AppCompatActivity implements View.OnClickListener{

    Button mChooseMode;
    Button mManageProfile;
    Button mInAppStore;
    Button mGameSettings;
    Button mLeadershipBoard;
    Button mExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu_options);

        initialize();
    }

    private void initialize(){
        mChooseMode = (Button) findViewById(R.id.choose_mode);
        mManageProfile = (Button) findViewById(R.id.manage_profile);
        mInAppStore = (Button) findViewById(R.id.in_app_store);
        mGameSettings = (Button) findViewById(R.id.game_setting);
        mLeadershipBoard = (Button) findViewById(R.id.leadership_board);
        mExit = (Button) findViewById(R.id.exit);
        mChooseMode.setOnClickListener(this);
        mManageProfile.setOnClickListener(this);
        mInAppStore.setOnClickListener(this);
        mGameSettings.setOnClickListener(this);
        mLeadershipBoard.setOnClickListener(this);
        mExit.setOnClickListener(this);
    }

    private void openActivity(Class<?> toActivity){
        Intent intent = new Intent(ChooseFromMenu.this,toActivity);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.manage_profile:
                openActivity(ManageProfile.class);
                break;

            case R.id.choose_mode:
                break;

            case R.id.in_app_store:
                break;

            case R.id.game_setting:
                openActivity(Settings.class);
                break;

            case R.id.leadership_board:
                break;

            case R.id.exit:
                break;
        }
    }
}
