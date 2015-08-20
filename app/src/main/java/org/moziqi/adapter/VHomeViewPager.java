package org.moziqi.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * 后期参考http://blog.sina.com.cn/s/blog_881875e70101m648.html
 * Created by moziqi on 15-7-27.
 */
public class VHomeViewPager extends PagerAdapter {
    private View[] views;

    private String[] titles;

    public VHomeViewPager() {
    }

    public VHomeViewPager(View[] views) {
        super();
        this.views = views;
    }

    public VHomeViewPager(String[] titles, View[] views) {
        this.titles = titles;
        this.views = views;
    }

    /**
     * 设置标题
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return views.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views[position]);
        return views[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views[position]);
    }
}
