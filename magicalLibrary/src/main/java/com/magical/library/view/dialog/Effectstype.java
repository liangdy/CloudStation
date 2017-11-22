package com.magical.library.view.dialog;

import com.magical.library.view.dialog.animation.BaseEffects;
import com.magical.library.view.dialog.animation.FadeIn;
import com.magical.library.view.dialog.animation.FlipH;
import com.magical.library.view.dialog.animation.FlipV;
import com.magical.library.view.dialog.animation.NewsPaper;
import com.magical.library.view.dialog.animation.SideFall;
import com.magical.library.view.dialog.animation.SlideLeft;
import com.magical.library.view.dialog.animation.SlideRight;
import com.magical.library.view.dialog.animation.SlideTop;
import com.magical.library.view.dialog.animation.Slit;

public enum Effectstype {

    Fadein(FadeIn.class),
    Slideleft(SlideLeft.class),
    Slidetop(SlideTop.class),
    SlideBottom(com.magical.library.view.dialog.animation.SlideBottom.class),
    Slideright(SlideRight.class),
    Fall(com.magical.library.view.dialog.animation.Fall.class),
    Newspager(NewsPaper.class),
    Fliph(FlipH.class),
    Flipv(FlipV.class),
    RotateBottom(com.magical.library.view.dialog.animation.RotateBottom.class),
    RotateLeft(com.magical.library.view.dialog.animation.RotateLeft.class),
    Slit(Slit.class),
    Shake(com.magical.library.view.dialog.animation.Shake.class),
    Sidefill(SideFall.class);
    private Class<? extends BaseEffects> effectsClazz;


    private Effectstype(Class<? extends BaseEffects> mclass) {
        effectsClazz = mclass;
    }


    public BaseEffects getAnimator() {
        BaseEffects bEffects = null;
        try {
            bEffects = effectsClazz.newInstance();
        } catch (ClassCastException | InstantiationException | IllegalAccessException e) {
            throw new Error("Can not init animatorClazz instance");
        }
        return bEffects;
    }
}
