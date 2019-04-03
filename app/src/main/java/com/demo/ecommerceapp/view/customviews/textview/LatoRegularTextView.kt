package com.demo.ecommerceapp.view.customviews.textview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class LatoRegularTextView : AppCompatTextView {

    constructor(context : Context):super(context){
        init()
    }

    constructor(context: Context, attrs: AttributeSet): super(context,attrs){
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int):super(context,attrs,defStyle){
        init()
    }

    private fun init() {
        val tf = Typeface.createFromAsset(context.assets, "fonts/LatoRegular.ttf")
        setTypeface(tf, Typeface.NORMAL)

    }
}