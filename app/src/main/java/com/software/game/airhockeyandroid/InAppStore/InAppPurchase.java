package com.software.game.airhockeyandroid.InAppStore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    int coins = 0;
    String powerType;
    int powerCount;

    private RequestQueue mQueue;

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
        coins = Player.getInstance().getPoints();
    }

    private void assignPowerUp(String type) {
        int deduct = 0;
        if (coins != 0) {
            for (int i = 0; i < 3; i++) {
                String power_type = Player.powerUps.get(i).getType();
                int powerUp_count = Player.powerUps.get(i).getCount();
                if (powerUp_count < 11 && power_type.equalsIgnoreCase(type)) {
                    if (power_type.equalsIgnoreCase("Mallet Size")) {
                        if (coins >= 300)
                            deduct = 300;
                        else {
                            Toast.makeText(InAppPurchase.this, R.string.insufficient_coins, Toast.LENGTH_SHORT).show();
                            break;
                        }
                    } else if (power_type.equalsIgnoreCase("Goal Size")) {
                        if (coins >= 300)
                            deduct = 300;
                        else {
                            Toast.makeText(InAppPurchase.this, R.string.insufficient_coins, Toast.LENGTH_SHORT).show();
                            break;
                        }
                    } else if (power_type.equalsIgnoreCase("Puck")) {
                        if (coins >= 200)
                            deduct = 200;
                        else {
                            Toast.makeText(InAppPurchase.this, R.string.insufficient_coins, Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    Player.powerUps.remove(i);
                    PowerUp power = new PowerUp(powerUp_count + 1, power_type);
                    Player.powerUps.add(i, power);
                    coins = coins - deduct;
                    mBalance.setText(Integer.toString(coins));
                    Player.getInstance().setPoints(coins);
                    Map<String, String> params = new HashMap<>();
                    params.put("username", Player.getInstance().getUsername());
                    params.put("coins", String.valueOf(coins));
                    updateDataBase(Constants.UPDATE_COINS_URL, params);
                    powerType = type;
                    powerCount = powerUp_count+1;
                    break;
                }
                else
                    Toast.makeText(InAppPurchase.this, R.string.no_more_than_ten, Toast.LENGTH_SHORT).show();
            }
        } else
            Toast.makeText(InAppPurchase.this, R.string.insufficient_coins, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
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

    private void updateDataBase(String url, Map<String, String> params) {
        CustomJSONRequest req = new CustomJSONRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                updatePowerUpDataBase(Constants.UPDATE_POWER_UP_URL);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(req);
    }

    private void updatePowerUpDataBase(String url) {
        Map<String, String> params = new HashMap<>();
        params.put("username", Player.getInstance().getUsername());
        params.put("count", String.valueOf(powerCount));
        params.put("type", powerType);
        CustomJSONRequest req = new CustomJSONRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(req);
    }
}
