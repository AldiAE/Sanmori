package com.aldiavliar.sanmoriapps.ui.main.sku.penggalang.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.data.Entity.PenggalangEntity
import com.aldiavliar.sanmoriapps.data.helper.PenggalangDiffCallback
import com.aldiavliar.sanmoriapps.databinding.ItemSkuPenggalangBinding
import com.aldiavliar.sanmoriapps.ui.main.sku.penggalang.addUpdate.TambahPenggalangActivity


class PenggalangTerapAdapter internal constructor(private val activity: Activity) : RecyclerView.Adapter<PenggalangTerapAdapter.PenggalangViewHolder>() {
    private val listPenggalangTerap= ArrayList<PenggalangEntity>()

    fun setListPenggalangTerap(listPenggalangTerap: List<PenggalangEntity>) {
        val diffCallback = PenggalangDiffCallback(this.listPenggalangTerap, listPenggalangTerap)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listPenggalangTerap.clear()
        this.listPenggalangTerap.addAll(listPenggalangTerap)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenggalangViewHolder {
        val binding = ItemSkuPenggalangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PenggalangViewHolder(binding)
    }
    override fun onBindViewHolder(holder: PenggalangViewHolder, position: Int) {
        holder.bind(listPenggalangTerap[position])
    }
    override fun getItemCount(): Int {
        return listPenggalangTerap.size
    }
    inner class PenggalangViewHolder(private val binding: ItemSkuPenggalangBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(penggalangEntity: PenggalangEntity) {
            with(binding) {
                tvPoint.text = penggalangEntity.point
                tvKategori.text = penggalangEntity.kategori
                tvDesk.text = penggalangEntity.desk_point
                tvCaraPengujian.text = penggalangEntity.cara_pengujian
                tvStatus.text = penggalangEntity.status
                expandableLayout.findViewById<ConstraintLayout>(R.id.expandableLayout)
                cvItemPenggalang.setOnClickListener {
                    val intent = Intent(activity, TambahPenggalangActivity::class.java)
                    intent.putExtra(TambahPenggalangActivity.EXTRA_POSITION, adapterPosition)
                    intent.putExtra(TambahPenggalangActivity.EXTRA_PENGGALANG, penggalangEntity)
                    activity.startActivityForResult(intent, TambahPenggalangActivity.REQUEST_UPDATE)
                }


            }
        }
    }
}