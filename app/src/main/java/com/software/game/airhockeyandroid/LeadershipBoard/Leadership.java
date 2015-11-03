package com.software.game.airhockeyandroid.LeadershipBoard;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.software.game.airhockeyandroid.Entities.Player;
import com.software.game.airhockeyandroid.NetworkManager.CustomJSONRequest;
import com.software.game.airhockeyandroid.NetworkManager.VolleySingleton;
import com.software.game.airhockeyandroid.R;
import com.software.game.airhockeyandroid.Utilities.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import com.software.game.airhockeyandroid.R;

/**
 * Created by neela on 10/31/2015.
 */
public class Leadership extends AppCompatActivity implements OnClickListener {

    private RequestQueue mQueue;
    TableLayout mTable;
    TextView mUserName;
    TextView mCoins;
    TextView mRank;
    TextView mGamesWon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_leadership_board);
        mQueue = VolleySingleton.getsInstance().getRequestQueue();
        mTable = (TableLayout) findViewById(R.id.leader_table);
        mTable.removeAllViewsInLayout();
        initialize();
        CustomJSONRequest request = new CustomJSONRequest(Request.Method.GET, Constants.LEADERSHIP_URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                showTable(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);
    }

    private void showTable(JSONObject response) {
        try {
            int length;
            if (response.getInt("success") == 1) {
                JSONArray jArray = response.getJSONArray("players");
                if (jArray.length() < 10)
                    length = jArray.length();
                else
                    length = 10;
                int flag = 1;
                for (int i = -1; i <= length - 1; i++) {
                    TableRow tr = new TableRow(Leadership.this);
                    if (flag == 1) {
                        TextView b6 = new TextView(Leadership.this);
                        b6.setPadding(20, 20, 20, 20);
                        ;
                        b6.setText("UserName");
                        b6.setTextColor(Color.BLUE);
                        b6.setTextSize(17);
                        b6.setGravity(Gravity.CENTER);
                        b6.setTypeface(Typeface.DEFAULT_BOLD);
                        tr.addView(b6);

                        TextView b19 = new TextView(Leadership.this);
                        b19.setPadding(20, 20, 20, 20);
                        ;
                        b19.setTextSize(17);
                        b19.setText("Coins");
                        b19.setTextColor(Color.BLUE);
                        b19.setGravity(Gravity.CENTER);
                        b19.setTypeface(Typeface.DEFAULT_BOLD);
                        tr.addView(b19);

                        TextView b29 = new TextView(Leadership.this);
                        b29.setPadding(20, 20, 20, 20);
                        ;
                        b29.setText("Rank");
                        b29.setTextColor(Color.BLUE);
                        b29.setTextSize(17);
                        b29.setTypeface(Typeface.DEFAULT_BOLD);
                        b29.setGravity(Gravity.CENTER);
                        tr.addView(b29);

                        TextView b39 = new TextView(Leadership.this);
                        b39.setPadding(20, 20, 20, 20);
                        ;
                        b39.setText("Games Won");
                        b39.setTextColor(Color.BLUE);
                        b39.setTextSize(17);
                        b39.setGravity(Gravity.CENTER);
                        b39.setTypeface(Typeface.DEFAULT_BOLD);
                        tr.addView(b39);

                        mTable.addView(tr);

                        final View vline = new View(Leadership.this);
                        vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 2));
                        vline.setBackgroundColor(Color.BLUE);

                        mTable.addView(vline);
                        flag = 0;
                    } else {

                        JSONObject json_data = jArray.getJSONObject(i);

                        Log.i("log_tag", "Username:" + json_data.getString("username") +
                                ", Coins: " + json_data.getInt("coins") +
                                ", Rank: " + json_data.getInt("rank") +
                                ", Games_won: " + json_data.getInt("games_won"));

                        TextView b = new TextView(Leadership.this);
                        String stime = String.valueOf(json_data.getString("username"));
                        b.setPadding(20, 20, 20, 20);
                        ;
                        b.setText(stime);
                        b.setTextColor(Color.RED);
                        b.setTextSize(17);
                        b.setGravity(Gravity.CENTER);
                        b.setTypeface(Typeface.DEFAULT_BOLD);
                        tr.addView(b);

                        TextView b1 = new TextView(Leadership.this);
                        String stime1 = String.valueOf(json_data.getInt("coins"));
                        b1.setPadding(20, 20, 20, 20);
                        ;
                        b1.setText(stime1);
                        b1.setTextColor(Color.RED);
                        b1.setTextSize(17);
                        b1.setGravity(Gravity.CENTER);
                        b1.setTypeface(Typeface.DEFAULT_BOLD);
                        tr.addView(b1);

                        TextView b2 = new TextView(Leadership.this);
                        String stime2 = String.valueOf(json_data.getInt("rank"));
                        b2.setPadding(20, 20, 20, 20);
                        ;
                        b2.setText(stime2);
                        b2.setTextColor(Color.RED);
                        b2.setTextSize(17);
                        b2.setGravity(Gravity.CENTER);
                        b2.setTypeface(Typeface.DEFAULT_BOLD);
                        tr.addView(b2);

                        TextView b3 = new TextView(Leadership.this);
                        String stime3 = String.valueOf(json_data.getInt("games_won"));
                        b3.setPadding(20, 20, 20, 20);
                        ;
                        b3.setText(stime3);
                        b3.setTextColor(Color.RED);
                        b3.setTextSize(17);
                        b3.setGravity(Gravity.CENTER);
                        b3.setTypeface(Typeface.DEFAULT_BOLD);
                        tr.addView(b3);

                        mTable.addView(tr);

                        final View vline1 = new View(Leadership.this);
                        vline1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
                        vline1.setBackgroundColor(Color.WHITE);
                        mTable.addView(vline1);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialize() {
        mUserName = (TextView) findViewById(R.id.user_nam_text);
        mCoins = (TextView) findViewById(R.id.leader_points_user);
        mRank = (TextView) findViewById(R.id.leader_rank_user);
        mGamesWon = (TextView) findViewById(R.id.leader_won_user);

        mUserName.setText(Player.getInstance().getUsername());
        mCoins.setText(Integer.toString(Player.getInstance().getPoints()));
        mRank.setText(Integer.toString(Player.getInstance().getRank()));
        mGamesWon.setText(Integer.toString(Player.getInstance().getGames_won()));

    }

    @Override
    public void onClick(View v) {

    }
}