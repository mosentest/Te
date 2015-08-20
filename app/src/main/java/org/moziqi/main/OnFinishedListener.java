package org.moziqi.main;

import org.moziqi.listener.GeneralListener;

import java.util.List;

/**
 * Created by moziqi on 15-8-12.
 */
public interface OnFinishedListener extends GeneralListener {

    public void onFinished(List<String> items);
}
