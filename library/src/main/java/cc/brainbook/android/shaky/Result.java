package cc.brainbook.android.shaky;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * Wrapper class for the data collected.
 */
public class Result {
    private static final String PREFIX = Result.class.getName();
    private static final String MESSAGE = PREFIX + ".message";
    private static final String TITLE = PREFIX + ".title";
    private static final String SCREENSHOT_URI = PREFIX + ".screenshotUri";
    private static final String ATTACHMENTS = PREFIX + ".attachments";
    private static final String SUBCATEGORY = PREFIX + ".subcategory";

    private final Bundle data;
    private ArrayList<Uri> attachments;

    Result(@NonNull Bundle data) {
        this.data = data;
    }

    Result() {
        this.data = new Bundle();
    }

    @Nullable
    public String getMessage() {
        return data.getString(MESSAGE);
    }

    void setMessage(@Nullable String message) {
        data.putString(MESSAGE, message);
    }

    @Nullable
    public String getTitle() {
        return data.getString(TITLE);
    }

    void setTitle(@Nullable String title) {
        data.putString(TITLE, title);
    }

    @Nullable
    public Uri getScreenshotUri() {
        return (Uri) data.getParcelable(SCREENSHOT_URI);
    }

    void setScreenshotUri(@Nullable Uri screenshotUri) {
        if (getScreenshotUri() == null && screenshotUri != null) {
            getAttachments().add(screenshotUri);
        }
        data.putParcelable(SCREENSHOT_URI, screenshotUri);
    }

    /**
     * @return the List of attachments. Changes to this array will be automatically preserved in the bundle.
     */
    @NonNull
    public ArrayList<Uri> getAttachments() {
        if (this.attachments == null) {
            ArrayList<Uri> attachments = data.getParcelableArrayList(ATTACHMENTS);
            if (attachments == null) {
                attachments = new ArrayList<>();
            }
            this.attachments = attachments;
        }
        return this.attachments;
    }

    void setAttachments(ArrayList<Uri> attachments) {
        this.attachments = attachments;
    }

    @Nullable
    public String getSubcategory() {
        return data.getString(SUBCATEGORY);
    }

    void setSubcategory(@Nullable String subcategory) {
        data.putString(SUBCATEGORY, subcategory);
    }

    /**
     * Attach all other app-related data to the Bundle. This will be saved and restored automatically.
     * @return the data received from {@link cc.brainbook.android.shaky.ShakeDelegate#collectData} and {@link cc.brainbook.android.shaky.ShakeDelegate#collectBackgroundData}.
     */
    @NonNull
    public Bundle getData() {
        // Just-In-Time attachments (so modifications to the attachments are preserved)
        if (attachments != null) {
            data.putParcelableArrayList(ATTACHMENTS, attachments);
        }
        return data;
    }
}
