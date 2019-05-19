package com.pmarchenko.photopuzzle.newpuzzle

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import java.util.*

/**
 * @author Pavel Marchenko (Pavel.Marchenko@datart.com -- DataArt)
 */
class PuzzleFieldView : ViewGroup {

    companion object {
        val MAX_FIELD_SIZE = 3
    }

    var rowsCount = 1
        set(value) {
            if (value <= MAX_FIELD_SIZE) field = value
        }

    var columnsCount = 1
        set(value) {
            if (value <= MAX_FIELD_SIZE) field = value
        }

    fun setFieldSize(rowsCount: Int, columnsCount: Int) {
        this.rowsCount = rowsCount
        this.columnsCount = columnsCount
        updateField()
    }

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        updateField()
    }

    private fun updateField() {
        removeAllViews()
        for (row in 0 until rowsCount) {
            for (column in 0 until columnsCount) {
                val view = View(context)
                view.setBackgroundColor(Color.BLACK + Random().nextInt(0xFFFFFF))
                val lp = MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
                lp.setMargins(10, 10, 10, 10)
                addView(view, lp)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val spec = if (width < height) widthMeasureSpec else heightMeasureSpec
        super.onMeasure(spec, spec)
        measureChildren(spec, spec)
    }

    override fun measureChild(child: View?, parentWidthMeasureSpec: Int, parentHeightMeasureSpec: Int) {
        val pSize = MeasureSpec.getSize(parentWidthMeasureSpec)

        val columnWidth = pSize / columnsCount
        val rowHeight = pSize / rowsCount
        val size = Math.min(columnWidth, rowHeight)
        val sizeSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY)
        child?.measure(sizeSpec, sizeSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (rowsCount == 0 && columnsCount == 0) return

        val containerSize = r - l
        val childSize = containerSize / Math.max(rowsCount, columnsCount)

        val childrenW = childSize * columnsCount
        val childrenH = childSize * rowsCount
        val hOffset = (containerSize - childrenW) / 2
        val vOffset = (containerSize - childrenH) / 2

        for (row in 0 until rowsCount) {
            for (column in 0 until columnsCount) {
                val child = getChildAt(row * columnsCount + column)

                val cWidth = child.measuredWidth
                val cHeight = child.measuredHeight

                val left = l + cWidth * column + hOffset
                val top = t + cHeight * row + vOffset

                child.layout(
                        left + child.marginLeft,
                        top + child.marginTop,
                        left + cWidth - child.marginRight,
                        top + cHeight - child.marginBottom
                )
            }
        }
    }
}