package br.vince.textmarker;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public final class TextMarkerView extends TextMarkerBase {


    public TextMarkerView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAnimDuration(int duration) {
        animDuration = duration;
    }

    public void setInterpolator(TimeInterpolator interpolator) {
        mInterpolator = interpolator;
    }

    public void setOnAnimationListener(OnAnimationViewListener listener) {
        mViewListener = listener;
    }

    public void removeAnimationListener() {
        mViewListener = null;
    }

    public void setOnClickListenerInterception(OnClickListenerInterception listener) {
        mClickListenerInterception = listener;
    }

    public void removeClickListenerInterception() {
        mClickListenerInterception = null;
    }

    public void setTag(final String tag) {
        this.tag = tag;
    }

    public void setText(final String text) {
        mTextView.setText(text);
    }

    public String getText() {
        return mTextView.getText().toString();
    }

    public AppCompatTextView getTextView() {
        return mTextView;
    }

    public MaterialCardView getMarker() {
        return mMarker;

    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean check) {
        setCheckedValue(check);
    }

    public void enableAnimation(final boolean enableAnimation) {
        isAnimationEnabled = enableAnimation;
    }

    public interface OnAnimationViewListener {
        void beforeShow(@Nullable String tag);

        void whenShow(@Nullable String tag);

        void beforeHide(@Nullable String tag);

        void whenHide(@Nullable String tag);
    }

    public interface OnClickListenerInterception {
        void onClick(TextMarkerView view);
    }

}
