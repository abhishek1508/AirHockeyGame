package com.software.game.airhockeyandroid.InAppStore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.software.game.airhockeyandroid.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_in_app_purchase);
    }

    private void initialize() {
        mPaddlePopLayout = (LinearLayout) findViewById(R.id.layout_update_paddle);
        mBlackHoleLayout = (LinearLayout) findViewById(R.id.layout_update_hole);
        mMultiPuckLayout = (LinearLayout) findViewById(R.id.layout_multi_puck);
        mPaddle = (Button) findViewById(R.id.paddle_pop);
        mPuck= (Button) findViewById(R.id.multi_puck);
        mHole= (Button) findViewById(R.id.black_hole);
        mPaddle.setOnClickListener(this);
        mHole.setOnClickListener(this);
        mPuck.setOnClickListener(this);
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


        }
    }
}
