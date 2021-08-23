package com.aldiavliar.sanmoriapps.ui.main.sku.penggalang.addUpdate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.data.Entity.PenggalangEntity
import com.aldiavliar.sanmoriapps.databinding.ActivityTambahPenggalangBinding
import com.aldiavliar.sanmoriapps.ui.main.sku.penggalang.ViewModel.TambahPenggalangViewModel
import com.aldiavliar.sanmoriapps.ui.main.sku.penggalang.ViewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_tambah_penggalang.*

class TambahPenggalangActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PENGGALANG = "extra_penggalang"
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
    private var penggalangEntity: PenggalangEntity? = null
    private var position = 0

    private lateinit var tambahPenggalangViewModel: TambahPenggalangViewModel

    private var _activityTambahPenggalangBinding: ActivityTambahPenggalangBinding? = null
    private val binding get() = _activityTambahPenggalangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_penggalang)

        _activityTambahPenggalangBinding = ActivityTambahPenggalangBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        tambahPenggalangViewModel = obtainViewModel(this)

        penggalangEntity = intent.getParcelableExtra(TambahPenggalangActivity.EXTRA_PENGGALANG)
        if (penggalangEntity != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        } else {
            penggalangEntity = PenggalangEntity()
        }

        val btnTitle: String

        if (isEdit) {
            btnTitle = getString(R.string.update)
            if (penggalangEntity != null) {
                penggalangEntity?.let { penggalangEntity ->
                    binding?.edtPoint?.setText(penggalangEntity.point)
                    binding?.edtKategori?.setText(penggalangEntity.kategori)
                    binding?.edtDeskripsi?.setText(penggalangEntity.desk_point)
                    binding?.edtCara?.setText(penggalangEntity.cara_pengujian)
                    binding?.edtStatus?.setText(penggalangEntity.status)
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
                penggalangEntity.let { penggalangEntity ->
                    penggalangEntity?.point = point
                    penggalangEntity?.kategori = kategori
                    penggalangEntity?.desk_point = desk_point
                    penggalangEntity?.cara_pengujian = cara_pengujian
                    penggalangEntity?.status = status
                }

                val intent = Intent().apply {
                    putExtra(EXTRA_PENGGALANG, penggalangEntity)
                    putExtra(EXTRA_POSITION, position)
                }

                if (isEdit) {
                    tambahPenggalangViewModel.update(penggalangEntity as PenggalangEntity)
                    setResult(RESULT_UPDATE, intent)
                    finish()
                } else {
                    tambahPenggalangViewModel.insert(penggalangEntity as PenggalangEntity)
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
        val isDialogClose = type == TambahPenggalangActivity.ALERT_DIALOG_CLOSE
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
                    tambahPenggalangViewModel.delete(penggalangEntity as PenggalangEntity)
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
        _activityTambahPenggalangBinding = null
    }
    private fun obtainViewModel(activity: AppCompatActivity): TambahPenggalangViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(TambahPenggalangViewModel::class.java)
    }
}