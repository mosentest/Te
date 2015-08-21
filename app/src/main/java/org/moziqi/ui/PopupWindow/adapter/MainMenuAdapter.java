package org.moziqi.ui.PopupWindow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.moziqi.ui.PopupWindow.entity.MainMenu;
import org.moziqi.util.DefaultException;

import java.util.List;

import moziqi.te.R;

/**
 * Created by moziqi on 15-8-20.
 */
public class MainMenuAdapter extends BaseAdapter {

    private Context context;
    private List<MainMenu> mainMenu;

    public MainMenuAdapter(Context context, List<MainMenu> mainMenu) {
        if (context == null) {
            throw new DefaultException("context is null in MainMenuAdapter");
        }
        if (mainMenu == null) {
            throw new DefaultException("mainMenu is null in MainMenuAdapter");
        }
        this.context = context;
        this.mainMenu = mainMenu;
    }

    @Override
    public int getCount() {
        return mainMenu.size();
    }

    @Override
    public Object getItem(int position) {
        return mainMenu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_main_menu_item, null);
            viewHolder = new ViewHolder();
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.iv_main_menu_item);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_main_menu_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置图片，异步加载呢？
        viewHolder.mImageView.setImageResource(mainMenu.get(position).getImgId());
        viewHolder.mTextView.setText(mainMenu.get(position).getContent());
        return convertView;
    }

    public void updateUI(List<MainMenu> mainMenu) {
        this.mainMenu = mainMenu;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        ImageView mImageView;
        TextView mTextView;
    }
}
