package org.moziqi.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.moziqi.activity.ResultActivity;
import org.moziqi.adapter.VHomeViewPager;
import org.moziqi.blow.handler.RecordHandler;
import org.moziqi.ui.HorizontalScrollView.PagerSlidingTabStrip;
import org.moziqi.util.Constant;
import org.moziqi.util.ProgressBarAsyncTask;

import java.lang.reflect.Field;

import moziqi.te.R;


public class VHomeFragment extends GeneralFragment implements View.OnClickListener {

    private static VHomeFragment fragment = null;
    private ViewPager mViewPager;
    private VHomeViewPager vHomeViewPager;
    private PagerSlidingTabStrip mTabs;
    private View oneView;
    private View twoView;
    private View threeView;
    public static int i = 0;
    private RecordHandler handler;
    private ThreeView mThreeView;
    private OneView mOneView;
    /**
     * 获取当前屏幕的密度
     */
    private DisplayMetrics dm;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    public static GeneralFragment getInstance() {
        return fragment == null ? fragment = new VHomeFragment() : fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(getResources().getString(R.string.home));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vhome, container, false);
        setOverflowShowingAlways();
        dm = getResources().getDisplayMetrics();
        mViewPager = (ViewPager) rootView.findViewById(R.id.vp_home_content);
        mTabs = (PagerSlidingTabStrip) rootView.findViewById(R.id.tabs);
        //获取LayoutInflater
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        //设置3个页面
        oneView = layoutInflater.inflate(R.layout.view_vhome_one, null);
        twoView = layoutInflater.inflate(R.layout.view_vhome_two, null);
        threeView = layoutInflater.inflate(R.layout.view_vhome_three, null);

        //初始化界面
        initView();
        View[] views = new View[]{oneView, twoView, threeView};
        String[] titles = getResources().getStringArray(R.array.homePageName);
        vHomeViewPager = new VHomeViewPager(titles, views);
        mViewPager.setAdapter(vHomeViewPager);
        mTabs.setViewPager(mViewPager);
        setTabsValue();
        return rootView;
    }

    public void initView() {
        mOneView = new OneView();
        mOneView.mButton = (Button) oneView.findViewById(R.id.btn_home_one);
        mOneView.mTextView = (TextView) oneView.findViewById(R.id.tv_home_one);
        mOneView.mButton.setOnClickListener(this);
        handler = new RecordHandler("你吹了一下屏幕" + i, mOneView.mTextView);
        mThreeView = new ThreeView();
        mThreeView.mButton = (Button) threeView.findViewById(R.id.btn_home_three_update);
        mThreeView.mTextView = (TextView) threeView.findViewById(R.id.tv_home_three_name);
        mThreeView.mProgressBar = (ProgressBar) threeView.findViewById(R.id.pb_home_three);
        mThreeView.mButton.setOnClickListener(this);
    }

    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        mTabs.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        mTabs.setDividerColor(Color.TRANSPARENT);
        // 设置Tab底部线的高度
        mTabs.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        mTabs.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, dm));
        // 设置Tab标题文字的大小
        mTabs.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, dm));
        // 设置Tab Indicator的颜色
        mTabs.setIndicatorColor(Color.parseColor("#45c01a"));
        // 设置选中Tab文字的颜色 (这是我自定义的一个方法)
        mTabs.setSelectedTextColor(Color.parseColor("#45c01a"));

        // 取消点击Tab时的背景色
        mTabs.setTabBackground(0);
    }

    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(getActivity());
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        Intent mIntent = null;
        switch (v.getId()) {
            case R.id.btn_home_one:
                //new RecordThread(handler).start();
                mIntent = new Intent(getActivity(), ResultActivity.class);
                //startActivity(mIntent);
                startActivityForResult(mIntent, Constant.Home2ResultActivity.requestCode);
                break;
            case R.id.btn_home_three_update:
                ProgressBarAsyncTask mProgressBarAsyncTask = new ProgressBarAsyncTask(mThreeView.mTextView, mThreeView.mProgressBar);
                mProgressBarAsyncTask.execute(1000);
            default:
                break;
        }
    }

    private static class Mytask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return null;
        }
    }

    private static class OneView {
        public Button mButton;
        public TextView mTextView;
    }

    private static class ThreeView {
        public TextView mTextView;
        public ProgressBar mProgressBar;
        public Button mButton;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        String msg = null;
        switch (requestCode) {
            case Constant.Home2ResultActivity.requestCode:
                msg = data.getStringExtra("result");
                mOneView.mTextView.setText(msg);
                break;
            default:
                break;
        }
    }
}
