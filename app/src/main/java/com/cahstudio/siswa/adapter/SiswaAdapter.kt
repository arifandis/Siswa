package com.cahstudio.siswa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cahstudio.siswa.R
import com.cahstudio.siswa.datasource.model.Siswa
import kotlinx.android.synthetic.main.item_siswa.view.*

class SiswaAdapter(var context: Context, var siswaList: List<Siswa>, var edit: (Siswa) -> Unit,
                   var delete: (Siswa) -> Unit):
    RecyclerView.Adapter<SiswaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiswaAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_siswa,parent,false))
    }

    override fun getItemCount(): Int = siswaList.size

    override fun onBindViewHolder(holder: SiswaAdapter.ViewHolder, position: Int) {
        holder.bindItem(siswaList[position],edit,delete)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvName = view.itemsiswa_tvName
        val btnEdit = view.itemsiswa_btnEdit
        val btnDelete = view.itemsiswa_btnDelete

        fun bindItem(siswa: Siswa, edit: (Siswa) -> Unit, delete: (Siswa) -> Unit){
            tvName.text = siswa.name

            btnEdit.setOnClickListener {
                edit(siswa)
            }

            btnDelete.setOnClickListener {
                delete(siswa)
            }
        }
    }
}