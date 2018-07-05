package com.tw.fluxlib.views

import android.app.Dialog
import android.content.Context
import android.view.animation.AnimationUtils
import com.tw.fluxlib.R
import com.tw.fluxlib.utils.app.AppUtil
import kotlinx.android.synthetic.main.view_dialog_loading.*


/**
 * Created by wei.tian
 * 2018/5/15
 */
class LoadingDialog : Dialog {

    constructor(context: Context) : this(context, R.style.LoadingDialogTheme)

    constructor(context: Context, themeResId: Int) : super(context, themeResId)

    init {
        setContentView(R.layout.view_dialog_loading)
        val sreenSize = AppUtil.getScreenSize(context)
        window.setLayout(sreenSize.widthPixels / 3, sreenSize.widthPixels / 3)
        setCanceledOnTouchOutside(false)
    }

    override fun show() {
        super.show()
        val animation = AnimationUtils.loadAnimation(context, R.anim.anim_common_loading)
        imgLoading.animation = animation
        imgLoading.startAnimation(animation)
    }

    override fun dismiss() {
        imgLoading.clearAnimation()
        super.dismiss()
    }
}