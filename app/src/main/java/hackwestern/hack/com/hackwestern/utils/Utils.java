package hackwestern.hack.com.hackwestern.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Sarthak on 18-11-2017
 */
public class Utils {
    public static Typeface getThisTypeFace(Context context, int type) {
        Typeface typeface;
        switch (type) {

            case AppConstants.OPEN_SANS_REGULAR:
                typeface = FontCache.getFont(context, "fonts/OpenSans-Regular.ttf");
                break;
            case AppConstants.PT_SANS_WEB_REGULAR:
                typeface = FontCache.getFont(context, "fonts/PT_Sans-Web-Regular.ttf");
                break;
            default:
                typeface = FontCache.getFont(context, "fonts/OpenSans-Regular.ttf");
                break;
        }
        return typeface;
    }
}
