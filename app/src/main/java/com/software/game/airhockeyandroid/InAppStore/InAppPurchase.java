package com.software.game.airhockeyandroid.InAppStore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.software.game.airhockeyandroid.Entities.Player;
import com.software.game.airhockeyandroid.Entities.PowerUp;
import com.software.game.airhockeyandroid.NetworkManager.CustomJSONRequest;
import com.software.game.airhockeyandroid.NetworkManager.VolleySingleton;
import com.software.game.airhockeyandroid.R;
import com.software.game.airhockeyandroid.Utilities.Constants;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by neela on 10/21/2015.
 */
public class InAppPurchase extends AppCompatActivity implements View.OnClickListener {
    LinearLayout mPaddlePopLayout;
    LinearLayout mBlackHoleLayout;
    LinearLayout mMultiPuckLayout;
    Button mPaddle;
    Button mPuck;
    Button mHole;
    Button mBuyPaddle;
    Button mBuyHole;
    Button mBuyPuck;
    TextView mBalance;
    boolean isUpdated = true;

    private RequestQueue mQueue;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_in_app_purchase);
        initialize();

    }

    private void initialize() {
        mPaddlePopLayout = (LinearLayout) findViewById(R.id.layout_update_paddle);
        mBlackHoleLayout = (LinearLayout) findViewById(R.id.layout_update_hole);
        mMultiPuckLayout = (LinearLayout) findViewById(R.id.layout_multi_puck);
        mPaddle = (Button) findViewById(R.id.paddle_pop);
        mPuck = (Button) findViewById(R.id.multi_puck);
        mHole = (Button) findViewById(R.id.black_hole);
        mBuyPaddle = (Button) findViewById(R.id.buy_paddle);
        mBuyHole = (Button) findViewById(R.id.buy_hole);
        mBuyPuck = (Button) findViewById(R.id.buy_puck);
        mPaddle.setOnClickListener(this);
        mHole.setOnClickListener(this);
        mPuck.setOnClickListener(this);
        mBuyPaddle.setOnClickListener(this);
        mBuyPuck.setOnClickListener(this);
        mBuyHole.setOnClickListener(this);
        mBlackHoleLayout.setVisibility(View.GONE);
        mMultiPuckLayout.setVisibility(View.GONE);
        mPaddlePopLayout.setVisibility(View.GONE);
        mBalance = (TextView) findViewById(R.id.balance_text);
        mBalance.setText(Integer.toString(Player.getInstance().getPoints()));
        mQueue = VolleySingleton.getsInstance().getRequestQueue();
        player = Player.getInstance();

    }

    private void assignPowerUp(String type) {
        int deduct = 0;
        if (!player.powerUps.isEmpty()) {
            for (PowerUp p : player.powerUps) {
                String power_type = p.getType();
                int powerUp_count = p.getCount();
                if (powerUp_count < 11 && power_type.equalsIgnoreCase(type)) {
                    if (power_type.equalsIgnoreCase("Mallet Size"))
                        deduct = 300;
                    else if (power_type.equalsIgnoreCase("Goal Size"))
                        deduct = 300;
                    else if (power_type.equalsIgnoreCase("Puck"))
                        deduct = 200;
                    Player.powerUps.remove(i);
                    PowerUp power = new PowerUp(powerUp_count, power_type);
                    Player.powerUps.add(i, power);
                    mBalance.setText(Integer.toString(Player.getInstance().getPoints() - deduct));
                    Map<String, String> params = new HashMap<>();
                    params.put("username", Player.getInstance().getUsername());
                    params.put("coins", String.valueOf(Player.getInstance().getPoints() - deduct));
                    updateDataBase(Constants.UPDATE_COINS_URL, params);
                    while (isUpdated) ;
                    params.clear();
                    params.put("username", Player.getInstance().getUsername());
                    params.put("count", String.valueOf(powerUp_count));
                    params.put("type", power_type);
                    updateDataBase(Constants.UPDATE_POWER_UP_URL, params);
                    break;
                }
            }
        }
        else{
            PowerUp power = new PowerUp(1,type);
            player.powerUps.add(power);
        }

    }

            @Override
            public void onClick (View v){
                switch (v.getId()) {

                    case R.id.paddle_pop:
                        mBlackHoleLayout.setVisibility(View.GONE);
                        mPaddlePopLayout.setVisibility(View.VISIBLE);
                        mMultiPuckLayout.setVisibility(View.GONE);
                        break;
                    case R.id.black_hole:
                        mBlackHoleLayout.setVisibility(View.VISIBLE);
                        mMultiPuckLayout.setVisibility(View.GONE);
                        mPaddlePopLayout.setVisibility(View.GONE);
                        break;
                    case R.id.multi_puck:
                        mPaddlePopLayout.setVisibility(View.GONE);
                        mMultiPuckLayout.setVisibility(View.VISIBLE);
                        mBlackHoleLayout.setVisibility(View.GONE);
                        break;

                    case R.id.buy_paddle:
                        assignPowerUp("Mallet Size");
                        break;

                    case R.id.buy_hole:
                        assignPowerUp("Goal Size");
                        break;

                    case R.id.buy_puck:
                        assignPowerUp("Puck");
                        break;
                }
            }

        private void updateDataBase (String url, Map < String, String > params){
            CustomJSONRequest req = new CustomJSONRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    isUpdated = false;
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            mQueue.add(req);
        }
    }
