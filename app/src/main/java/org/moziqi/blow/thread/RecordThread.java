package org.moziqi.blow.thread;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Message;

import org.moziqi.blow.handler.RecordHandler;
import org.moziqi.blow.util.Parameter;
import org.moziqi.fragment.VHomeFragment;

/**
 * Created by moziqi on 15-7-30.
 */
public class RecordThread extends Thread {
    private AudioRecord audioRecord;
    private int bs = 100;
    private static int SAMPLE_RATE_IN_HZ = 8000;
    private Message msg;
    private int number = 1;
    private int tal = 1;
    private RecordHandler handler;
    private long time = 1;
    //到达该值之后 触发时间
    private static int BLOW_ACTIVITY = 3000;

    public RecordThread(RecordHandler mRecordHandler) {
        super();
        bs = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT);
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
                SAMPLE_RATE_IN_HZ,
                AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT, bs);
        handler = mRecordHandler;
    }

    @Override
    public void run() {
        super.run();
        try {
            audioRecord.startRecording();
            Parameter.isBlow = true;
            //用于读取的buffer
            byte[] buffer = new byte[bs];
            while (Parameter.isBlow) {
                number++;
                sleep(8);
                long currentTime = System.currentTimeMillis();
                int r = audioRecord.read(buffer, 0, bs) + 1;
                int v = 0;
                for (int i = 0; i < buffer.length; i++) {
                    v += (buffer[i] * buffer[i]);
                }
                int value = Integer.valueOf(v / (int) r);
                tal = tal + value;
                long endTime = System.currentTimeMillis();
                time = time + (endTime - currentTime);
                if (time >= 500 || number > 5) {
                    int total = tal / number;
                    if (total > BLOW_ACTIVITY) {
                        //发送消息通知到界面 触发动画

                        //利用传入的handler 给界面发送通知
                        VHomeFragment.i += 1;
                        handler.sendEmptyMessage(0); //改变i的值后，发送一个空message到主线程
                        //
                        number = 1;
                        tal = 1;
                        time = 1;
                    }
                }
            }
            audioRecord.stop();
            audioRecord.release();
            bs = 100;
        } catch (Exception e) {

        }
    }
}
