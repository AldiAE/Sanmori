package com.aldiavliar.sanmoriapps.ui.main.sku.siaga.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.data.Entity.SiagaEntity
import com.aldiavliar.sanmoriapps.data.helper.SiagaDiffCallback
import com.aldiavliar.sanmoriapps.databinding.ItemSkuBinding
import com.aldiavliar.sanmoriapps.ui.main.sku.siaga.addUpdate.TambahSiagaActivity


class SiagaBantuAdapter internal constructor(private val activity: Activity) : RecyclerView.Adapter<SiagaBantuAdapter.SiagaViewHolder>() {
    private val listSiagaBantu = ArrayList<SiagaEntity>()


    fun setListSiagaBantu(listSiagaBantu: List<SiagaEntity>) {
        val diffCallback = SiagaDiffCallback(this.listSiagaBantu, listSiagaBantu)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listSiagaBantu.clear()
        this.listSiagaBantu.addAll(listSiagaBantu)
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiagaViewHolder {
        val binding = ItemSkuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SiagaViewHolder(binding)
    }
    override fun onBindViewHolder(holder: SiagaViewHolder, position: Int) {
        holder.bind(listSiagaBantu[position])
    }
    override fun getItemCount(): Int {
        return listSiagaBantu.size
    }
    inner class SiagaViewHolder(private val binding: ItemSkuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(siagaEntity: SiagaEntity) {
            with(binding) {
                tvPoint.text = siagaEntity.point
                tvKategori.text = siagaEntity.kategori
                tvDesk.text = siagaEntity.desk_point
                tvCaraPengujian.text = siagaEntity.cara_pengujian
                tvStatus.text = siagaEntity.status
                expandableLayout.findViewById<ConstraintLayout>(R.id.expandableLayout)
                cvItemSiaga.setOnClickListener {
                    val intent = Intent(activity, TambahSiagaActivity::class.java)
                    intent.putExtra(TambahSiagaActivity.EXTRA_POSITION, adapterPosition)
                    intent.putExtra(TambahSiagaActivity.EXTRA_SIAGA, siagaEntity)
                    activity.startActivityForResult(intent, TambahSiagaActivity.REQUEST_UPDATE)
                }


            }
        }
    }
}