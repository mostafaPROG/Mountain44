package ir.nilgroup.mountain44.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ir.nilgroup.mountain44.Base.PersonData
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.activities.MessageListActivity

class PersonAdapter(val arrayList: ArrayList<PersonData>, val context: Context):RecyclerView.Adapter<PersonAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_card, parent, false)
        val constraintLayout:ConstraintLayout = view.findViewById(R.id.constraintPersonCard)
        constraintLayout.setOnClickListener {
            var position = parent.findViewById<RecyclerView>(R.id.recyclerPerson).getChildLayoutPosition(view)
            val intent = Intent(context.applicationContext, MessageListActivity::class.java)
                .putExtra("nameSender",arrayList[position].nameId)
                .putExtra("state",arrayList[position].state)
            context.startActivity(intent)

        }

        return ViewHolder(view)
    }


    override fun getItemCount(): Int = arrayList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = arrayList[position].nameId

    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val name:TextView = itemView.findViewById(R.id.nameCardPerson)
    }

}
