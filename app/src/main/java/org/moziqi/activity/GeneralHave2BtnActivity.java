package org.moziqi.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import moziqi.te.R;

/**
 * Created by moziqi on 15-8-19.
 */
public abstract class GeneralHave2BtnActivity extends ActionBarActivity {
    private TopListener topListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initView() {
        findViewById(R.id.top_left_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topListener != null) {
                    topListener.setTopLeftBtnListener();
                }
            }
        });
        findViewById(R.id.top_right_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topListener != null) {
                    topListener.setTopRightBtnListener();
                }
            }
        });
    }

    interface TopListener {
        public void setTopRightBtnListener();
        public void setTopLeftBtnListener();
    }

    public void setTopListener(TopListener topListener) {
        this.topListener = topListener;
        initView();
    }

    public void showShortToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
