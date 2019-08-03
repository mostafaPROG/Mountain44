package ir.nilgroup.mountain44.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.executor.GlideExecutor
import ir.nilgroup.mountain44.base.GalleryData
import ir.nilgroup.mountain44.R

class GalleryAdapter(val arrayList: ArrayList<GalleryData>, val context: Context, val type: Int) :
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (type) {
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_card2, parent, false)
                ViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_card, parent, false)
                ViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int = arrayList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = arrayList[position].title
        holder.number.text = arrayList[position].number.toString()
        holder.time.text = arrayList[position].time
        val stringBuilder = "p${(1..7).random()}"
        val id = context.resources.getIdentifier(stringBuilder, "drawable", context.packageName)
        Glide.with(context).load(id).into(holder.image)
        GlideExecutor.newAnimationExecutor()

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageGalleryGroup)
        val title: TextView = itemView.findViewById(R.id.titleGalleryGroup)
        val time: TextView = itemView.findViewById(R.id.timeGalleryGroup)
        val number: TextView = itemView.findViewById(R.id.numberGalleryGroup)
    }

}
