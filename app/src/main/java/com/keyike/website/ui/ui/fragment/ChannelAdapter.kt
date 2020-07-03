package com.keyike.website.ui.ui.fragment

import android.app.Activity
import android.opengl.Visibility
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.keyike.website.R
const val TYPE_HOME = 0
const val TYPE_BABY = 1
const val TYPE_SCHOOL = 2
class ChannelAdapter(var list:List<String>,val context: Activity,val type:Int) :RecyclerView.Adapter<ChannelAdapter.ViewHolder>() {



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var img_head:ImageView = view.findViewById(R.id.img_head)
        var tv_name:TextView = view.findViewById(R.id.tv_name)
        var tv_count:TextView = view.findViewById(R.id.tv_count)
        var ll_content:LinearLayout = view.findViewById(R.id.ll_content)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelAdapter.ViewHolder {
        return ViewHolder(context.layoutInflater.inflate(R.layout.item_babay,parent,false))
    }

    override fun getItemCount() = list.size*3

    override fun onBindViewHolder(holder: ChannelAdapter.ViewHolder, position: Int) {
        with(holder){
            this.img_head.setImageResource(R.mipmap.ic_launcher)
            this.tv_count.text = "1000"
            this.tv_name.text = "juggist"
            if(type == TYPE_HOME){
                this.ll_content.visibility = VISIBLE
            }else if(type == TYPE_BABY ||type == TYPE_SCHOOL){
                this.ll_content.visibility = GONE
            }
        }

    }
}