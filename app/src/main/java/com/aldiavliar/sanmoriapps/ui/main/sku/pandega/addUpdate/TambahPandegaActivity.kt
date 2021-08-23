package com.aldiavliar.sanmoriapps.ui.main.sku.pandega.addUpdate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.data.Entity.PandegaEntity
import com.aldiavliar.sanmoriapps.databinding.ActivityTambahPandegaBinding
import com.aldiavliar.sanmoriapps.ui.main.sku.pandega.ViewModel.TambahPandegaViewModel
import com.aldiavliar.sanmoriapps.ui.main.sku.pandega.ViewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_tambah_pandega.*

class TambahPandegaActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PANDEGA = "extra_pandega"
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
    private var pandegaEntity: PandegaEntity? = null
    private var position = 0

    private lateinit var tambahPandegaViewModel: TambahPandegaViewModel

    private var _activityTambahPandegaBinding: ActivityTambahPandegaBinding? = null
    private val binding get() = _activityTambahPandegaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_pandega)

        _activityTambahPandegaBinding = ActivityTambahPandegaBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        tambahPandegaViewModel = obtainViewModel(this)

        pandegaEntity = intent.getParcelableExtra(EXTRA_PANDEGA)
        if (pandegaEntity != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        } else {
            pandegaEntity = PandegaEntity()
        }

        val btnTitle: String

        if (isEdit) {
            btnTitle = getString(R.string.update)
            if (pandegaEntity != null) {
                pandegaEntity?.let { pandegaEntity ->
                    binding?.edtPoint?.setText(pandegaEntity.point)
                    binding?.edtKategori?.setText(pandegaEntity.kategori)
                    binding?.edtDeskripsi?.setText(pandegaEntity.desk_point)
                    binding?.edtCara?.setText(pandegaEntity.cara_pengujian)
                    binding?.edtStatus?.setText(pandegaEntity.status)
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
                pandegaEntity.let { pandegaEntity ->
                    pandegaEntity?.point = point
                    pandegaEntity?.kategori = kategori
                    pandegaEntity?.desk_point = desk_point
                    pandegaEntity?.cara_pengujian = cara_pengujian
                    pandegaEntity?.status = status
                }

                val intent = Intent().apply {
                    putExtra(EXTRA_PANDEGA, pandegaEntity)
                    putExtra(EXTRA_POSITION, position)
                }

                if (isEdit) {
                    tambahPandegaViewModel.update(pandegaEntity as PandegaEntity)
                    setResult(RESULT_UPDATE, intent)
                    finish()
                } else {
                    tambahPandegaViewModel.insert(pandegaEntity as PandegaEntity)
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
                    tambahPandegaViewModel.delete(pandegaEntity as PandegaEntity)
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
        _activityTambahPandegaBinding = null
    }
    private fun obtainViewModel(activity: AppCompatActivity): TambahPandegaViewModel{
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(TambahPandegaViewModel::class.java)
    }
}