package org.moziqi.ui.PopupWindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import org.moziqi.ui.PopupWindow.adapter.MainMenuAdapter;
import org.moziqi.ui.PopupWindow.entity.MainMenu;

import java.util.List;

import moziqi.te.R;

/**
 * http://michaelye1988.iteye.com/blog/1766629
 * Created by moziqi on 15-8-20.
 */
public class MainMenuPopupWindow extends PopupWindow implements AdapterView.OnItemClickListener {

    private final static int NUM_OF_VISIBLE_LIST_ROWS = 3;

    private MainMenuItemListener listener;

    public MainMenuPopupWindow(Context context, List<MainMenu> mainMenus) {
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mMenuView = layoutInflater.inflate(R.layout.view_main_menu, null);
        ListView listView = (ListView) mMenuView.findViewById(R.id.lv_main_menu);
        MainMenuAdapter mainMenuAdapter = new MainMenuAdapter(context, mainMenus);
        listView.setAdapter(mainMenuAdapter);
        listView.setOnItemClickListener(this);
        // 控制MainMenuPopupWindow的宽度和高度自适应
        listView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        this.setWidth(listView.getMeasuredWidth());
        this.setHeight(listView.getMeasuredHeight() * NUM_OF_VISIBLE_LIST_ROWS);
        setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_popupwindow));// 设置背景图片，不能在布局中设置，要通过代码来设
        setOutsideTouchable(true);
        setFocusable(true);
        setContentView(mMenuView);
    }

    public void setListener(MainMenuItemListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listener != null) {
            listener.onItemListener(parent, view, position, id);
        }

    }

    public interface MainMenuItemListener {
        public void onItemListener(AdapterView<?> parent, View view, int position, long id);
    }
}
