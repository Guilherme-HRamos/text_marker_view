package br.vince.textmarker;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;


class TextMarkerBase extends FrameLayout {

    TextMarkerView.OnAnimationViewListener mViewListener;
    TextMarkerView.OnClickListenerInterception mClickListenerInterception;

    AppCompatTextView mTextView;
    MaterialCardView mMarker;

    boolean isChecked;

    String tag;
    boolean isAnimationEnabled;
    long animDuration;
    TimeInterpolator mInterpolator = new OvershootInterpolator(4);

    private int markerWidth = 0;

    List<TextMarkerView> mAnimateGroup = new ArrayList<>();
    boolean isLikeRadioButton = false;

    TextMarkerBase(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.TextMarkerBase, 0, 0);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_text_marker, this, true);

        mMarker = (MaterialCardView) ((FrameLayout) getChildAt(0)).getChildAt(0);
        mTextView = (AppCompatTextView) ((FrameLayout) getChildAt(0)).getChildAt(1);

        config(a);

        init();

    }

    private void setChecked() {
        isChecked = false;
    }

    void setCheckedValue(boolean check) {
        isChecked = !check;

        startAnimation();
    }

    private void config(final TypedArray a) {
        mTextView.setText(a.getString(R.styleable.TextMarkerBase_setTitle));
        mTextView.setTextSize(a.getDimension(R.styleable.TextMarkerBase_setTextSize,
                getResources().getDimension(R.dimen.defaultSize)));
        mTextView.setTextAppearance(getContext(), a.getResourceId(R.styleable.TextMarkerBase_setTextAppearence,
                (android.R.style.TextAppearance_Holo_Small)));
        mTextView.setTextColor(a.getColor(R.styleable.TextMarkerBase_setTextColor,
                getResources().getColor(android.R.color.black)));

        mMarker.setCardBackgroundColor(a.getColor(R.styleable.TextMarkerBase_setMarkerColor,
                getResources().getColor(android.R.color.white)));
        mMarker.setCardElevation(a.getDimension(R.styleable.TextMarkerBase_setTextSize,
                getResources().getDimension(R.dimen.defaultElevation)));

        getChildAt(0).setBackgroundResource(a.getResourceId(R.styleable.TextMarkerBase_setBackground,
                getResources().getColor(android.R.color.transparent)));

        tag = (a.getString(R.styleable.TextMarkerBase_setTag));
        isChecked = a.getBoolean(R.styleable.TextMarkerBase_setChecked, false);
        isAnimationEnabled = a.getBoolean(R.styleable.TextMarkerBase_setEnableAnimation, false);
        animDuration = a.getInt(R.styleable.TextMarkerBase_setAnimDuration, 250);

        a.recycle();

    }

    private void init() {
        View mView = getChildAt(0);

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isChecked && isLikeRadioButton && isAnimationEnabled)
                    return;

                if (mClickListenerInterception != null)
                    mClickListenerInterception.onClick((TextMarkerView) TextMarkerBase.this);
                startAnimation();
            }
        });

        mMarker.setVisibility(VISIBLE);

        mMarker.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                mMarker.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                mMarker.setRadius(mMarker.getHeight() / 2);

                markerWidth = mMarker.getWidth();

                if (isChecked)
                    mTextView.setTranslationX(markerWidth);
                else {
                    mTextView.setTranslationX(0);
                    mMarker.setVisibility(GONE);
                }
            }
        });

    }

    void startAnimation() {

        if (isChecked) {
            hideMarker();
        } else {
            showMarker();
        }

        isChecked = !isChecked;
    }

    private void showMarker() {
        if (!isAnimationEnabled) {
            if (mViewListener != null)
                mViewListener.whenShow(tag);
            return;
        }

        if (isLikeRadioButton)
            disableOtherObjects();

        mMarker.setTranslationX(-markerWidth);

        mMarker.setVisibility(VISIBLE);

        if (mViewListener != null)
            mViewListener.beforeShow(tag);

        translationInAnimation(mMarker, 0);
        translationInAnimation(mTextView, markerWidth);

    }

    private void disableOtherObjects() {
        for (TextMarkerBase animateTextView : mAnimateGroup) {
            if (animateTextView != null && animateTextView.isChecked) {
                animateTextView.hideMarker();
                animateTextView.setChecked();
            }
        }
    }

    private void hideMarker() {
        if (!isAnimationEnabled) {
            if (mViewListener != null)
                mViewListener.whenHide(tag);
            return;
        }


        mMarker.setTranslationX(0);

        if (mViewListener != null)
            mViewListener.beforeHide(tag);

        translationOutAnimation(mMarker, -markerWidth);
        translationOutAnimation(mTextView, 0);


    }

    private void translationInAnimation(final View v, final float position) {
        v.animate().translationX(position).setDuration(animDuration).setInterpolator(mInterpolator)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        if (mViewListener != null && v instanceof AppCompatTextView)
                            mViewListener.whenShow(tag);
                    }
                });
    }

    private void translationOutAnimation(final View v, final float position) {
        v.animate().translationX(position).setDuration(animDuration).setInterpolator(mInterpolator)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        mMarker.setVisibility(GONE);
                        if (mViewListener != null && v instanceof AppCompatTextView)
                            mViewListener.whenHide(tag);
                    }
                });
    }

    void setGroup(List<TextMarkerView> items) {
        mAnimateGroup = items;
    }

    void setType(TextMarkerGroup.AnimateType type) {
        switch (type) {
            case CHECKBOX:
                isLikeRadioButton = false;
                break;
            case RADIOBUTTON:
                isLikeRadioButton = true;

                if (mAnimateGroup.isEmpty())
                    return;

                int enableds = 0;

                for (TextMarkerView textMarkerView : mAnimateGroup) {
                    if (textMarkerView.isChecked)
                        enableds++;
                }

                if (enableds == 0)
                    mAnimateGroup.get(0).startAnimation();
                else if (enableds > 1) {
                    for (int i = 0; i < mAnimateGroup.size(); i++) {
                        if (mAnimateGroup.get(i).isChecked)
                            mAnimateGroup.get(i).startAnimation();
                    }
                    mAnimateGroup.get(0).startAnimation();
                }

                break;
        }
    }

}
