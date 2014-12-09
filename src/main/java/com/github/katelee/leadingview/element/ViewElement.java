package com.github.katelee.leadingview.element;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created with IntelliJ IDEA.
 * User: Kate
 * Date: 2014/12/9
 * Time: 下午4:38
 */
public class ViewElement implements LeadingElement {
    private final View view;

    public ViewElement(View view) {
        this.view = view;
    }

    @Override
    public Rect getRect(ViewGroup viewGroup) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        int[] locationVg = new int[2];
        viewGroup.getLocationInWindow(locationVg);
        return new Rect(location[0] - locationVg[0], location[1] - locationVg[1], location[0] + view.getWidth()  - locationVg[0], location[1] + view.getHeight() - locationVg[1]);
    }
}
