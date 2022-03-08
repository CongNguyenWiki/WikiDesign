package com.skypremiuminternational.app.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.ArrayList;
import java.util.List;


public class EllipSizeTextView extends HtmlTextView {
  private static final Spanned ELLIPSIS = new SpannedString("…more");



  public interface EllipsizeListener {
    void ellipsizeStateChanged(boolean ellipsized);
  }

  private final List<EllipsizeListener> ellipsizeListeners = new ArrayList<EllipsizeListener>();
  private boolean isEllipsized;
  private boolean isStale;
  private boolean programmaticChange;
  private Spanned fullText;
  private int maxLines;
  private float lineSpacingMultiplier = 1.0f;
  private float lineAdditionalVerticalPadding = 0.0f;

  public EllipSizeTextView(Context context) {
    this(context, null);
  }

  public EllipSizeTextView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public EllipSizeTextView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    super.setEllipsize(null);
    TypedArray a = context.obtainStyledAttributes(attrs, new int[] { android.R.attr.maxLines });
    setMaxLines(a.getInt(0, Integer.MAX_VALUE));
  }

  public void addEllipsizeListener(EllipsizeListener listener) {
    if (listener == null) {
      throw new NullPointerException();
    }
    ellipsizeListeners.add(listener);
  }

  public void removeEllipsizeListener(EllipsizeListener listener) {
    ellipsizeListeners.remove(listener);
  }

  public boolean isEllipsized() {
    return isEllipsized;
  }

  @Override
  public void setMaxLines(int maxLines) {
    super.setMaxLines(maxLines);
    this.maxLines = maxLines;
    isStale = true;
  }



  public int getMaxLines() {
    return maxLines;
  }

  public boolean ellipsizingLastFullyVisibleLine() {
    return maxLines == Integer.MAX_VALUE;
  }

  @Override
  public void setLineSpacing(float add, float mult) {
    this.lineAdditionalVerticalPadding = add;
    this.lineSpacingMultiplier = mult;
    super.setLineSpacing(add, mult);
  }

  @Override
  public void setText(CharSequence text, TextView.BufferType type) {
    if (!programmaticChange && text instanceof Spanned) {
      fullText = (Spanned) text;
      isStale = true;
    }
    super.setText(text, type);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    if (ellipsizingLastFullyVisibleLine()) {
      isStale = true;
    }
  }

  public void setPadding(int left, int top, int right, int bottom) {
    super.setPadding(left, top, right, bottom);
    if (ellipsizingLastFullyVisibleLine()) {
      isStale = true;
    }
  }

  @Override
  protected void onDraw(Canvas canvas) {
    if (isStale) {
      try {
        resetText();
      } catch (NullPointerException ex){

      }
    }
    super.onDraw(canvas);
  }

  private void resetText() {
    Spanned workingText = fullText;
    boolean ellipsized = false;
    Layout layout = createWorkingLayout(workingText);
    int linesCount = getLinesCount();
    if (layout.getLineCount() > linesCount) {
      // We have more lines of text than we are allowed to display.
      workingText = (Spanned) fullText.subSequence(0, layout.getLineEnd(linesCount - 1));
      while (createWorkingLayout((Spanned) TextUtils.concat(workingText, ELLIPSIS)).getLineCount() > linesCount) {
        int lastSpace = workingText.toString().lastIndexOf(' ');
        if (lastSpace == -1) {
          break;
        }
        workingText = (Spanned) workingText.subSequence(0, lastSpace);
      }
      workingText = (Spanned) TextUtils.concat(workingText, getColoredString(getContext(),ELLIPSIS, "#CBA84A"));
      ellipsized = true;
    }
    if (!workingText.equals(getText())) {
      programmaticChange = true;
      try {
        setText(workingText);
      } finally {
        programmaticChange = false;
      }
    }
    isStale = false;
    if (ellipsized != isEllipsized) {
      isEllipsized = ellipsized;
      for (EllipsizeListener listener : ellipsizeListeners) {
        listener.ellipsizeStateChanged(ellipsized);
      }
    }
  }

  /**
   * Get how many lines of text we are allowed to display.
   */
  private int getLinesCount() {
    if (ellipsizingLastFullyVisibleLine()) {
      int fullyVisibleLinesCount = getFullyVisibleLinesCount();
      if (fullyVisibleLinesCount == -1) {
        return 1;
      } else {
        return fullyVisibleLinesCount;
      }
    } else {
      return maxLines;
    }
  }

  /**
   * Get how many lines of text we can display so their full height is visible.
   */
  private int getFullyVisibleLinesCount() {
    Layout layout = createWorkingLayout(new SpannedString(""));
    int height = getHeight() - getPaddingTop() - getPaddingBottom();
    int lineHeight = layout.getLineBottom(0);
    return height / lineHeight;
  }

  private Layout createWorkingLayout(Spanned workingText) {
    return new StaticLayout(workingText, getPaint(),
        getWidth() - getPaddingLeft() - getPaddingRight(),
        Layout.Alignment.ALIGN_NORMAL, lineSpacingMultiplier,
        lineAdditionalVerticalPadding, false /* includepad */);
  }

  @Override
  public void setEllipsize(TextUtils.TruncateAt where) {
    // Ellipsize settings are not respected
  }




  public static final Spannable getColoredString(Context context, CharSequence text, String color) {
    Spannable spannable = new SpannableString(text);
    spannable.setSpan(new ForegroundColorSpan(Color.parseColor(color)), 0, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    return spannable;
  }

}