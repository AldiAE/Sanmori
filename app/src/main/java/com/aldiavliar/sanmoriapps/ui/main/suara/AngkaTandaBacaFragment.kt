package com.aldiavliar.sanmoriapps.ui.main.suara

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldiavliar.sanmoriapps.databinding.FragmentAngkaTandaBacaBinding
import com.aldiavliar.sanmoriapps.ui.dataMorse.DataSound


class AngkaTandaBacaFragment : Fragment() {

    private lateinit var binding: FragmentAngkaTandaBacaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAngkaTandaBacaBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val soundATB = DataSound.atbSound()
            val listATBAdapter = ListATBAdapter()
            listATBAdapter.setSoundATB(soundATB)
            with(binding.rvAtb) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = listATBAdapter
            }
        }

    }

}