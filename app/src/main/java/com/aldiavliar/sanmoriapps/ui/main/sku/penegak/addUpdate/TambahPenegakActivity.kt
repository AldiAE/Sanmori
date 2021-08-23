package com.aldiavliar.sanmoriapps.ui.main.sku.penegak.addUpdate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.data.Entity.PenegakEntity
import com.aldiavliar.sanmoriapps.databinding.ActivityTambahPenegakBinding
import com.aldiavliar.sanmoriapps.ui.main.sku.penegak.ViewModel.TambahPenegakViewModel
import com.aldiavliar.sanmoriapps.ui.main.sku.penegak.ViewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_tambah_penegak.*

class TambahPenegakActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PENEGAK = "extra_penegak"
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
    private var penegakEntity: PenegakEntity? = null
    private var position = 0

    private lateinit var tambahPenegakViewModel: TambahPenegakViewModel

    private var _activityTambahPenegakBinding: ActivityTambahPenegakBinding? = null
    private val binding get() = _activityTambahPenegakBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_penegak)

        _activityTambahPenegakBinding = ActivityTambahPenegakBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        tambahPenegakViewModel = obtainViewModel(this)

        penegakEntity = intent.getParcelableExtra(EXTRA_PENEGAK)
        if (penegakEntity != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        } else {
            penegakEntity = PenegakEntity()
        }

        val btnTitle: String

        if (isEdit) {
            btnTitle = getString(R.string.update)
            if (penegakEntity != null) {
                penegakEntity?.let { penegakEntity ->
                    binding?.edtPoint?.setText(penegakEntity.point)
                    binding?.edtKategori?.setText(penegakEntity.kategori)
                    binding?.edtDeskripsi?.setText(penegakEntity.desk_point)
                    binding?.edtCara?.setText(penegakEntity.cara_pengujian)
                    binding?.edtStatus?.setText(penegakEntity.status)
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
                penegakEntity.let { penegakEntity ->
                    penegakEntity?.point = point
                    penegakEntity?.kategori = kategori
                    penegakEntity?.desk_point = desk_point
                    penegakEntity?.cara_pengujian = cara_pengujian
                    penegakEntity?.status = status
                }

                val intent = Intent().apply {
                    putExtra(EXTRA_PENEGAK, penegakEntity)
                    putExtra(EXTRA_POSITION, position)
                }

                if (isEdit) {
                    tambahPenegakViewModel.update(penegakEntity as PenegakEntity)
                    setResult(RESULT_UPDATE, intent)
                    finish()
                } else {
                    tambahPenegakViewModel.insert(penegakEntity as PenegakEntity)
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
        val isDialogClose = type == TambahPenegakActivity.ALERT_DIALOG_CLOSE
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
                    tambahPenegakViewModel.delete(penegakEntity as PenegakEntity)
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
        _activityTambahPenegakBinding = null
    }
    private fun obtainViewModel(activity: AppCompatActivity): TambahPenegakViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(TambahPenegakViewModel::class.java)
    }
}