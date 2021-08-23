package com.aldiavliar.sanmoriapps.ui.main.suara

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.databinding.FragmentHurufBinding
import com.aldiavliar.sanmoriapps.ui.dataMorse.DataSound


class HurufFragment : Fragment() {
    private lateinit var binding: FragmentHurufBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHurufBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val soundHuruf = DataSound.hurufSound()
            val listHurufAdapter = ListHurufAdapter()
            listHurufAdapter.setSoundHuruf(soundHuruf)
            with(binding.rvHuruf) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = listHurufAdapter
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search, menu)
    }



}