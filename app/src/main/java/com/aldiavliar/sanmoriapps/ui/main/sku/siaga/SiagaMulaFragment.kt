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
import com.aldiavliar.sanmoriapps.databinding.FragmentSiagaMulaBinding
import com.aldiavliar.sanmoriapps.ui.main.sku.siaga.ViewModel.SiagaMulaViewModel
import com.aldiavliar.sanmoriapps.ui.main.sku.siaga.ViewModel.ViewModelFactory
import com.aldiavliar.sanmoriapps.ui.main.sku.siaga.adapter.SiagaMulaAdapter
import com.aldiavliar.sanmoriapps.ui.main.sku.siaga.addUpdate.TambahSiagaActivity

class SiagaMulaFragment : Fragment() {

    private var _fragmentSiagaMulaBinding: FragmentSiagaMulaBinding? = null
    private val binding get() = _fragmentSiagaMulaBinding

    private lateinit var mulaAdapter: SiagaMulaAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentSiagaMulaBinding = FragmentSiagaMulaBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val siagaMulaViewModel = obtainViewModel(this)
        siagaMulaViewModel.getSiagaMula().observe(viewLifecycleOwner, siagaMulaObserver)

        mulaAdapter = SiagaMulaAdapter(context as Activity)

        binding?.rvSiaga?.layoutManager = LinearLayoutManager(this.context)
        binding?.rvSiaga?.setHasFixedSize(true)
        binding?.rvSiaga?.adapter = mulaAdapter

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

    private val siagaMulaObserver =
        Observer<List<SiagaEntity>> { siagaMulaList ->
        if (siagaMulaList != null) {
            mulaAdapter.setListSiagaMula(siagaMulaList)
        }

    }

    private fun obtainViewModel(siagaMulaFragment: SiagaMulaFragment): SiagaMulaViewModel {
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        return ViewModelProvider(siagaMulaFragment, factory).get(SiagaMulaViewModel::class.java)
    }
    

    override fun onDestroy() {
        super.onDestroy()
        _fragmentSiagaMulaBinding = null
    }
    


}




