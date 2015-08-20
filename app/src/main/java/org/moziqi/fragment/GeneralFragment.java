package org.moziqi.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.moziqi.activity.AHomeActivity;

import java.io.Serializable;
import java.util.Objects;

import moziqi.te.R;


public class GeneralFragment extends Fragment implements Serializable {
    private int item;//用于区分底部菜单项
    private static View main_title_RelativeLayout;//标题栏
    protected final static String key = "Bundle";//跳转值传递key的名称
    private static GeneralFragment generalFragment = null;

    public static GeneralFragment getInstance() {
        return generalFragment == null ? generalFragment = new GeneralFragment() : generalFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (getArguments().containsKey(AHomeActivity.Item)) ;
            item = getArguments().getInt(AHomeActivity.Item);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general, container, false);
        GeneralFragment fragment = null;
        switch (item) {
            case R.id.fragment_bottom_home://初始化主页
                fragment = VHomeFragment.getInstance();
                break;
            case R.id.fragment_bottom_order://订单
                fragment = VWriteFragment.getInstance();
                break;
            case R.id.fragment_bottom_msg://公告
                fragment = VMsgFragment.getInstance();
                break;
            case R.id.fragment_bottom_more://更多
                fragment = VMoreFragment.getInstance();
                break;
            default:
                break;
        }
        if (fragment != null) {
            //更换mainView中间的内容（home,msg,at,more）
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.general_fragment, fragment)
                    .commit();
        }
        main_title_RelativeLayout = ((View) container.getParent()).findViewById(R.id.main_title_RelativeLayout);
        //将生成的view返回
        return view;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    protected void setTitle(String title) {
        if (main_title_RelativeLayout != null) {
            TextView mTextView = (TextView) main_title_RelativeLayout.findViewById(R.id.main_title_TextView);
            if (mTextView != null) {
                mTextView.setText(title);
            }
        }
    }

    /**
     * 页面跳转值传递
     *
     * @param objects
     */
    protected void setBundle(Object... objects) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(key, objects);
        GeneralFragment generalFragment = new GeneralFragment();
        generalFragment.setArguments(arguments);
    }

    /**
     * 获取所传递的值
     *
     * @return
     */
    protected Object[] getBundle() {
        if (getArguments() != null) {
            if (getArguments().containsKey(key)) {
                Object[] objects = (Object[]) getArguments().getSerializable(key);
                return objects;
            }
        }
        return null;
//        return (getArguments() != null && getArguments().containsKey(key)) ? (Object[]) getArguments().getSerializable(key) : null;
    }

    /**
     * 无参页面跳转
     *
     * @param generalFragment
     */
    protected void toIntent(GeneralFragment generalFragment) {
        if (generalFragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.general_fragment, generalFragment)
                    .commit();
        }
    }
}
