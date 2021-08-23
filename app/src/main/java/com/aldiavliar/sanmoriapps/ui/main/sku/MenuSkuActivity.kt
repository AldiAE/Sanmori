package com.aldiavliar.sanmoriapps.ui.main.sku

import android.Manifest
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.ui.main.sku.pandega.SkuPandegaActivity
import com.aldiavliar.sanmoriapps.ui.main.sku.penegak.SkuPenegakActivity
import com.aldiavliar.sanmoriapps.ui.main.sku.penggalang.SkuPenggalangActivity
import com.aldiavliar.sanmoriapps.ui.main.sku.siaga.SkuSiagaActivity
import kotlinx.android.synthetic.main.activity_menu_sku.*
import java.util.*


@Suppress("DEPRECATION", "UNREACHABLE_CODE")
class MenuSkuActivity : AppCompatActivity() {

    private lateinit var back : ImageButton
    private lateinit var SkuSiaga : LinearLayout
    private lateinit var SkuPenggalang: LinearLayout
    private lateinit var SkuPenegak : LinearLayout
    private lateinit var SkuPandega : LinearLayout

    var mydownloadid : Long = 0

    companion object {
        const val ALERT_DIALOG_OPENED = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_sku)

        back =  findViewById(R.id.onPressedBack)
        back.setOnClickListener {
            super.onBackPressed()
        }

        SkuSiaga = findViewById(R.id.skuSiaga)
        skuSiaga.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SkuSiagaActivity::class.java)
            startActivity(intent)
        })

        SkuPenggalang = findViewById(R.id.skuPenggalang)
        skuPenggalang.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SkuPenggalangActivity::class.java)
            startActivity(intent)
        })

        SkuPenegak = findViewById(R.id.skuPenegak)
        skuPenegak.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SkuPenegakActivity::class.java)
            startActivity(intent)
        })

        SkuPandega = findViewById(R.id.skuPandega)
        skuPandega.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SkuPandegaActivity::class.java)
            startActivity(intent)
        })

        fab_download.setOnClickListener {
            showAlertDialog(ALERT_DIALOG_OPENED)
            haveStoragePermission()
        }
    }

    private fun showAlertDialog(alertDialogOpened: Int) {
        val isDialogOpen = alertDialogOpened == ALERT_DIALOG_OPENED
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogOpen) {
            dialogTitle = getString(R.string.download)
            dialogMessage = getString(R.string.download_tutorial)
        } else {
            dialogMessage = getString(R.string.message_cancel)
            dialogTitle = getString(R.string.cancel)
        }

        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            this.setTitle(dialogTitle)
            this.setMessage(dialogMessage)
            this.setCancelable(false)
            this.setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                requestDownload(this@MenuSkuActivity)
                dialog.dismiss()
            }
            this.setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    fun haveStoragePermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error", "You have permission")
                true
            } else {
                Log.e("Permission error", "You have asked for permission")
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
                false
            }
        } else { //you dont need to worry about these stuff below api level 23
            Log.e("Permission error", "You already have the permission")
            true
        }
    }

    private fun requestDownload(menuSkuActivity: MenuSkuActivity) {
        var request = DownloadManager.Request(
                Uri.parse("https://drive.google.com/uc?export=view&id=1XYnHicOdL5SmEyhzg6uwb4Ogkd2kNjbG")
        )
            .setTitle("Modul Penyelesaian SKU.zip")
            .setDescription("Modul Downloading")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Modul Penyelesaian SKU.zip")
            .setAllowedOverMetered(true)

        var dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        mydownloadid = dm.enqueue(request)

    }

    var br  = object :BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var id:Long? = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (id == mydownloadid) {
                Toast.makeText(this@MenuSkuActivity, "Modul Download Completed", Toast.LENGTH_LONG).show()
            }
        }

    }
    override fun registerReceiver(receiver: BroadcastReceiver?, filter: IntentFilter?): Intent? {
        return super.registerReceiver(receiver, filter)
        registerReceiver(br, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

}