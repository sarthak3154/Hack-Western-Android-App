package hackwestern.hack.com.hackwestern.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import hackwestern.hack.com.hackwestern.R;
import hackwestern.hack.com.hackwestern.utils.Utils;

/**
 * Created by Sarthak on 18-11-2017
 */

public class AppButton extends AppCompatButton {

    public AppButton(Context context) {
        super(context);
    }

    public AppButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context, attrs);
    }

    public AppButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context, attrs);
    }

    private void setFont(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs,
                    R.styleable.customView,
                    0, 0);
            try {
                int textStyleIndex = typedArray.getInt(R.styleable.customView_textStyle, 0);
                setTextFontStyle(context, textStyleIndex);
            } finally {
                typedArray.recycle();
            }
        }
    }

    private void setTextFontStyle(Context context, int textStyleIndex) {
        setTypeface(Utils.getThisTypeFace(context, textStyleIndex));
    }
}
