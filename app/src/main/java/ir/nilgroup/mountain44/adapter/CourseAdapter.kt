package ir.nilgroup.mountain44.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.executor.GlideExecutor
import ir.nilgroup.mountain44.base.Course
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.activities.CourseSing

class CourseAdapter(private val course: List<Course>, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING  = 1

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val description: TextView = itemView.findViewById(R.id.descriptCourseCard)
        val card: ImageView = itemView.findViewById(R.id.cardCourseClicked)
        val title: TextView = itemView.findViewById(R.id.titleCardCourse)
    }

    class LoadingViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val progressBar:ProgressBar = itemView.findViewById(R.id.progressItem)
    }

    override fun getItemViewType(position: Int): Int {
        return if (course[position].description == ""){
            VIEW_TYPE_LOADING
        }else{
            VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ITEM){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.cours_card, parent, false)
            val image = view.findViewById<ImageView>(R.id.cardCourseClicked)
            image.setOnClickListener {
                var position = parent.findViewById<RecyclerView>(R.id.recyclerCourse).getChildLayoutPosition(view)
                val intent = Intent(context, CourseSing::class.java)
                    .putExtra("state", course[position].description)
                    .putExtra("pic", (1..7).random())
                    .putExtra("title",course[position].title)
                context.startActivity(intent)
            }
            return Viewholder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            return LoadingViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return course.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Viewholder){
            holder.description.text = course[position].description
            holder.title.text = course[position].title
            val stringBuilder = "p${(1..7).random()}"
            val id = context.resources.getIdentifier(stringBuilder, "drawable", context.packageName)
            Glide.with(context).load(id).into(holder.card)
            GlideExecutor.newAnimationExecutor()
        }else if(holder is LoadingViewHolder){
            holder.progressBar.isIndeterminate = true
        }
    }
}