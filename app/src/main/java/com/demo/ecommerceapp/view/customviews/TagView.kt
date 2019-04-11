package com.demo.ecommerceapp.view.customviews

import android.content.Context
import android.text.method.TextKeyListener.clear
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.demo.ecommerceapp.R
import java.util.*

/**
 * Created by yang on 2016/11/7.
 * paulyung@outlook.com
 * a auto new line layout
 *
 * @attr int android.R.styleable#LaybelLayout_line_padding
 * @attr int android.R.styleable#LaybelLayout_child_margin
 * @attr ref android.R.styleable#LaybelLayout_text_background
 *
 *
 * the default child view is TextView
 */

class TagView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ViewGroup(context, attrs, defStyleAttr), View.OnClickListener {

    private val mChildView: MutableList<View>
    private val mChildrenMsg: MutableMap<View, ChildLayoutMsg>
    private var mLinePadding: Int = 0//行内上下边距
    private var minWidth: Int = 0
    private var minHeight: Int = 0//本控件的最小宽高

    private var onItemClickListener: OnItemClickListener? = null

    //------------ child view msg ---------------
    private var childMargin: Int = 0
    private var textBackground: Int = 0

    //------------ child view data --------------
    private var mAdapter: Adapter? = null

    init {
        mChildView = ArrayList<View>()
        mChildrenMsg = HashMap<View, ChildLayoutMsg>()
        initAttr(attrs)
    }

    private fun initAttr(attrs: AttributeSet?) {
        val t = getContext().obtainStyledAttributes(attrs, R.styleable.LaybelLayout)
        mLinePadding = dip2px(t.getInt(R.styleable.LaybelLayout_line_padding, 0).toFloat())
        childMargin = dip2px(t.getInt(R.styleable.LaybelLayout_child_margin, 0).toFloat())
        textBackground = t.getResourceId(R.styleable.LaybelLayout_text_background, R.drawable.custom_back)
        t.recycle()
    }

    /**
     * according to datas generate the child views
     */

    var viewAdded: Boolean = false

    private fun prepareView() {
        if (mAdapter!!.view == null) {
            generateDefaultView()
        } else {
            for (i in 0 until mAdapter!!.count) {
                val child = mAdapter!!.view
                var params: ViewGroup.MarginLayoutParams? = child!!.getLayoutParams() as ViewGroup.MarginLayoutParams
                if (params == null) {
                    params = generateDefaultLayoutParams() as ViewGroup.MarginLayoutParams
                }
                mAdapter!!.onDataSet(child, mAdapter!!.getItem(i))
                addView(child, params)
            }
        }
        if (onItemClickListener != null)
            for (i in 0 until getChildCount()) {
                getChildAt(i).setOnClickListener(this)
            }

        viewAdded = true
    }

    private fun generateDefaultView() {
        for (i in 0 until mAdapter!!.count) {
            val child = TextView(getContext())
            val params = generateDefaultLayoutParams() as ViewGroup.MarginLayoutParams
            params.leftMargin = dip2px(5f)
            params.rightMargin = dip2px(5f)
            child.setBackgroundDrawable(getContext().getResources().getDrawable(textBackground))
            child.setText(mAdapter!!.getItem(i))
            mAdapter!!.onDataSet(child, mAdapter!!.getItem(i))
            addView(child, params)
        }
    }

    protected override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (!viewAdded)
            prepareView()
    }

    protected override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        minWidth = getPaddingLeft() + getPaddingRight()
        minHeight = getPaddingTop() + getPaddingBottom()
        val count = getChildCount()
        for (i in 0 until count) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            //            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0); 这里置为0时候，和 measureChild 是一回事
            val layoutParams = child.getLayoutParams() as ViewGroup.MarginLayoutParams
            var defSize = (getPaddingLeft() + layoutParams.leftMargin
                    + child.getMeasuredWidth() + layoutParams.rightMargin + getPaddingRight())
            if (defSize > getMeasuredWidth()) {
                defSize = (getMeasuredWidth() - layoutParams.leftMargin
                        - layoutParams.rightMargin - getPaddingLeft() - getPaddingRight())
                layoutParams.width = defSize
                measureChild(child, widthMeasureSpec, heightMeasureSpec)
            }
            if (!mChildView.contains(child))
                mChildView.add(child)
        }
        writeViewMsg()
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)

        if (widthMode != View.MeasureSpec.EXACTLY && heightMode != View.MeasureSpec.EXACTLY)
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        else if (widthMode != View.MeasureSpec.EXACTLY)
            setMeasuredDimension(minWidth, View.getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec))
        else if (heightMode != View.MeasureSpec.EXACTLY)
            setMeasuredDimension(View.getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec), minHeight)
    }

    private fun writeViewMsg() {
        var lineHeight = 0
        var lineHeightSum = 0
        var left = 0
        var top = 0
        var right = 0
        var bottom = 0
        var freeWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight()//横向剩余空间
        var isFirst = true
        var tmpWidth = 0

        for (i in mChildView.indices) {
            val view = mChildView[i]
            val layoutParams = view.getLayoutParams() as ViewGroup.MarginLayoutParams

            val childWidth = layoutParams.leftMargin + view.getMeasuredWidth() +
                    layoutParams.rightMargin
            if (childWidth > freeWidth) {
                isFirst = true
                lineHeightSum += lineHeight
                lineHeight = 0
                freeWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight()//设为初始剩余
            }

            if (isFirst) {
                left = getPaddingLeft() + layoutParams.leftMargin
                isFirst = false
                if (tmpWidth > minWidth)
                    minWidth = tmpWidth
                tmpWidth = childWidth
            } else {
                val prView = mChildView[i - 1]
                val ll = prView.getLayoutParams() as ViewGroup.MarginLayoutParams
                left += prView.getMeasuredWidth() + ll.rightMargin + layoutParams.leftMargin
                tmpWidth += childWidth
            }
            top = getPaddingTop() + lineHeightSum + mLinePadding + layoutParams.topMargin
            right = left + view.getMeasuredWidth()
            bottom = top + view.getMeasuredHeight()
            val tmpHeight = (mLinePadding * 2
                    + layoutParams.topMargin
                    + view.getMeasuredHeight()
                    + layoutParams.bottomMargin)
            if (tmpHeight > lineHeight)
                lineHeight = tmpHeight
            freeWidth -= childWidth

            if (mChildrenMsg.containsKey(view))
                mChildrenMsg.remove(view)
            mChildrenMsg[view] = ChildLayoutMsg(left, top, right, bottom)
        }
        lineHeightSum += lineHeight
        minHeight += lineHeightSum
    }

    protected override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val set = mChildrenMsg.keys
        for (child in set) {
            val msg = mChildrenMsg[child]
            child.layout(msg!!.left, msg!!.top, msg!!.right, msg!!.bottom)
        }
    }

    protected override fun generateDefaultLayoutParams(): ViewGroup.LayoutParams {
        return ViewGroup.MarginLayoutParams(super.generateDefaultLayoutParams())
    }

    override fun generateLayoutParams(attrs: AttributeSet): ViewGroup.LayoutParams {
        return ViewGroup.MarginLayoutParams(getContext(), attrs)
    }

    protected override fun generateLayoutParams(p: ViewGroup.LayoutParams): ViewGroup.LayoutParams {
        return ViewGroup.MarginLayoutParams(p)
    }

    private fun dip2px(dpValue: Float): Int {
        val scale = getContext().getResources().getDisplayMetrics().density
        return (dpValue * scale + 0.5f).toInt()
    }

    override fun onClick(v: View) {
        if (onItemClickListener != null) {
            onItemClickListener!!.onItemClick(mChildView.indexOf(v))
        }
    }

    private class ChildLayoutMsg internal constructor(
        internal var left: Int,
        internal var top: Int,
        internal var right: Int,
        internal var bottom: Int
    )

    fun setLinePadding(paddingSize: Int) {
        mLinePadding = dip2px(paddingSize.toFloat())
    }

    fun setAdapter(adapter: Adapter) {
        mAdapter = adapter
    }

    class Adapter {
        var datas = mutableListOf<String>()

        val count: Int
            get() = if (datas == null) 0 else datas!!.size

        /**
         * if you want to use custom child view, you can overide this method,
         * otherwise,the default view can be set
         *
         * @return your custom view
         */
        val view: View?
            get() = null

        constructor(datas: MutableList<String>) {
            if (this.datas != null) {
                this.datas.clear()
                this.datas.addAll(datas)
            } else
                this.datas = datas

        }

        constructor(vararg datas: String) {
            this.datas = Arrays.asList(*datas)
        }

        fun getItem(position: Int): String {
            return if (datas == null) "" else datas!![position]
        }

        //called when data set by LaybelLayout
        fun onDataSet(v: View, data: String) {}
    }

    fun setOnItemClickListener(l: OnItemClickListener) {
        onItemClickListener = l
    }

    interface OnItemClickListener {
        fun onItemClick(p: Int)
    }

    companion object {
        private val TAG = "LaybelLayout"
    }
}