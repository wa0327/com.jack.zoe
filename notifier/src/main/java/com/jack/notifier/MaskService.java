package com.jack.notifier;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.jack.notifier.util.J;

public class MaskService extends Service {
    private static final String TAG = MaskService.class.getSimpleName();

    public static boolean needRestart = true;
    public static MaskView maskView;

    @Override
    public void onCreate() {
        J.d(TAG, "onCreate");
        if (maskView == null) {
            maskView = new MaskView(this);
        }

        maskView.show();
        J.i(TAG, "service [%s] created", this.getClass().getName());
    }

    @Override
    public void onDestroy() {
        J.d(TAG, "onDestroy");
        maskView.hide();

        if (needRestart) {
            startService(new Intent(this, MaskService.class));
        }

        J.i(TAG, "service [%s] destroyed", this.getClass().getName());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
