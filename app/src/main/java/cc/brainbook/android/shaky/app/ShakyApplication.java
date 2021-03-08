package cc.brainbook.android.shaky.app;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import cc.brainbook.android.shaky.EmailShakeDelegate;
import cc.brainbook.android.shaky.Shaky;
import cc.brainbook.android.shaky.ShakyFlowCallback;

/**
 * Hello world example.
 */
public class ShakyApplication extends Application {
    private static final String TAG = ShakyApplication.class.getSimpleName();

    private Shaky shaky;
    private @StyleRes
    Integer theme = null;
    private @StyleRes
    Integer popupTheme = null;

    @Override
    public void onCreate() {
        super.onCreate();
        shaky = Shaky.with(this, new EmailShakeDelegate("hello@world.com") {
            @Override
            @Nullable
            public Integer getTheme() {
                return theme;
            }

            @Override
            @Nullable
            public Integer getPopupTheme() {
                return popupTheme;
            }

        }, new ShakyFlowCallback() {
            @Override
            public void onShakyStarted(@ShakyFlowCallback.ShakyStartedReason int reason) {
                Log.d(TAG, "onShakyStarted: " + reason);
            }

            @Override
            public void onShakyFinished(@ShakyFinishedReason int reason) {
                Log.d(TAG, "onShakyFinished: " + reason);
            }

            @Override
            public void onUserPromptShown() {
                Log.d(TAG, "onUserPromptShown");
            }

            @Override
            public void onCollectingData() {
                Log.d(TAG, "onCollectingData");
            }

            @Override
            public void onConfiguringFeedback() {
                Log.d(TAG, "onConfiguringFeedback");
            }
        });
    }

    @NonNull
    public Shaky getShaky() {
        return shaky;
    }

    public void setShakyTheme(@StyleRes Integer theme) {
        this.theme = theme;
    }

    public void setShakyPopupTheme(@StyleRes Integer theme) {
        this.popupTheme = theme;
    }
}
