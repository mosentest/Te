package org.moziqi.util;

import android.util.Log;

/**
 * Created by moziqi on 15-7-23.
 * 总处理异常接口
 */
public class DefaultException extends RuntimeException {

    public DefaultException(String detailMessage) {
        super(detailMessage);
        LogUtils.e(detailMessage);
    }

    public DefaultException(Throwable throwable) {
        super(throwable);
        LogUtils.e(throwable.getMessage());
    }
}
