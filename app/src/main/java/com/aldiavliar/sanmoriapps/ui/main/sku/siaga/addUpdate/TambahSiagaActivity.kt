package com.aldiavliar.sanmoriapps.ui.main.sku.siaga.addUpdate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.data.Entity.SiagaEntity
import com.aldiavliar.sanmoriapps.databinding.ActivityTambahSiagaBinding
import com.aldiavliar.sanmoriapps.ui.main.sku.siaga.ViewModel.TambahSiagaViewModel
import com.aldiavliar.sanmoriapps.ui.main.sku.siaga.ViewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_tambah_siaga.*

class TambahSiagaActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SIAGA = "extra_siaga"
        const val EXTRA_POSITION = "extra_position"
        const val REQUEST_ADD = 100
        const val RESULT_ADD = 101
        const val REQUEST_UPDATE = 200
        const val RESULT_UPDATE = 201
        const val RESULT_DELETE = 301
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

    private var isEdit = false
    private var siagaEntity: SiagaEntity? = null
    private var position = 0

    private lateinit var tambahSiagaViewModel: TambahSiagaViewModel

    private var _activityTambahSiagaBinding: ActivityTambahSiagaBinding? = null
    private val binding get() = _activityTambahSiagaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_siaga)

        _activityTambahSiagaBinding = ActivityTambahSiagaBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        tambahSiagaViewModel = obtainViewModel(this)

        siagaEntity = intent.getParcelableExtra(EXTRA_SIAGA)
        if (siagaEntity != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        } else {
            siagaEntity = SiagaEntity()
        }

        val btnTitle: String

        if (isEdit) {
            btnTitle = getString(R.string.update)
            if (siagaEntity != null) {
                siagaEntity?.let { siagaEntity ->
                    binding?.edtPoint?.setText(siagaEntity.point)
                    binding?.edtKategori?.setText(siagaEntity.kategori)
                    binding?.edtDeskripsi?.setText(siagaEntity.desk_point)
                    binding?.edtCara?.setText(siagaEntity.cara_pengujian)
                    binding?.edtStatus?.setText(siagaEntity.status)
                }
            }
        } else {
            btnTitle = getString(R.string.save)
        }

        binding?.btnSubmit?.text = btnTitle
        binding?.btnSubmit?.setOnClickListener {
            val point = binding?.edtPoint?.text.toString().trim()
            val kategori = binding?.edtKategori?.text.toString().trim()
            val desk_point = binding?.edtDeskripsi?.text.toString().trim()
            val cara_pengujian = binding?.edtCara?.text.toString().trim()
            val status = binding?.edtStatus?.text.toString().trim()

            if (point.isEmpty()) {
                binding?.edtPoint?.error = getString(R.string.empty)
            } else if (kategori.isEmpty()) {
                binding?.edtKategori?.error = getString(R.string.empty)
            } else if (desk_point.isEmpty()) {
                binding?.edtDeskripsi?.error = getString(R.string.empty)
            } else if (cara_pengujian.isEmpty()) {
                binding?.edtCara?.error = getString(R.string.empty)
            } else if (status.isEmpty()) {
                binding?.edtStatus?.error = getString(R.string.empty)
            } else {
                siagaEntity.let { siagaEntity ->
                    siagaEntity?.point = point
                    siagaEntity?.kategori = kategori
                    siagaEntity?.desk_point = desk_point
                    siagaEntity?.cara_pengujian = cara_pengujian
                    siagaEntity?.status = status
                }

                val intent = Intent().apply {
                    putExtra(EXTRA_SIAGA, siagaEntity)
                    putExtra(EXTRA_POSITION, position)
                }

                if (isEdit) {
                    tambahSiagaViewModel.update(siagaEntity as SiagaEntity)
                    setResult(RESULT_UPDATE, intent)
                    finish()
                } else {
                    tambahSiagaViewModel.insert(siagaEntity as SiagaEntity)
                    setResult(RESULT_ADD, intent)
                    finish()
                }
            }
        }

        btn_delete.setOnClickListener {
            showAlertDialog(ALERT_DIALOG_DELETE)
        }
    }

    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogClose) {
            dialogTitle = getString(R.string.cancel)
            dialogMessage = getString(R.string.message_cancel)
        } else {
            dialogMessage = getString(R.string.message_delete)
            dialogTitle = getString(R.string.deleteImg)
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                if (!isDialogClose) {
                    tambahSiagaViewModel.delete(siagaEntity as SiagaEntity)
                    val intent = Intent()
                    intent.putExtra(EXTRA_POSITION, position)
                    setResult(RESULT_DELETE, intent)
                }
                finish()
            }
            setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _activityTambahSiagaBinding = null
    }
    private fun obtainViewModel(activity: AppCompatActivity): TambahSiagaViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(TambahSiagaViewModel::class.java)
    }
}