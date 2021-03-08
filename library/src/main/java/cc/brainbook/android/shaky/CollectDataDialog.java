package cc.brainbook.android.shaky;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;

/**
 * Dialog to show while the app is collecting data. Used with {@link cc.brainbook.android.shaky.CollectDataTask}.
 */
public class CollectDataDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProgressDialog dialog = new ProgressDialog(getActivity()) {
            @Override
            public void onBackPressed() {
                // don't call super, so the back button won't close the dialog
            }
        };
        dialog.setTitle(R.string.shaky_collecting_feedback);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setIndeterminate(true);
        dialog.setProgressPercentFormat(null);
        dialog.setProgressNumberFormat(null);
        return dialog;
    }
}
