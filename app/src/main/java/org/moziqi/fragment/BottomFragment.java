package org.moziqi.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.moziqi.util.DefaultException;

import moziqi.te.R;

public class BottomFragment extends Fragment {


    //默认回调接口实现类的对象
    private CallBacks callBacks = defaultCallBacks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RadioGroup radioGroup = (RadioGroup) inflater.inflate(R.layout.fragment_bottom, container, false);
        //绑定监听器
        radioGroup.setOnCheckedChangeListener(checkedChangeListener);
        return radioGroup;
    }


    /**
     * RadioGroup监听器
     */
    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            callBacks.onItemSelected(checkedId); //调用接口中方法
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof CallBacks)) {
            throw new DefaultException("Activity must implements fragment's callbacks !");
        }
        callBacks = (CallBacks) activity;
    }

    public interface CallBacks {
        /**
         * 导航栏回调接口
         *
         * @param item
         */
        public void onItemSelected(int item);
    }

    /**
     * 默认回调实现类的对象
     */
    private static CallBacks defaultCallBacks = new CallBacks() {
        @Override
        public void onItemSelected(int item) {
            //
        }
    };

    /**
     * Fragment和Activity解除关联的时候调用
     */
    @Override
    public void onDetach() {
        super.onDetach();
        callBacks = defaultCallBacks;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        callBacks = null;
        defaultCallBacks = null;
    }
}
