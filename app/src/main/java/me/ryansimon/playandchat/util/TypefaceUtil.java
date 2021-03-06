package me.ryansimon.playandchat.util;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryan Simon
 */
public class TypefaceUtil {
    private static final Map<String, Typeface> TYPEFACES = new HashMap<String, Typeface>(4);
    private static String FONTS_DIR = "fonts/";

    public static void setFontsDirectory(String directory) {
        if (null != directory) {
            FONTS_DIR = directory;
            if (!FONTS_DIR.endsWith("/")) {
                FONTS_DIR += "/";
            }
        } else {
            FONTS_DIR = "";
        }
    }

    public static Typeface getTypeface(final String name, final View view) {
        return getTypeface(name, view.getContext());
    }

    public static Typeface getTypeface(final String name, final Context context) {
        Typeface typeface = TYPEFACES.get(name);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), FONTS_DIR + name);
            TYPEFACES.put(name, typeface);
        }
        return typeface;
    }

    public static <V extends TextView> V setTypeface(final String name, final V view) {
        if (view != null) {
            view.setTypeface(getTypeface(name, view));
        }
        return view;
    }

    public static void setTypeface(final String name, final TextView... views) {
        if (views == null || views.length == 0) {
            return;
        }

        Typeface typeface = getTypeface(name, views[0]);
        for (TextView view : views) {
            view.setTypeface(typeface);
        }
    }
}
