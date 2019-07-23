package ir.nilgroup.mountain44.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.executor.GlideExecutor
import com.mikhaellopez.circularimageview.CircularImageView
import ir.nilgroup.mountain44.base.GroupData
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.activities.GroupProfileActivity

class GroupsAdapter(val arrayList: ArrayList<GroupData>, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val filterType = 0
    val itemType = 1

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameGroup: TextView = itemView.findViewById(R.id.nameCardGroup)
        val rank: TextView = itemView.findViewById(R.id.rankCardGroup)
        val member: TextView = itemView.findViewById(R.id.memCardGroup)
        val image:CircularImageView = itemView.findViewById(R.id.imageGroupCard)
    }

    class FilterViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val province: Button = itemView.findViewById(R.id.provinceText)
        val city: Button = itemView.findViewById(R.id.cityText)
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            filterType
        } else
            itemType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == filterType) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.filter_group_card, parent, false)
            return FilterViewholder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.group_card,parent,false)
            view.findViewById<CircularImageView>(R.id.imageGroupCard).setOnClickListener {
                context.startActivity(Intent(context,GroupProfileActivity::class.java))
            }
            return ItemViewholder(view)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewholder){
            holder.member.text = arrayList[position].members.toString()
            holder.rank.text=arrayList[position].rank.toString()
            holder.nameGroup.text=arrayList[position].nameId
            val stringBuilder = "p${(1..7).random()}"
            val id = context.resources.getIdentifier(stringBuilder, "drawable", context.packageName)
            Glide.with(context).load(id).into(holder.image)
            GlideExecutor.newAnimationExecutor()
        }
    }


}
