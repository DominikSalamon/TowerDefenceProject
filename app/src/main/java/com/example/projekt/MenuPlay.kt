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

            val xTiles = sText1.text.toString().toInt()
            val yTiles = sText2.text.toString().toInt()

            intent.putExtra( "s3",sText3.text.toString().toInt())
            intent.putExtra( "s4",sText4.text.toString().toInt())
            intent.putExtra( "s5",sText5.text.toString().toInt())
            intent.putExtra( "s6",sText6.text.toString().toInt())
            intent.putExtra( "s7",sText7.text.toString().toInt())
            intent.putExtra( "s8",sText8.text.toString().toInt())
            intent.putExtra( "s9",sText9.text.toString().toInt())
            intent.putExtra( "s10",sText10.text.toString().toInt())
            intent.putExtra( "s11",sText11.text.toString().toInt())
            intent.putExtra( "s12",sText12.text.toString().toInt())
            intent.putExtra( "s13",sText13.text.toString().toInt())
            intent.putExtra( "s14",sText14.text.toString().toInt())
            intent.putExtra( "s15",sText15.text.toString().toInt())
            intent.putExtra( "s16",sText16.text.toString().toInt())
            intent.putExtra( "s17",sText17.text.toString().toInt())
            intent.putExtra( "s18",sText18.text.toString().toInt())
            intent.putExtra( "s19",sText19.text.toString().toInt())
            intent.putExtra( "s20",sText20.text.toString().toInt())
            intent.putExtra( "s21",sText21.text.toString().toInt())
            intent.putExtra( "s22",sText22.text.toString().toInt())
            intent.putExtra( "s23",sText23.text.toString().toInt())
            intent.putExtra( "s24",sText24.text.toString().toInt())

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

        seekBar3.max = 256
        sText3.text = "0"
        seekBar3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText3.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar4.max = 20
        sText4.text = "0"
        seekBar4.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText4.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar5.max = 256
        sText5.text = "0"
        seekBar5.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText5.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar6.max = 20
        sText6.text = "0"
        seekBar6.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText6.text = i.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar7.max = 50
        sText7.text = "0"
        seekBar7.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText7.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar8.max = 20
        sText8.text = "0"
        seekBar8.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText8.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar9.max = 50
        sText9.text = "0"
        seekBar9.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText9.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar10.max = 20
        sText10.text = "0"
        seekBar10.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText10.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar11.max = 50
        sText11.text = "0"
        seekBar11.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText11.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar12.max = 20
        sText12.text = "0"
        seekBar12.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText12.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar13.max = 50
        sText13.text = "0"
        seekBar13.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText13.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar14.max = 20
        sText14.text = "0"
        seekBar14.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText14.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar15.max = 50
        sText15.text = "0"
        seekBar15.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText15.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar16.max = 20
        sText16.text = "0"
        seekBar16.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText16.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })


        seekBar17.max = 50
        sText17.text = "0"
        seekBar17.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText17.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar18.max = 20
        sText18.text = "0"
        seekBar18.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText18.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar19.max = 50
        sText19.text = "0"
        seekBar19.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText19.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar20.max = 20
        sText20.text = "0"
        seekBar20.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText20.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar21.max = 50
        sText21.text = "0"
        seekBar21.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText21.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar22.max = 20
        sText22.text = "0"
        seekBar22.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText22.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar23.max = 50
        sText23.text = "0"
        seekBar23.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText23.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

        seekBar24.max = 20
        sText24.text = "0"
        seekBar24.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                sText24.text = (i).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })

    }
}

