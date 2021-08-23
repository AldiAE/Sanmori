package com.aldiavliar.sanmoriapps.ui.main.sku.penegak

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
import com.aldiavliar.sanmoriapps.data.Entity.PenegakEntity
import com.aldiavliar.sanmoriapps.databinding.FragmentPenegakBantaraBinding
import com.aldiavliar.sanmoriapps.ui.main.sku.penegak.ViewModel.PenegakBantaraViewModel
import com.aldiavliar.sanmoriapps.ui.main.sku.penegak.ViewModel.ViewModelFactory
import com.aldiavliar.sanmoriapps.ui.main.sku.penegak.adapter.PenegakBantaraAdapter
import com.aldiavliar.sanmoriapps.ui.main.sku.penegak.addUpdate.TambahPenegakActivity

class PenegakBantaraFragment : Fragment() {

    private var _fragmentPenegakBantaraBinding: FragmentPenegakBantaraBinding? = null
    private val binding get() = _fragmentPenegakBantaraBinding

    private lateinit var penegakAdapter: PenegakBantaraAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentPenegakBantaraBinding = FragmentPenegakBantaraBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val penegakBantaraViewModel = obtainViewModel(this)
        penegakBantaraViewModel.getPenegakBantara().observe(viewLifecycleOwner, penegakBantaraObserver)

        penegakAdapter = PenegakBantaraAdapter(context as Activity)

        binding?.rvPenegak?.layoutManager = LinearLayoutManager(this.context)
        binding?.rvPenegak?.setHasFixedSize(true)
        binding?.rvPenegak?.adapter = penegakAdapter

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
            if (requestCode == TambahPenegakActivity.REQUEST_ADD) {
                if (resultCode == TambahPenegakActivity.RESULT_ADD) {
                    Toast.makeText(activity?.applicationContext, "Tersimpan", Toast.LENGTH_SHORT).show()
                }
            } else if (requestCode == TambahPenegakActivity.REQUEST_UPDATE) {
                if (resultCode == TambahPenegakActivity.RESULT_UPDATE) {
                    Toast.makeText(activity?.applicationContext, "Terupdate", Toast.LENGTH_SHORT).show()
                } else if (resultCode == TambahPenegakActivity.RESULT_DELETE) {
                    Toast.makeText(activity?.applicationContext, "Terhapus", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val penegakBantaraObserver =
        Observer<List<PenegakEntity>> { penegakBantaraList ->
            if (penegakBantaraList != null) {
                penegakAdapter.setListPenegakBantara(penegakBantaraList)
            }

        }

    private fun obtainViewModel(penegakBantaraFragment: PenegakBantaraFragment): PenegakBantaraViewModel {
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        return ViewModelProvider(penegakBantaraFragment, factory).get(PenegakBantaraViewModel::class.java)
    }


    override fun onDestroy() {
        super.onDestroy()
        _fragmentPenegakBantaraBinding = null
    }

}