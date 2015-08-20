package org.moziqi.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.moziqi.fragment.BlankHome2Fragment;
import org.moziqi.fragment.BlankHome3Fragment;
import org.moziqi.fragment.BlankHomeFragment;

import moziqi.te.R;

public class MainHomeActivity extends GeneralHave2BtnActivity implements RadioGroup.OnCheckedChangeListener, GeneralHave2BtnActivity.TopListener {
    private BlankHomeFragment mBlankHomeFragment;
    private BlankHome2Fragment mBlankHome2Fragment;
    private BlankHome3Fragment mBlankHome3Fragment;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        supportFragmentManager = getSupportFragmentManager();
        ((RadioGroup) findViewById(R.id.rg_home)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.rb_one)).setChecked(true);
        setFragmentSelect(R.id.rb_one);
        setTopListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_home, menu);
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
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        setFragmentSelect(checkedId);
    }

    private void setFragmentSelect(int id) {
        if (supportFragmentManager == null) {
            return;
        }
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        //先隐藏所有
        if (mBlankHomeFragment != null) {
            fragmentTransaction.hide(mBlankHomeFragment);
        }
        if (mBlankHome2Fragment != null) {
            fragmentTransaction.hide(mBlankHome2Fragment);
        }
        if (mBlankHome3Fragment != null) {
            fragmentTransaction.hide(mBlankHome3Fragment);
        }
        switch (id) {
            case R.id.rb_one:
                if (mBlankHomeFragment == null) {
                    mBlankHomeFragment = new BlankHomeFragment();
                    fragmentTransaction.add(R.id.fl_main_home, mBlankHomeFragment);
                } else {
                    fragmentTransaction.show(mBlankHomeFragment);
                }
                break;
            case R.id.rb_two:
                if (mBlankHome2Fragment == null) {
                    mBlankHome2Fragment = new BlankHome2Fragment();
                    fragmentTransaction.add(R.id.fl_main_home, mBlankHome2Fragment);
                } else {
                    fragmentTransaction.show(mBlankHome2Fragment);
                }
                break;
            case R.id.rb_three:
                if (mBlankHome3Fragment == null) {
                    mBlankHome3Fragment = new BlankHome3Fragment();
                    fragmentTransaction.add(R.id.fl_main_home, mBlankHome3Fragment);
                } else {
                    fragmentTransaction.show(mBlankHome3Fragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    public void setTopRightBtnListener() {
        showShortToast("right");
    }

    @Override
    public void setTopLeftBtnListener() {
        showShortToast("left");
    }
}
