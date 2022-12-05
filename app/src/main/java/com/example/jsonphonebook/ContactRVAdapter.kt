package com.example.jsonphonebook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonphonebook.data.model.Names
import com.example.jsonphonebook.data.model.Phone

class ContactRVAdapter(context: Context?, val data:MutableList<Names>, private val data2:MutableList<Phone>): RecyclerView.Adapter<ContactRVAdapter.ContactViewHolder?>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    private var iClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view: View = layoutInflater.inflate(R.layout.items, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = data[position]
        val item2 = data2[position]
        holder.nameTextView.text = item.title
        holder.phoneTextView.text = item2.phone
    }

    override fun getItemCount(): Int = data.size

    inner class ContactViewHolder(item: View):RecyclerView.ViewHolder(item), View.OnClickListener{
        var nameTextView: TextView= item.findViewById(R.id.tvName)
        var phoneTextView: TextView = item.findViewById(R.id.tvPhone)
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(view: View?){
            iClickListener?.onItemClick(view, adapterPosition)
        }
    }

    fun setOnClickListener(itemClickListener: ItemClickListener?){
        iClickListener = itemClickListener
    }

    interface ItemClickListener{
        fun onItemClick(view: View?, position: Int)
    }

}