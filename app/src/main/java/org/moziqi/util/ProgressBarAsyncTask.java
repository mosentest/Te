package org.moziqi.util;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by moziqi on 15-8-7.
 */
public class ProgressBarAsyncTask extends AsyncTask<Integer, Integer, String> {

    private TextView mTextView;
    private ProgressBar mProgressBar;

    public ProgressBarAsyncTask(TextView mTextView, ProgressBar mProgressBar) {
        super();
        this.mTextView = mTextView;
        this.mProgressBar = mProgressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mTextView.setText("开始执行异步线程");
    }

    @Override
    protected String doInBackground(Integer... params) {
        NetOperator netOperator = new NetOperator();
        int i = 0;
        for (i = 10; i <= 100; i += 10) {
            netOperator.operator();
            publishProgress(i);
        }
        return i + params[0].intValue() + "";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int value = values[0];
        mProgressBar.setProgress(value);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mTextView.setText("结束 - " + s);
    }

}
