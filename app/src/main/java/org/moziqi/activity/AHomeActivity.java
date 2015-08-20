package org.moziqi.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.moziqi.fragment.BottomFragment;
import org.moziqi.fragment.GeneralFragment;

import moziqi.te.R;

public class AHomeActivity extends ActionBarActivity implements BottomFragment.CallBacks {

    public final static String Item = "item";

    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahome);

//        getSupportActionBar().hide();
        if (savedInstanceState != null) {
            state = savedInstanceState.getInt("state");
            onItemSelected(state);
        } else {
            onItemSelected(R.id.fragment_bottom_home);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("state", state);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        state = savedInstanceState.getInt("state");
        onItemSelected(state);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ahome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(int item) {
        Bundle arguments = new Bundle();
        state = item;
        arguments.putInt(Item, item);//将选中的底部radio的Id放进去
        GeneralFragment generalFragment = new GeneralFragment();//设置参数值
        generalFragment.setArguments(arguments);
        //这里根据item的ID进行界面跳转
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction().replace(R.id.main_detail_FrameLayout, generalFragment).commit();
    }
}
