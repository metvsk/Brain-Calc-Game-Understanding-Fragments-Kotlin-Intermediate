package com.chillandcode.oparator_fragments_kotlin

import android.content.Context
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar


class Utilities {

    companion object {

        fun snackIt(
            context: Context,
            views: View,
            message: String,
            duration: Int,
            drawableBackGround: Int,
            gravity: Int,
            textColor: Int,
            backgroundColor: Int? =null
        ) {
            val snackBarView = Snackbar.make(views, message, duration)
            val view = snackBarView.view
            val params =view.layoutParams as CoordinatorLayout.LayoutParams
            params.gravity = gravity
            view.layoutParams = params
            view.background =
                ContextCompat.getDrawable(context, drawableBackGround) // for custom background
            snackBarView.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
            snackBarView.setTextColor(textColor)
            if(backgroundColor!=null)
                snackBarView.setBackgroundTint(backgroundColor)
            snackBarView.show()
        }
    }
}