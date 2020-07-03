package com.keyike.website.ui.ui.fragment

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.keyike.website.R
const val TYPE_CHANNEL = 0
const val TYPE_GAME = 1
class PriceAdapter(var list:List<String>,val context:Activity,val type:Int) :RecyclerView.Adapter<PriceAdapter.ViewHolder>(){
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val iv_content:ImageView = view.findViewById(R.id.iv_content)
        val tv_title:TextView = view.findViewById(R.id.tv_title)
        val tv_content:TextView = view.findViewById(R.id.tv_content)
        val tv_more:TextView = view.findViewById(R.id.tv_more)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(context.layoutInflater.inflate(R.layout.item_price,parent,false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            this.iv_content.setImageResource(R.mipmap.ic_launcher)
            if(type == TYPE_CHANNEL){
                this.tv_more.text = "MORE  >"
            }else if(type == TYPE_GAME){
                this.tv_more.text = "了解详情  >"
            }
//            this.tv_title.text = "标题"
        }
    }
}