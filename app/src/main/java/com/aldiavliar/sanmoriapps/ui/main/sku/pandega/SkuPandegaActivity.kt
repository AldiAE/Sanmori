package com.aldiavliar.sanmoriapps.ui.main.sku.pandega

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.data.Entity.PandegaEntity
import com.aldiavliar.sanmoriapps.databinding.ActivitySkuPandegaBinding
import com.aldiavliar.sanmoriapps.ui.main.sku.pandega.ViewModel.SkuPandegaViewModel
import com.aldiavliar.sanmoriapps.ui.main.sku.pandega.ViewModel.ViewModelFactory
import com.aldiavliar.sanmoriapps.ui.main.sku.pandega.adapter.PandegaAdapter
import com.aldiavliar.sanmoriapps.ui.main.sku.pandega.addUpdate.TambahPandegaActivity

class SkuPandegaActivity : AppCompatActivity(), View.OnClickListener {

    private var _activitySkuPandegaBinding: ActivitySkuPandegaBinding? = null
    private val binding get() = _activitySkuPandegaBinding

    private lateinit var pandegaAdapter: PandegaAdapter
//    private lateinit var back : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sku_pandega)

//        back =  findViewById(R.id.onPressedBack)
//        back.setOnClickListener {
//            supportActionBar?.hide()
//            super.onBackPressed()
//        }

        _activitySkuPandegaBinding= ActivitySkuPandegaBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val pandegaViewModel = obtainViewModel(this@SkuPandegaActivity)
        pandegaViewModel.getPandega().observe(this, pandegaObserver)

        pandegaAdapter = PandegaAdapter(this@SkuPandegaActivity)

        binding?.onPressedBack?.setOnClickListener(this)
        binding?.rvPandega?.layoutManager = LinearLayoutManager(this)
        binding?.rvPandega?.setHasFixedSize(true)
        binding?.rvPandega?.adapter = pandegaAdapter

        binding?.fabAdd?.setOnClickListener { view ->
            if (view.id == R.id.fab_add) {
                val intent = Intent(this@SkuPandegaActivity, TambahPandegaActivity::class.java)
                startActivityForResult(intent, TambahPandegaActivity.REQUEST_ADD)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (requestCode == TambahPandegaActivity.REQUEST_ADD) {
                if (resultCode == TambahPandegaActivity.RESULT_ADD) {
//                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                }
            } else if (requestCode == TambahPandegaActivity.REQUEST_UPDATE) {
                if (resultCode == TambahPandegaActivity.RESULT_UPDATE) {
//                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                } else if (resultCode == TambahPandegaActivity.RESULT_DELETE) {
//                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): SkuPandegaViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(SkuPandegaViewModel::class.java)
    }
    private val pandegaObserver = Observer<List<PandegaEntity>> { pandegaList ->
        if (pandegaList != null) {
            pandegaAdapter.setListPandega(pandegaList)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.onPressedBack -> {
                super.onBackPressed()
            }
        }
    }
}