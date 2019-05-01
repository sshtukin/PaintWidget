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

    private val paintGrayCircle = Paint()
    private val paintCircle = Paint()

    var color: Int = Color.BLACK
        set(value) {
            field = value
            paintCircle.color = color
        }

    init {
        paintGrayCircle.color = Color.GRAY
        paintGrayCircle.style = Paint.Style.FILL
        paintCircle.color = color
        paintCircle.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        if (isChecked) {
            canvas?.drawCircle(
                (width / 2).toFloat(),
                (height / 2).toFloat(),
                (width / 4).toFloat(),
                paintGrayCircle
            )
            canvas?.drawCircle(
                (width / 2).toFloat(),
                (height / 2).toFloat(),
                (width / 5.5).toFloat(),
                paintCircle
            )
        } else {
            canvas?.drawCircle(
                (width / 2).toFloat(),
                (height / 2).toFloat(),
                (width / 4.5).toFloat(),
                paintCircle
            )
        }
    }
}
