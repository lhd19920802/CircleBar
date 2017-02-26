package com.atguigu.circlebar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Li Huaidong on 2017/2/26 16;//30.
 * 作用：自定义圆形进度条
 */
public class CircleBar extends View
{
    private int progress;// 当前进度
    private int max=100;// 最大进度
    private int roundColor = Color.GREEN;// 圆环的颜色
    private int roundProgressColor = Color.BLUE;// 圆环进度的颜色
    private int roundWidth = 10;// 圆环的宽度
    private int textSize = 20;// 文字的大小
    private int textColor = Color.RED;// 文字的颜色

    private float viewWidth;//视图的宽高
    private Paint paint;


    public CircleBar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        paint = new Paint();
        //抗锯齿
        paint.setAntiAlias(true);
    }

    public int getProgress()
    {
        return progress;
    }

    public void setProgress(int progress)
    {
        this.progress = progress;
        postInvalidate();
    }

    public int getMax()
    {
        return max;
    }

    public void setMax(int max)
    {
        this.max = max;
    }

    public int getRoundColor()
    {
        return roundColor;
    }

    public void setRoundColor(int roundColor)
    {
        this.roundColor = roundColor;
    }

    public int getRoundProgressColor()
    {
        return roundProgressColor;
    }

    public void setRoundProgressColor(int roundProgressColor)
    {
        this.roundProgressColor = roundProgressColor;
    }

    public int getRoundWidth()
    {
        return roundWidth;
    }

    public void setRoundWidth(int roundWidth)
    {
        this.roundWidth = roundWidth;
    }

    public int getTextSize()
    {
        return textSize;
    }

    public void setTextSize(int textSize)
    {
        this.textSize = textSize;
    }

    public int getTextColor()
    {
        return textColor;
    }

    public void setTextColor(int textColor)
    {
        this.textColor = textColor;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        //绘制圆环
        float cx = viewWidth / 2;
        paint.setColor(roundColor);
        paint.setStrokeWidth(roundWidth);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(cx, cx, cx - roundWidth / 2, paint);
        //绘制圆弧
        RectF oval = new RectF(roundWidth / 2, roundWidth / 2, viewWidth - roundWidth / 2,
                viewWidth - roundWidth / 2);
        paint.setColor(roundProgressColor);
        canvas.drawArc(oval, 0, progress * 360 / max, false, paint);
        //绘制文本
        String text = progress * 100 / max + "%";
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        float textWidth = bounds.width();
        float textHeight = bounds.height();
        float textX = cx - textWidth / 2;
        float textY = cx + textHeight / 2;
        paint.setColor(textColor);
        paint.setStrokeWidth(0);
        paint.setTextSize(textSize);
        canvas.drawText(text, textX, textY, paint);
    }
}
