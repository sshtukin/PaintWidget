package com.sshtukin.paintwidget

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.Log
import android.widget.SeekBar
import kotlinx.android.synthetic.main.widget_paint.view.*

/**
 * CustomView based on [ConstraintLayout],
 * contains [seekBar], [radioGroup] with custom [PaintRadioButton]
 *
 * @author Sergey Shtukin
 */

class PaintWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var seekBarMaxWidth = 10
        set(value) {
            field = value
            applySeekBarMaxWidth(value)
        }

    var defaultColorPosition = 0
        set(value) {
            field = value
            applyDefaultColorPosition(value)
        }

    var firstItemColor = Color.BLACK
        set(value) {
            field = value
            applyFirstItemColor(value)
        }

    private val paintWidgetListener: PaintWidgetListener = context as PaintWidgetListener

    private fun setProgressColor(color: Int) {
        val layerDrawable = seekBar.progressDrawable as LayerDrawable
        val progress = layerDrawable.findDrawableByLayerId(R.id.progress)
        progress.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }

    init {
        inflate(context, R.layout.widget_paint, this)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.PaintWidget)
        defaultColorPosition = ta.getInteger(R.styleable.PaintWidget_defaultColorPosition, 1)
        seekBarMaxWidth = ta.getInteger(R.styleable.PaintWidget_seekBarMaxWidth, 10)
        firstItemColor = ta.getInteger(R.styleable.PaintWidget_firstItemColor, 0)
        ta?.recycle()

        tvWidthValue.text = seekBar.progress.toString()

        seekBar.max = seekBarMaxWidth
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, value: Int, b: Boolean) {
                tvWidthValue.text = value.toString()
                paintWidgetListener.onChangedWidth()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        rbSecond.color = ContextCompat.getColor(context, R.color.colorRed)
        rbThird.color = ContextCompat.getColor(context, R.color.colorGreen)
        rbFourth.color = ContextCompat.getColor(context, R.color.colorBlue)

        when (defaultColorPosition) {
            1 -> {
                radioGroup.check(R.id.rbFirst)
                setProgressColor(rbFirst.color)
            }
            2 -> {
                radioGroup.check(R.id.rbSecond)
                setProgressColor(rbSecond.color)
            }
            3 -> {
                radioGroup.check(R.id.rbThird)
                setProgressColor(rbThird.color)
            }
            4 -> {
                radioGroup.check(R.id.rbFourth)
                setProgressColor(rbFourth.color)
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val rbChecked = findViewById<PaintRadioButton>(checkedId)
            setProgressColor(rbChecked.color)
            paintWidgetListener.onChangedColor()
        }
    }

    private fun applySeekBarMaxWidth(value: Int){
        seekBar.max = value
    }

    private fun applyDefaultColorPosition(value: Int){
        val rb = radioGroup.getChildAt(value) as PaintRadioButton
        rb.invalidate()
        radioGroup.check(rb.id)
    }

    private fun applyFirstItemColor(value: Int){
        rbFirst.color = value
    }

    interface PaintWidgetListener{
        fun onChangedColor()
        fun onChangedWidth()
    }
}