package com.example.flores20100651ef2023.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flores20100651ef2023.Models.ProductModel
import com.example.flores20100651ef2023.R

class productoAdapter(private var productoList: List<ProductModel>): RecyclerView.Adapter<productoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        val tvPrecio: TextView = itemView.findViewById(R.id.tvPrecio)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_producto, parent, false))
    }
    override fun getItemCount(): Int {
        return productoList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productoItem = productoList[position]
        holder.tvDescripcion.text = productoItem.descripcion.toString()
        holder.tvPrecio.text = productoItem.precio.toString()

    }

    fun setData(productoList: List<ProductModel>){
        this.productoList = productoList
        notifyDataSetChanged()
    }

}