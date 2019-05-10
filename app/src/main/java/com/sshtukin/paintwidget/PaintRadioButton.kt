package com.sshtukin.paintwidget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.RadioButton

/**
 * CustomView based on [RadioButton], draw color buttons
 *
 * @author Sergey Shtukin
 */

class PaintRadioButton(
    context: Context?, attrs: AttributeSet?
) : RadioButton(context, attrs) {

    private val paintCircle = Paint()

    var color: Int = Color.BLACK

    init {
        paintCircle.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        if (isChecked) {
            paintCircle.color = Color.GRAY
            canvas?.drawCircle(
                (width / 2).toFloat(),
                (height / 2).toFloat(),
                (width / 4).toFloat(),
                 paintCircle
            )
            paintCircle.color = color
            canvas?.drawCircle(
                (width / 2).toFloat(),
                (height / 2).toFloat(),
                (width / 5.5).toFloat(),
                paintCircle
            )
        } else {
            paintCircle.color = color
            canvas?.drawCircle(
                (width / 2).toFloat(),
                (height / 2).toFloat(),
                (width / 4.5).toFloat(),
                paintCircle
            )
        }
    }
}
