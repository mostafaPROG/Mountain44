package ir.nilgroup.mountain44.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.executor.GlideExecutor
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.base.EventData
import ir.nilgroup.mountain44.base.RegisterNumber

class EventAdapter(val arrayList: ArrayList<EventData>, val context: Context, val state: String) :
    RecyclerView.Adapter<EventAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_card, parent, false)
        view.findViewById<TextView>(R.id.eventNumReg).setOnClickListener {
            showDialogRegister()
        }
        return ViewHolder(view)
    }


    override fun getItemCount(): Int = arrayList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.eventName.text = arrayList[position].eventName
        holder.eventDate.text = arrayList[position].date
        holder.eventNum.text = arrayList[position].numberRegisters.toString()
        holder.eventState.text = state
        val stringBuilder = "event_back"
        val id = context.resources.getIdentifier(stringBuilder, "drawable", context.packageName)
        Glide.with(context).load(id).into(holder.eventImage)
        GlideExecutor.newAnimationExecutor()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventName: TextView = itemView.findViewById(R.id.eventName)
        val eventNum: TextView = itemView.findViewById(R.id.eventNumReg)
        val eventState: TextView = itemView.findViewById(R.id.eventState)
        val eventDate: TextView = itemView.findViewById(R.id.eventDate)
        val eventImage: ImageView = itemView.findViewById(R.id.eventImage)
    }

    fun showDialogRegister() {
        val mDialog = LayoutInflater.from(context).inflate(R.layout.table_number_register, null)
        val builder = AlertDialog.Builder(context).setView(mDialog)
        val recyclerView: RecyclerView = mDialog.findViewById(R.id.recyclerNumRegister)
        val list: ArrayList<RegisterNumber> = ArrayList()
        list.add(RegisterNumber("مصطفی", "قربانزاده", "۰۹۱۵۲۴۴۲۹۲۵", true))
        list.add(RegisterNumber("مصطفی", "قربانزاده", "۰۹۱۵۲۴۴۲۹۲۵", true))
        list.add(RegisterNumber("مصطفی", "قربانزاده", "۰۹۱۵۲۴۴۲۹۲۵", true))
        list.add(RegisterNumber("مصطفی", "قربانزاده", "۰۹۱۵۲۴۴۲۹۲۵", true))
        list.add(RegisterNumber("مصطفی", "قربانزاده", "۰۹۱۵۲۴۴۲۹۲۵", false))
        list.add(RegisterNumber("مصطفی", "قربانزاده", "۰۹۱۵۲۴۴۲۹۲۵", true))
        list.add(RegisterNumber("مصطفی", "قربانزاده", "۰۹۱۵۲۴۴۲۹۲۵", true))
        list.add(RegisterNumber("مصطفی", "قربانزاده", "۰۹۱۵۲۴۴۲۹۲۵", false))
        list.add(RegisterNumber("مصطفی", "قربانزاده", "۰۹۱۵۲۴۴۲۹۲۵", false))
        list.add(RegisterNumber("مصطفی", "قربانزاده", "۰۹۱۵۲۴۴۲۹۲۵", true))
        list.add(RegisterNumber("مصطفی", "قربانزاده", "۰۹۱۵۲۴۴۲۹۲۵", false))
        list.add(RegisterNumber("مصطفی", "قربانزاده", "۰۹۱۵۲۴۴۲۹۲۵", true))
        list.add(RegisterNumber("مصطفی", "قربانزاده", "۰۹۱۵۲۴۴۲۹۲۵", true))
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = TableViewAdapter(list, context)
        builder.show()
        builder.setCancelable(true)
    }


}
