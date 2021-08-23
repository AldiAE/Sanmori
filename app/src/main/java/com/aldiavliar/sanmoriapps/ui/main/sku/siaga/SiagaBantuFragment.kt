package com.aldiavliar.sanmoriapps.ui.main.sku.siaga

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
import com.aldiavliar.sanmoriapps.data.Entity.SiagaEntity
import com.aldiavliar.sanmoriapps.databinding.FragmentSiagaBantuBinding
import com.aldiavliar.sanmoriapps.ui.main.sku.siaga.ViewModel.SiagaBantuViewModel
import com.aldiavliar.sanmoriapps.ui.main.sku.siaga.ViewModel.ViewModelFactory
import com.aldiavliar.sanmoriapps.ui.main.sku.siaga.adapter.SiagaBantuAdapter
import com.aldiavliar.sanmoriapps.ui.main.sku.siaga.addUpdate.TambahSiagaActivity


class SiagaBantuFragment : Fragment() {

    private var _fragmentSiagaBantuBinding: FragmentSiagaBantuBinding? = null
    private val binding get() = _fragmentSiagaBantuBinding

    private lateinit var bantuAdapter: SiagaBantuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentSiagaBantuBinding = FragmentSiagaBantuBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val siagaBantuViewModel = obtainViewModel(this)
        siagaBantuViewModel.getSiagaBantu().observe(viewLifecycleOwner, siagaBantuObserver)

        bantuAdapter = SiagaBantuAdapter(context as Activity)

        binding?.rvSiaga?.layoutManager = LinearLayoutManager(this.context)
        binding?.rvSiaga?.setHasFixedSize(true)
        binding?.rvSiaga?.adapter = bantuAdapter

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
            if (requestCode == TambahSiagaActivity.REQUEST_ADD) {
                if (resultCode == TambahSiagaActivity.RESULT_ADD) {
                    Toast.makeText(activity?.applicationContext, "Tersimpan", Toast.LENGTH_SHORT).show()
                }
            } else if (requestCode == TambahSiagaActivity.REQUEST_UPDATE) {
                if (resultCode == TambahSiagaActivity.RESULT_UPDATE) {
                    Toast.makeText(activity?.applicationContext, "Terupdate", Toast.LENGTH_SHORT).show()
                } else if (resultCode == TambahSiagaActivity.RESULT_DELETE) {
                    Toast.makeText(activity?.applicationContext, "Terhapus", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val siagaBantuObserver =
            Observer<List<SiagaEntity>> { siagaBantuList ->
                if (siagaBantuList != null) {
                    bantuAdapter.setListSiagaBantu(siagaBantuList)
                }

            }

    private fun obtainViewModel(siagaBantuFragment: SiagaBantuFragment): SiagaBantuViewModel {
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        return ViewModelProvider(siagaBantuFragment, factory).get(SiagaBantuViewModel::class.java)
    }


    override fun onDestroy() {
        super.onDestroy()
        _fragmentSiagaBantuBinding = null
    }


}