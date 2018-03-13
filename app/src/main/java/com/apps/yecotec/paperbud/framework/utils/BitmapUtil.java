package com.apps.yecotec.paperbud.framework.utils;

/**
 * Created by kenruizinoue on 12/4/17.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;

public class BitmapUtil {

    public static Bitmap getBitmapFromDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof VectorDrawable) {
            return getBitmapFromVectorDrawable((VectorDrawable) drawable);
        } else {
            throw new IllegalArgumentException("Unable to convert to bitmap");
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap getBitmapFromVectorDrawable(VectorDrawable vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

}