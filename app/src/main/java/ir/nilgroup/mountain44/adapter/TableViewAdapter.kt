package ir.nilgroup.mountain44.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.base.RegisterNumber

class TableViewAdapter(val list: ArrayList<RegisterNumber>,val context: Context) : RecyclerView.Adapter<TableViewAdapter.RowViewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_num_register, parent, false)

        return RowViewholder(view)
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: RowViewholder, position: Int) {
        when (holder.adapterPosition) {
            0 -> {
                holder.name.text = "نام"
                holder.family.text = "نام خانوادگی"
                holder.phone.text = "شماره تماس"
                holder.cost.text = "هزینه"
            }
            else -> {
                holder.name.typeface= Typeface.create("font/iransansweb_medium.ttf",Typeface.NORMAL)
                holder.family.typeface= Typeface.create("font/iransansweb_medium.ttf",Typeface.NORMAL)
                holder.phone.typeface= Typeface.create("font/iransansweb_medium.ttf",Typeface.NORMAL)
                holder.cost.typeface= Typeface.create("font/iransansweb_medium.ttf",Typeface.NORMAL)

                holder.name.setTextColor(ContextCompat.getColor(context,R.color.gray))
                holder.family.setTextColor(ContextCompat.getColor(context,R.color.gray))
                holder.cost.setTextColor(ContextCompat.getColor(context,R.color.gray))
                holder.phone.setTextColor(ContextCompat.getColor(context,R.color.gray))


                holder.name.text = list[position-1].name
                holder.family.text = list[position-1].family
                holder.phone.text = list[position-1].phone
                if (list[position-1].cost)
                    holder.cost.text = "پرداخت شده"
                else
                    holder.cost.text = "پرداخت نشده"
            }
        }
    }

    class RowViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.registerName)
        val family: TextView = itemView.findViewById(R.id.registerFamily)
        val phone: TextView = itemView.findViewById(R.id.registerPhone)
        val cost: TextView = itemView.findViewById(R.id.registerCost)
    }


}
