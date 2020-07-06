package com.keyike.website.ui.ui.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.keyike.website.R
import org.w3c.dom.Text

class CommonAdapter(val context: Context):RecyclerView.Adapter<CommonAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_common,parent,false))
    }

    override fun getItemCount() = 3

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position % 2 == 0){
            holder.itemView.setBackgroundColor(context.resources.getColor(R.color.colorCommonBg))
        }else{
            holder.itemView.setBackgroundColor(context.resources.getColor(R.color.colorWhite))
        }
    }
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val img_head = view.findViewById<ImageView>(R.id.img_head)
        val tv_name = view.findViewById<TextView>(R.id.tv_name)
        val tv_sex = view.findViewById<TextView>(R.id.tv_sex)
        val tv_age = view.findViewById<TextView>(R.id.tv_age)
        val tv_height = view.findViewById<TextView>(R.id.tv_height)
        val tv_weight = view.findViewById<TextView>(R.id.tv_weight)
        val tv_shoeSize = view.findViewById<TextView>(R.id.tv_shoeSize)

    }
}