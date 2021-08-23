package com.aldiavliar.sanmoriapps.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.ui.main.AlarmActivity
import com.aldiavliar.sanmoriapps.ui.main.ProfilActivity


class SettingFragment : Fragment() {

    private lateinit var settingholder: LinearLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)

    }

    @SuppressLint("WrongViewCast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingholder =  getView()?.findViewById(R.id.setting_holder) as LinearLayout


        val profil = view.findViewById(R.id.LinearLayoutProfil) as LinearLayout
        profil.setOnClickListener(object:View.OnClickListener {
            override fun onClick(view:View) {
                val intent = Intent(getActivity(), ProfilActivity::class.java)
                startActivity(intent)
            }
        })

        val alarm = view.findViewById(R.id.LinearLayoutAlarm) as LinearLayout
        alarm.setOnClickListener(object:View.OnClickListener {
            override fun onClick(view:View) {
                val intent = Intent(getActivity(), AlarmActivity::class.java)
                startActivity(intent)
            }
        })

    }

}



