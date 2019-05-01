package com.sshtukin.paintwidget

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Activity which contains [btnShow] to show and [btnHide] to hide [paintWidget]
 *
 * @author Sergey Shtukin
 */

class MainActivity : AppCompatActivity(), PaintWidgetListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        paintWidget.paintWidgetListener = this
        btnHide.setOnClickListener {
            paintWidget.visibility = View.GONE
        }
        btnShow.setOnClickListener {
            paintWidget.visibility = View.VISIBLE
        }
    }

    override fun onChangeColor() {
        Toast.makeText(this, getString(R.string.color_changed), Toast.LENGTH_LONG).show()
    }

    override fun onChangeWidth() {
        Toast.makeText(this, getString(R.string.width_changed), Toast.LENGTH_LONG).show()
    }
}
