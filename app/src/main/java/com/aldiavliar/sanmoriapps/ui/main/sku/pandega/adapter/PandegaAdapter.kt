package com.aldiavliar.sanmoriapps.ui.main.sku.pandega.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.data.Entity.PandegaEntity
import com.aldiavliar.sanmoriapps.data.helper.PandegaDiffCallback
import com.aldiavliar.sanmoriapps.databinding.ItemSkuPandegaBinding
import com.aldiavliar.sanmoriapps.ui.main.sku.pandega.addUpdate.TambahPandegaActivity

class PandegaAdapter internal constructor(private val activity: Activity) : RecyclerView.Adapter<PandegaAdapter.PandegaViewHolder>() {
    private val listPandega = ArrayList<PandegaEntity>()

    fun setListPandega(listPandega: List<PandegaEntity>) {
        val diffCallback = PandegaDiffCallback(this.listPandega, listPandega)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listPandega.clear()
        this.listPandega.addAll(listPandega)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PandegaViewHolder {
        val binding = ItemSkuPandegaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PandegaViewHolder(binding)
    }
    override fun onBindViewHolder(holder: PandegaViewHolder, position: Int) {
        holder.bind(listPandega[position])
    }
    override fun getItemCount(): Int {
        return listPandega.size
    }
    inner class PandegaViewHolder(private val binding: ItemSkuPandegaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pandegaEntity: PandegaEntity) {
            with(binding) {
                tvPoint.text = pandegaEntity.point
                tvKategori.text = pandegaEntity.kategori
                tvDesk.text = pandegaEntity.desk_point
                tvCaraPengujian.text = pandegaEntity.cara_pengujian
                tvStatus.text = pandegaEntity.status
                expandableLayout.findViewById<ConstraintLayout>(R.id.expandableLayout)
                cvItemPandega.setOnClickListener {
                    val intent = Intent(activity, TambahPandegaActivity::class.java)
                    intent.putExtra(TambahPandegaActivity.EXTRA_POSITION, adapterPosition)
                    intent.putExtra(TambahPandegaActivity.EXTRA_PANDEGA, pandegaEntity)
                    activity.startActivityForResult(intent, TambahPandegaActivity.REQUEST_UPDATE)
                }


            }
        }
    }
}