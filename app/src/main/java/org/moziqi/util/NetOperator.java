package org.moziqi.util;

/**
 * Created by moziqi on 15-8-7.
 */
public class NetOperator {
    public void operator() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
