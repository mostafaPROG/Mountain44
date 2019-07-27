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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.activities.CourseSing
import ir.nilgroup.mountain44.activities.MessageListActivity
import ir.nilgroup.mountain44.base.PersonData

class MemberAdapter(private val member: List<PersonData>, private val context: Context) :
    RecyclerView.Adapter<MemberAdapter.Viewholder>() {


    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val state: TextView = itemView.findViewById(R.id.stateMember)
        val chat: FloatingActionButton = itemView.findViewById(R.id.chatByMember)
        val title: TextView = itemView.findViewById(R.id.nameMember)
        val image:ImageView = itemView.findViewById(R.id.imageMember)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberAdapter.Viewholder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.member_card, parent, false)
            val chat = view.findViewById<FloatingActionButton>(R.id.chatByMember)
            chat.setOnClickListener {
                var position = parent.findViewById<RecyclerView>(R.id.recyclerMember).getChildLayoutPosition(view)
                val intent = Intent(context, MessageListActivity::class.java)
                    .putExtra("state", member[position].state)
                    .putExtra("nameSender",member[position].nameId)
                context.startActivity(intent)
            }
            return Viewholder(view)
    }

    override fun getItemCount(): Int {
        return member.size
    }

    override fun onBindViewHolder(holder: MemberAdapter.Viewholder, position: Int) {
            holder.state.text = member[position].state
            holder.title.text = member[position].nameId
            val stringBuilder = "p${(1..7).random()}"
            val id = context.resources.getIdentifier(stringBuilder, "drawable", context.packageName)
            Glide.with(context).load(id).into(holder.image)
            GlideExecutor.newAnimationExecutor()
    }
}