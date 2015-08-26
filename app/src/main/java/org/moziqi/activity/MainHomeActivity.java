package org.moziqi.activity;

import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.moziqi.fragment.BlankHome2Fragment;
import org.moziqi.fragment.BlankHome3Fragment;
import org.moziqi.fragment.BlankHomeFragment;
import org.moziqi.ui.PopupWindow.MainMenuPopupWindow;
import org.moziqi.ui.PopupWindow.SelectPicPopupWindow;
import org.moziqi.ui.PopupWindow.entity.MainMenu;
import org.moziqi.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import moziqi.te.R;

public class MainHomeActivity extends GeneralHave2BtnActivity implements
        RadioGroup.OnCheckedChangeListener,
        GeneralHave2BtnActivity.TopListener,
        SelectPicPopupWindow.SelectListener,
        MainMenuPopupWindow.MainMenuItemListener {

    private BlankHomeFragment mBlankHomeFragment;
    private BlankHome2Fragment mBlankHome2Fragment;
    private BlankHome3Fragment mBlankHome3Fragment;
    private FragmentManager supportFragmentManager;
    private SelectPicPopupWindow mSelectPicPopupWindow;
    private MainMenuPopupWindow mainMenuPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        supportFragmentManager = getSupportFragmentManager();
        ((RadioGroup) findViewById(R.id.rg_home)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.rb_one)).setChecked(true);
        setFragmentSelect(R.id.rb_one);
        setTopListener(this);
        mSelectPicPopupWindow = new SelectPicPopupWindow(this);
        mSelectPicPopupWindow.setSelectListener(this);
        List<MainMenu> mainMenus = getMainMenus();
        mainMenuPopupWindow = new MainMenuPopupWindow(this, mainMenus);
        mainMenuPopupWindow.setListener(this);
    }

    private List<MainMenu> getMainMenus() {
        //设置菜单内容
        List<MainMenu> mainMenus = new ArrayList<MainMenu>();
        MainMenu mainMenu1 = new MainMenu();
        mainMenu1.setContent("设置菜单内容1");
        mainMenu1.setImgId(R.drawable.ic_action_accept);
        mainMenus.add(mainMenu1);
        MainMenu mainMenu2 = new MainMenu();
        mainMenu2.setContent("设置菜单内容2");
        mainMenu2.setImgId(R.drawable.ic_action_accounts);
        mainMenus.add(mainMenu2);
        MainMenu mainMenu3 = new MainMenu();
        mainMenu3.setContent("设置菜单内容3");
        mainMenu3.setImgId(R.drawable.ic_action_person);
        mainMenus.add(mainMenu3);
        MainMenu mainMenu4 = new MainMenu();
        mainMenu4.setContent("设置菜单内容4");
        mainMenu4.setImgId(R.drawable.ic_action_accept);
        mainMenus.add(mainMenu4);
        MainMenu mainMenu5 = new MainMenu();
        mainMenu5.setContent("设置菜单内容5");
        mainMenu5.setImgId(R.drawable.ic_action_accept);
        mainMenus.add(mainMenu5);
        return mainMenus;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
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
        if (mainMenuPopupWindow.isShowing()) {
            mainMenuPopupWindow.dismiss();
        } else {
            mainMenuPopupWindow.showAsDropDown(this.findViewById(R.id.top_right_btn));
        }

    }

    @Override
    public void setTopLeftBtnListener() {
        if(mSelectPicPopupWindow.isShowing()){
            mSelectPicPopupWindow.dismiss();
        }else{
            // 获取屏幕密度（方法1）
            int screenWidth  = getWindowManager().getDefaultDisplay().getWidth();       // 屏幕宽（像素，如：480px）
            int screenHeight = getWindowManager().getDefaultDisplay().getHeight();      // 屏幕高（像素，如：800p）
            LogUtils.e(screenWidth+"-"+screenHeight);
            Rect frame = new Rect();//创建一个空的矩形对象
            getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);//获得顶层窗口的装饰视图，即状态栏，然后把状态栏显示的框架填充给刚刚我们创建的矩形对象，再通过矩形对象来获取状态栏高度
            int statusBarHeight = frame.height();// 获取状态栏高度：frame.top
            int statusBarWidth = frame.width();
            LogUtils.e(statusBarHeight + "---"+statusBarWidth);//打印出来的值为：38，即状态栏高度为38px
            View v = getWindow().findViewById(Window.ID_ANDROID_CONTENT);// /获得根视图，
            int allHeight = v.getTop();// 状态栏和标题栏的总高度
            LogUtils.e(allHeight + "");//打印出来的值为：38，即状态栏高度为38px
            mSelectPicPopupWindow.showAtLocation(this.findViewById(R.id.ll_main_home), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, statusBarWidth, statusBarHeight);
        }
    }

    @Override
    public void takePhotoListener() {

    }

    @Override
    public void pickPhotoListener() {

    }

    @Override
    public void onItemListener(AdapterView<?> parent, View view, int position, long id) {
        showShortToast(position + "");
    }
}
