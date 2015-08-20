package org.moziqi.blow.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import org.moziqi.blow.util.Parameter;

/**
 * Created by moziqi on 15-7-30.
 */
public class RecordHandler extends Handler {
    private String text;
    private View view;

    public RecordHandler(String text, View view) {
        this.text = text;
        this.view = view;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        ((TextView) view).setText(text);
        Parameter.isBlow = false;
    }
}
