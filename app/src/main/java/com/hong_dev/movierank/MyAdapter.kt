package com.hong_dev.movierank

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val context: Context, var items: List<DailyBoxOffice>): RecyclerView.Adapter<MyAdapter.VH>(){

    inner class VH constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val rank: TextView by lazy {itemView.findViewById(R.id.rank)}
        val movieNm: TextView by lazy {itemView.findViewById(R.id.movieNm)}
        val openDt: TextView by lazy {itemView.findViewById(R.id.openDt)}
        val audiAcc: TextView by lazy {itemView.findViewById(R.id.audiAcc)}

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)
        val itemView:View = layoutInflater.inflate(R.layout.movie_item, parent, false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item:DailyBoxOffice = items.get(position)

        holder.rank.text = item.rank
        holder.movieNm.text = item.movieNm
        holder.openDt.text = item.openDt
        holder.audiAcc.text = item.audiAcc}

    override fun getItemCount(): Int = items.size

}
