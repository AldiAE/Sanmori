package com.aldiavliar.sanmoriapps.ui.fragment

import android.os.Bundle
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aldiavliar.sanmoriapps.R
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView8.setText("Email : \n" +
                "aldirosyad68@gmail.com \n" +
                "No. Hp : \n " +
                "+6285879596795")
        Linkify.addLinks(textView8, Linkify.ALL)
    }

}