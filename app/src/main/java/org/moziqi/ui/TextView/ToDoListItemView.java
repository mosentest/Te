package org.moziqi.ui.TextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import moziqi.te.R;

/**
 * Created by moziqi on 15-8-4.
 */
public class ToDoListItemView extends TextView {
    private int paperColor;
    private float margin;
    private Paint linePaint;
    private Paint marginPaint;

    public ToDoListItemView(Context context) {
        super(context);
        init();
    }

    public ToDoListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ToDoListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        marginPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        marginPaint.setColor(getColor(R.color.cyan));
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(getColor(R.color.bisque));
        paperColor = getColor(R.color.blue);
        margin = getResources().getDimension(R.dimen.notepad_margin);
    }

    private int getColor(int id) {
        return getResources().getColor(id);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(paperColor);
        canvas.drawLine(0, 0, 0, getMeasuredHeight(), linePaint);
        canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), linePaint);
        canvas.drawLine(margin, 0, margin, getMeasuredHeight(), marginPaint);
        canvas.save();
        canvas.translate(margin, 0);
        super.onDraw(canvas);
        canvas.restore();

    }
}
