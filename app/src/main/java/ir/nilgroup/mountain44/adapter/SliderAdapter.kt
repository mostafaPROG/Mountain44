package ir.nilgroup.mountain44.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.base.SliderData
import ir.nilgroup.mountain44.databinding.ActivityMountBinding
import kotlinx.android.synthetic.main.activity_mount.view.*

private lateinit var root:ActivityMountBinding

class SliderAdapter(private val context:Context,private val sliderList:List<SliderData>):SliderViewAdapter<SliderAdapter.SliderVH>() {

    class SliderVH(itemView:View):SliderViewAdapter.ViewHolder(itemView){
       val image:ImageView=itemView.findViewById(R.id.imageSliderMount)
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderVH =
        SliderVH(LayoutInflater.from(parent!!.context).inflate(R.layout.image_slider_layout,parent,false))

    override fun getCount(): Int = sliderList.size

    override fun onBindViewHolder(viewHolder: SliderVH?, position: Int) {
        val sliderItem:SliderData = sliderList[position]
        Glide.with(viewHolder!!.itemView)
            .load(sliderItem.image)
            .fitCenter()
            .into(viewHolder.image)
    }
}