package org.moziqi.ui.PopupWindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
        final View mMenuView = layoutInflater.inflate(R.layout.view_select_pic_popup_window, null);
        mMenuView.findViewById(R.id.btn_take_photo).setOnClickListener(this);
        mMenuView.findViewById(R.id.btn_pick_photo).setOnClickListener(this);
        mMenuView.findViewById(R.id.btn_cancel).setOnClickListener(this);
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.ll_top).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置允许在外点击消失(包括back键)
        this.setOutsideTouchable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
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

    public interface SelectListener {
        public void takePhotoListener();

        public void pickPhotoListener();
    }

    public void setSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }
}
