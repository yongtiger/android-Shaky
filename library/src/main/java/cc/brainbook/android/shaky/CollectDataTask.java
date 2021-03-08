package cc.brainbook.android.shaky;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;

import java.io.File;

/**
 * Background task to collect user data. Used with {@link CollectDataDialog}.
 */
class CollectDataTask extends AsyncTask<Bitmap, Void, Result> {

    private static final String TAG = CollectDataTask.class.getSimpleName();

    private static final String SCREENSHOT_DIRECTORY = "/screenshots";

    private final Activity activity;
    private final ShakeDelegate delegate;
    private final Callback callback;

    CollectDataTask(@NonNull Activity activity,
                    @NonNull ShakeDelegate delegate,
                    @NonNull Callback callback) {
        this.activity = activity;
        this.delegate = delegate;
        this.callback = callback;
    }

    @Override
    protected Result doInBackground(Bitmap... params) {
        String screenshotDirectoryRoot = getScreenshotDirectoryRoot(activity);
        if (screenshotDirectoryRoot == null) {
            return null;
        }

        // delete any old screenshots that we may have left lying around
        File screenshotDirectory = new File(screenshotDirectoryRoot);
        if (screenshotDirectory.exists()) {
            File[] oldScreenshots = screenshotDirectory.listFiles();
            for (File oldScreenshot : oldScreenshots) {
                if (!oldScreenshot.delete()) {
                    Log.e(TAG, "Could not delete old screenshot:" + oldScreenshot);
                }
            }
        }

        Uri screenshotUri = null;

        Bitmap bitmap = params != null && params.length != 0 ? params[0] : null;
        if (bitmap != null) {
            File screenshotFile = Utils.writeBitmapToDirectory(bitmap, screenshotDirectory);
            screenshotUri = Uri.fromFile(screenshotFile);
        }

        Result result = new Result();
        result.setScreenshotUri(screenshotUri);
        delegate.collectData(activity, result);
        return result;
    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);

        callback.onDataReady(result);
    }

    @Nullable
    @WorkerThread
    private static String getScreenshotDirectoryRoot(@NonNull Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir == null) {
            return null;
        }
        return filesDir.getAbsolutePath() + SCREENSHOT_DIRECTORY;
    }

    interface Callback {
        void onDataReady(@Nullable Result result);
    }
}
