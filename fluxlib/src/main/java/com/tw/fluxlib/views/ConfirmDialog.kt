package com.tw.fluxlib.views

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.tw.fluxlib.R
import com.tw.fluxlib.utils.app.AppUtil
import kotlinx.android.synthetic.main.view_dialog_confirm.*

/**
 * Created by wei.tian
 * 2018/6/1
 */
class ConfirmDialog : Dialog {
    constructor(context: Context) : this(context, R.style.ConfirmDialogTheme)

    constructor(context: Context, themeResId: Int) : super(context, themeResId)

    private val tvTitle: TextView
    private val tvOk: TextView
    private val vMoulding: View
    private val tvCancel: TextView
    private var listener: ButtonClickListener? = null

    init {
        setContentView(R.layout.view_dialog_confirm)
        val sreenSize = AppUtil.getScreenSize(context)
        window.setLayout(sreenSize.widthPixels - (sreenSize.widthPixels / 4), LinearLayout.LayoutParams.WRAP_CONTENT)
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        tvTitle = findViewById(R.id.tvTitle)
        tvOk = findViewById(R.id.tvOk)
        vMoulding = findViewById(R.id.moulding)
        tvCancel = findViewById(R.id.tvCancel)
        tvOk.setOnClickListener({
            listener?.onOk()
            dismiss()
        })
        tvCancel.setOnClickListener({
            listener?.onCancel()
            dismiss()
        })
    }

    fun setTitle(title: String): ConfirmDialog {
        tvTitle.text = title
        return this
    }

    fun setContent(content: String): ConfirmDialog {
        tvContent.text = content
        return this
    }

    fun setOnButtonClickListener(listener: ButtonClickListener): ConfirmDialog {
        this.listener = listener
        return this
    }

    fun isShowOkOnly(showOkOnly: Boolean): ConfirmDialog {
        if (showOkOnly) {
            if (tvCancel.visibility != View.GONE) {
                tvCancel.visibility = View.GONE
            }
            if (vMoulding.visibility != View.GONE) {
                vMoulding.visibility = View.GONE
            }
        } else {
            if (tvCancel.visibility != View.VISIBLE) {
                tvCancel.visibility = View.VISIBLE
            }
            if (vMoulding.visibility != View.VISIBLE) {
                vMoulding.visibility = View.VISIBLE
            }
        }
        return this
    }


    interface ButtonClickListener {
        fun onOk()
        fun onCancel() = Unit
    }
}