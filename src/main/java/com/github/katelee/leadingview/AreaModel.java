package com.github.katelee.leadingview;

import android.graphics.Color;
import com.github.katelee.leadingview.element.LeadingElement;

/**
 * Created with IntelliJ IDEA.
 * User: Kate
 * Date: 2014/12/9
 * Time: 下午2:01
 */
public class AreaModel {
    public enum ShapeType {
        RECTANGLE,
        ROUND_RECTANGLE,
        CIRCLE
    }

    protected ShapeType shapeType;
//    protected int left;
//    protected int top;
//    protected int right;
//    protected int bottom;
    protected float scaleX;
    protected float scaleY;
    protected int background_rId;
    protected int colorMask;
//    protected int side_rId;
    protected LeadingElement leadingElement;

    public static AreaModel getRectangleModel(LeadingElement leadingElement)
    {
        return getRectangleModel(leadingElement, 1, 1);
    }

    public static AreaModel getRectangleModel(LeadingElement leadingElement, float scaleX, float scaleY)
    {
        AreaModel areaModel = new AreaModel();
//        areaModel.left = location[0];
//        areaModel.top = location[1];
//        areaModel.right = location[0] + view.getWidth();
//        areaModel.bottom = location[1] + view.getHeight();
        areaModel.leadingElement = leadingElement;
        areaModel.shapeType = ShapeType.RECTANGLE;
        areaModel.scaleX = scaleX;
        areaModel.scaleY = scaleY;
        areaModel.background_rId = R.color.transparent;
        areaModel.colorMask = Color.TRANSPARENT;
        return areaModel;
    }
}
