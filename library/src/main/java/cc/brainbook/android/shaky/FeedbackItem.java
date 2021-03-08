package cc.brainbook.android.shaky;

import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Data wrapper for a single row in the select feedback type view.
 */
class FeedbackItem {
    @IntDef({
            BUG,
            FEATURE,
            GENERAL
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface FeedbackType {}

    public static final int BUG = 0;
    public static final int FEATURE = 1;
    public static final int GENERAL = 2;

    @DrawableRes public final int icon;
    public final String title;
    public final String description;
    public final int feedbackType;

    FeedbackItem(@NonNull String title, @NonNull String description, @DrawableRes int icon,
                 @FeedbackType int feedbackType) {
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.feedbackType = feedbackType;
    }
}
