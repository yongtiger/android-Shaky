package cc.brainbook.android.shaky;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;

class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private final Shaky shaky;

    LifecycleCallbacks(@NonNull Shaky shaky) {
        this.shaky = shaky;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}

    @Override
    public void onActivityStarted(Activity activity) {}

    @Override
    public void onActivityResumed(Activity activity) {
        shaky.setActivity(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        shaky.setActivity(null);
    }

    @Override
    public void onActivityStopped(Activity activity) {}

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

    @Override
    public void onActivityDestroyed(Activity activity) {}
}
