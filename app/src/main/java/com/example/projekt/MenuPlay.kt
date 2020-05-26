package com.example.projekt


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_menu_play.*
import kotlinx.android.synthetic.main.fragment_menu_play.button_back
import kotlinx.android.synthetic.main.fragment_menu_settings.*

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

    private var idMap = 1

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_back.setOnClickListener {
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
            fragmentTransaction?.replace(R.id.fragment_container, MenuHome())?.commit()
        }

        button_mapa1.setOnClickListener{
            idMap = 1
        }
        button_mapa2.setOnClickListener{
            idMap = 2
        }
        button_mapa3.setOnClickListener{
            idMap = 3
        }
        button_mapa4.setOnClickListener{
            idMap = 4
        }
        button_play2.setOnClickListener{
            val intent = Intent(activity, GameActivity::class.java)
            intent.putExtra( "idMap",idMap.toString())
            startActivity(intent)
        }

    }
}

