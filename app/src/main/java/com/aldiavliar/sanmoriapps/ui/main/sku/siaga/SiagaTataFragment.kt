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
import com.aldiavliar.sanmoriapps.databinding.FragmentSiagaTataBinding
import com.aldiavliar.sanmoriapps.ui.main.sku.siaga.ViewModel.SiagaTataViewModel
import com.aldiavliar.sanmoriapps.ui.main.sku.siaga.ViewModel.ViewModelFactory
import com.aldiavliar.sanmoriapps.ui.main.sku.siaga.adapter.SiagaTataAdapter
import com.aldiavliar.sanmoriapps.ui.main.sku.siaga.addUpdate.TambahSiagaActivity

class SiagaTataFragment : Fragment() {

    private var _fragmentSiagaTataBinding: FragmentSiagaTataBinding? = null
    private val binding get() = _fragmentSiagaTataBinding

    private lateinit var tataAdapter: SiagaTataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentSiagaTataBinding = FragmentSiagaTataBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val siagaTataViewModel = obtainViewModel(this)
        siagaTataViewModel.getSiagaTata().observe(viewLifecycleOwner, siagaTataObserver)

        tataAdapter = SiagaTataAdapter(context as Activity)

        binding?.rvSiaga?.layoutManager = LinearLayoutManager(this.context)
        binding?.rvSiaga?.setHasFixedSize(true)
        binding?.rvSiaga?.adapter = tataAdapter

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

    private val siagaTataObserver =
            Observer<List<SiagaEntity>> { siagaTataList ->
                if (siagaTataList != null) {
                    tataAdapter.setListSiagaTata(siagaTataList)
                }

            }

    private fun obtainViewModel(siagaTataFragment: SiagaTataFragment): SiagaTataViewModel {
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        return ViewModelProvider(siagaTataFragment, factory).get(SiagaTataViewModel::class.java)
    }


    override fun onDestroy() {
        super.onDestroy()
        _fragmentSiagaTataBinding = null
    }

}