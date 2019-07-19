package ir.nilgroup.mountain44.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ir.nilgroup.mountain44.Base.GroupData
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.activities.MessageGroupActivity

class GroupsAdapter(val arrayList: ArrayList<GroupData>, val context: Context):RecyclerView.Adapter<GroupsAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.group_card, parent, false)
        val constraintLayout:ConstraintLayout = view.findViewById(R.id.constraintGroupCard)
        constraintLayout.setOnClickListener {
            var position = parent.findViewById<RecyclerView>(R.id.recyclerGroup).getChildLayoutPosition(view)
            val intent = Intent(context.applicationContext, MessageGroupActivity::class.java)
                .putExtra("nameGroup",arrayList[position].nameId)
                .putExtra("member",arrayList[position].members)
            context.startActivity(intent)
        }

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = arrayList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = arrayList[position].nameId
        holder.member.text = arrayList[position].members

    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val name:TextView = itemView.findViewById(R.id.nameCardGroup)
        val member:TextView = itemView.findViewById(R.id.stateCardGroup)
    }

}
