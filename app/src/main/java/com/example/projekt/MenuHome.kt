package com.example.projekt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_menu_home.*

/**
 * A simple [Fragment] subclass.
 */
class MenuHome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_settings.setOnClickListener {
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.setCustomAnimations(R.anim.fade_out, R.anim.fade_in,R.anim.fade_out, R.anim.fade_in)
            fragmentTransaction?.replace(R.id.fragment_container, MenuSettings())?.addToBackStack(null)?.commit()
        }
        button_play.setOnClickListener {
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.setCustomAnimations(R.anim.fade_out, R.anim.fade_in,R.anim.fade_out, R.anim.fade_in)
            fragmentTransaction?.replace(R.id.fragment_container, MenuPlay())?.addToBackStack(null)?.commit()
        }
    }

}
