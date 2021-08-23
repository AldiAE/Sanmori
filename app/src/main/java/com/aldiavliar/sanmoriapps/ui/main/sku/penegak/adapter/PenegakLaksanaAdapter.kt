package com.aldiavliar.sanmoriapps.ui.main.sku.penegak.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.data.Entity.PenegakEntity
import com.aldiavliar.sanmoriapps.data.helper.PenegakDiffCallback
import com.aldiavliar.sanmoriapps.databinding.ItemSkuPenegakBinding
import com.aldiavliar.sanmoriapps.ui.main.sku.penegak.addUpdate.TambahPenegakActivity

class PenegakLaksanaAdapter internal constructor(private val activity: Activity) : RecyclerView.Adapter<PenegakLaksanaAdapter.PenegakViewHolder>() {
    private val listPenegakLaksana = ArrayList<PenegakEntity>()

    fun setListPenegakLaksana(listPenegakLaksana: List<PenegakEntity>) {
        val diffCallback = PenegakDiffCallback(this.listPenegakLaksana, listPenegakLaksana)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listPenegakLaksana.clear()
        this.listPenegakLaksana.addAll(listPenegakLaksana)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenegakViewHolder {
        val binding = ItemSkuPenegakBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PenegakViewHolder(binding)
    }
    override fun onBindViewHolder(holder: PenegakViewHolder, position: Int) {
        holder.bind(listPenegakLaksana[position])
    }
    override fun getItemCount(): Int {
        return listPenegakLaksana.size
    }
    inner class PenegakViewHolder(private val binding: ItemSkuPenegakBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(penegakEntity: PenegakEntity) {
            with(binding) {
                tvPoint.text = penegakEntity.point
                tvKategori.text = penegakEntity.kategori
                tvDesk.text = penegakEntity.desk_point
                tvCaraPengujian.text = penegakEntity.cara_pengujian
                tvStatus.text = penegakEntity.status
                expandableLayout.findViewById<ConstraintLayout>(R.id.expandableLayout)
                cvItemPenegak.setOnClickListener {
                    val intent = Intent(activity, TambahPenegakActivity::class.java)
                    intent.putExtra(TambahPenegakActivity.EXTRA_POSITION, adapterPosition)
                    intent.putExtra(TambahPenegakActivity.EXTRA_PENEGAK, penegakEntity)
                    activity.startActivityForResult(intent, TambahPenegakActivity.REQUEST_UPDATE)
                }


            }
        }
    }
}