package com.software.game.airhockeyandroid.LeadershipBoard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.TableRow.LayoutParams;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.software.game.airhockeyandroid.Entities.Player;
import com.software.game.airhockeyandroid.GameSettings.Settings;
import com.software.game.airhockeyandroid.LoginManager.ChooseFromMenu;
import com.software.game.airhockeyandroid.NetworkManager.CustomJSONRequest;
import com.software.game.airhockeyandroid.NetworkManager.VolleySingleton;
import com.software.game.airhockeyandroid.NetworkRequest.JSONManager;
import com.software.game.airhockeyandroid.R;
import com.software.game.airhockeyandroid.Utilities.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.software.game.airhockeyandroid.R;

/**
 * Created by neela on 10/31/2015.
 */
public class Leadership extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_leadership_board);
    }
}
