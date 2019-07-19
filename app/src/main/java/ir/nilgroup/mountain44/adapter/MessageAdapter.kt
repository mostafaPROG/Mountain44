package ir.nilgroup.mountain44.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.nilgroup.mountain44.Base.MessageData
import ir.nilgroup.mountain44.R

class MessageAdapter(private var arrayList: ArrayList<MessageData>? = null, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun submitList(list: ArrayList<MessageData>)  {
        arrayList = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.received_message, parent, false)
                ReceiveViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.sent_message, parent, false)
                SendViewHolder(view)
            }
        }
    }

/*
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = parent
        when (viewType) {
            1 -> {
            }
            0 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.sent_message, parent, false)
                var holder = ViewHolder(view)
                holder.receivedMessage = view.findViewById(R.id.sentMessage)
                holder.timeReceived = view.findViewById(R.id.timeSentMessage)
            }
        }

        return ViewHolder(view)
    }
*/

    override fun getItemCount(): Int = arrayList?.size?:0

    //    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.receivedMessage.text = arrayList[position].message
//        holder.timeReceived.text = arrayList[position].time
//        holder.nameReceiver.text = arrayList[position].nameSender
//    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = arrayList!![position]
        when (data.type) {
            1 -> {
                holder as ReceiveViewHolder
                holder.onBind(data, position)
            }
            else -> {
                holder as SendViewHolder
                holder.onBind(data, position)
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return arrayList!![position].type
    }

    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameReceiver:TextView = itemView.findViewById(R.id.nameReceiver)
        val messageReceiver:TextView = itemView.findViewById(R.id.receivedMessage)
        val timeReceiver:TextView = itemView.findViewById(R.id.timeReceivedMessage)

        fun onBind(data: MessageData, position: Int) {
            nameReceiver.text = data.nameSender
            messageReceiver.text = data.message
            timeReceiver.text = data.time
        }
    }

    class SendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageSender:TextView = itemView.findViewById(R.id.sentMessage)
        val timeSender:TextView = itemView.findViewById(R.id.timeSentMessage)

        fun onBind(data: MessageData, position: Int) {
            messageSender.text = data.message
            timeSender.text = data.time
        }
    }


}