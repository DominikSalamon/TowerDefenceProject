package com.example.projekt


import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_menu_play.*

/**
 * A simple [Fragment] subclass.
 */
class MenuPlay : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_menu_play, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        button_play2.setOnClickListener{
            val intent = Intent(activity, GameActivity::class.java)
            intent.putExtra( "randomMap",checkBox.isChecked)
            val xTiles = sText1.text.toString().toInt()
            val yTiles = sText2.text.toString().toInt()
            intent.putExtra( "xTiles",xTiles)
            intent.putExtra( "yTiles",yTiles)
            startActivity(intent)
        }

        seekBar.max = 27
        sText1.text = "3"
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText1.text = (i+3).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })
        seekBar2.max = 27
        sText2.text = "3"
        seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText2.text = (i+3).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

    }
}

