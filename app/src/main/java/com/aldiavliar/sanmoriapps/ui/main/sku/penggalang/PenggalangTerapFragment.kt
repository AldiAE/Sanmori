package com.aldiavliar.sanmoriapps.ui.main.sku.penggalang

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldiavliar.sanmoriapps.data.Entity.PenggalangEntity
import com.aldiavliar.sanmoriapps.databinding.FragmentPenggalangTerapBinding
import com.aldiavliar.sanmoriapps.ui.main.sku.penggalang.ViewModel.PenggalangTerapViewModel
import com.aldiavliar.sanmoriapps.ui.main.sku.penggalang.ViewModel.ViewModelFactory
import com.aldiavliar.sanmoriapps.ui.main.sku.penggalang.adapter.PenggalangTerapAdapter
import com.aldiavliar.sanmoriapps.ui.main.sku.penggalang.addUpdate.TambahPenggalangActivity


class PenggalangTerapFragment : Fragment() {


    private var _fragmentPenggalangTerapBinding: FragmentPenggalangTerapBinding? = null
    private val binding get() = _fragmentPenggalangTerapBinding

    private lateinit var penggalangAdapter: PenggalangTerapAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentPenggalangTerapBinding = FragmentPenggalangTerapBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val penggalangRakitViewModel = obtainViewModel(this)
        penggalangRakitViewModel.getPenggalangTerap().observe(viewLifecycleOwner, penggalangTerapObserver)

        penggalangAdapter = PenggalangTerapAdapter(context as Activity)

        binding?.rvPenggalang?.layoutManager = LinearLayoutManager(this.context)
        binding?.rvPenggalang?.setHasFixedSize(true)
        binding?.rvPenggalang?.adapter = penggalangAdapter

//        val fabAdd = view.findViewById(R.id.fab_add) as FloatingActionButton
//        binding?.fabAdd?.setOnClickListener(object:View.OnClickListener {
//            override fun onClick(view:View) {
//                val intent = Intent(getActivity(), TambahSiagaActivity::class.java)
//                startActivityForResult(intent, TambahSiagaActivity.REQUEST_ADD)
//            }
//        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (requestCode == TambahPenggalangActivity.REQUEST_ADD) {
                if (resultCode == TambahPenggalangActivity.RESULT_ADD) {
                    Toast.makeText(activity?.applicationContext, "Tersimpan", Toast.LENGTH_SHORT).show()
                }
            } else if (requestCode == TambahPenggalangActivity.REQUEST_UPDATE) {
                if (resultCode == TambahPenggalangActivity.RESULT_UPDATE) {
                    Toast.makeText(activity?.applicationContext, "Terupdate", Toast.LENGTH_SHORT).show()
                } else if (resultCode == TambahPenggalangActivity.RESULT_DELETE) {
                    Toast.makeText(activity?.applicationContext, "Terhapus", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val penggalangTerapObserver =
        Observer<List<PenggalangEntity>> { penggalangTerapList ->
            if (penggalangTerapList != null) {
                penggalangAdapter.setListPenggalangTerap(penggalangTerapList)
            }

        }

    private fun obtainViewModel(penggalangTerapFragment: PenggalangTerapFragment): PenggalangTerapViewModel {
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        return ViewModelProvider(penggalangTerapFragment, factory).get(PenggalangTerapViewModel::class.java)
    }


    override fun onDestroy() {
        super.onDestroy()
        _fragmentPenggalangTerapBinding = null
    }

}