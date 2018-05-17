package com.zyh.guidemanager.uitls;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Utils
 * <p>
 * Created by Oscar-Zhang on 2018/5/17.
 */
public class DisplayUtils {
    /**
     * dp to px
     * @param value dp
     * @return px
     */
    public static int dp(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, Resources.getSystem().getDisplayMetrics());
    }
}
