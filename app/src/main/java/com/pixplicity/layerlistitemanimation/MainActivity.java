package com.pixplicity.layerlistitemanimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_activate)
    protected ImageButton mBtnActivate;

    @BindView(R.id.vg_activated)
    protected ViewGroup mVgActivated;

    private ObjectAnimator mAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_activate)
    public void activateSomething() {
        animateButton();
    }

    private void animateButton() {
        LayerDrawable layerDrawable = (LayerDrawable) mBtnActivate.getDrawable();
        RotateDrawable drawable = ((RotateDrawable) layerDrawable.getDrawable(0));
        mAnimator = ObjectAnimator.ofInt(drawable, "level", 0, 10000);
        mAnimator.setDuration(300);
        mAnimator.start();
        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(final Animator animator) {
                // do nothing
            }

            @Override
            public void onAnimationEnd(final Animator animator) {
                mBtnActivate.setVisibility(View.GONE);
                mVgActivated.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(final Animator animator) {
                Log.v("animation", "onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(final Animator animator) {
                // do nothing
            }
        });
    }

    @OnClick(R.id.btn_undo)
    public void undoActivation() {
        if (mAnimator.isRunning()) {
            mAnimator.cancel();
        }
        mBtnActivate.setVisibility(View.VISIBLE);
        mVgActivated.setVisibility(View.GONE);
    }
}
