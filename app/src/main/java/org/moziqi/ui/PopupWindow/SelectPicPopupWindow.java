package org.moziqi.ui.PopupWindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import moziqi.te.R;

/**
 * Created by moziqi on 15-8-19.
 */
public class SelectPicPopupWindow extends PopupWindow implements View.OnClickListener {

    private SelectListener selectListener;

    public SelectPicPopupWindow(Context context) {
        super(context);
        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mMenuView = layoutInflater.inflate(R.layout.view_select_pic_popup_window, null);
        mMenuView.findViewById(R.id.btn_take_photo).setOnClickListener(this);
        mMenuView.findViewById(R.id.btn_pick_photo).setOnClickListener(this);
        mMenuView.findViewById(R.id.btn_cancel).setOnClickListener(this);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_take_photo:
                if (selectListener != null) {
                    selectListener.takePhotoListener();
                }
                break;
            case R.id.btn_pick_photo:
                if (selectListener != null) {
                    selectListener.pickPhotoListener();
                }
                break;
            case R.id.btn_cancel:
                dismiss();
        }
    }

    interface SelectListener {
        public void takePhotoListener();

        public void pickPhotoListener();
    }

    public void setSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }
}
