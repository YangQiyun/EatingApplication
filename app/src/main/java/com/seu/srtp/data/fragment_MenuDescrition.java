package com.seu.srtp.data;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.seu.srtp.main.R;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

/**
 * Created by Mind on 2017/3/28.
 */
public class fragment_MenuDescrition extends SupportBlurDialogFragment implements View.OnClickListener{

   private static String ARS="MENUNUMBER";

    /**
     * Since image is going to be blurred, we don't care about resolution.
     * Down scale factor to reduce blurring time and memory allocation.
     */
    static final float DEFAULT_BLUR_DOWN_SCALE_FACTOR = 2.0f;

    /**
     * Radius used to blur the background
     */
    static final int DEFAULT_BLUR_RADIUS = 8;

    /**
     * Default dimming policy.
     */
    static final boolean DEFAULT_DIMMING_POLICY = true;

    /**
     * Default debug policy.
     */
    static final boolean DEFAULT_DEBUG_POLICY = false;

    /**
     * Default action bar blurred policy.
     */
    static final boolean DEFAULT_ACTION_BAR_BLUR = true;

    /**
     * Default use of RenderScript.
     */
    static final boolean DEFAULT_USE_RENDERSCRIPT = true;


    public static fragment_MenuDescrition newInstance(int menuNumber) {
        fragment_MenuDescrition fragment = new fragment_MenuDescrition();
        Bundle args = new Bundle();
        args.putInt(ARS,menuNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(700, 1150);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.item_menudescrition, null);
        builder.setView(view);
        view.setOnClickListener(this);
        return builder.create();
    }

    @Override
    protected boolean isDebugEnable() {
        return DEFAULT_DEBUG_POLICY;
    }

    @Override
    protected boolean isDimmingEnable() {
        return DEFAULT_DIMMING_POLICY;
    }

    @Override
    protected boolean isActionBarBlurred() {
        return DEFAULT_ACTION_BAR_BLUR;
    }

    @Override
    protected float getDownScaleFactor() {
        return DEFAULT_BLUR_DOWN_SCALE_FACTOR;
    }

    @Override
    protected int getBlurRadius() {
        return DEFAULT_BLUR_RADIUS;
    }

    @Override
    protected boolean isRenderScriptEnable() {
        return DEFAULT_USE_RENDERSCRIPT;
    }

    @Override
    public void onClick(View v) {
        this.dismiss();

    }
}
