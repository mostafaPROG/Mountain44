package ir.nilgroup.mountain44.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.nilgroup.mountain44.base.MessageData
import ir.nilgroup.mountain44.base.SenderState
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.adapter.MessageAdapter
import java.util.*


class MessageListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var receivedMessageAdapter: MessageAdapter
    private lateinit var backBtn:ImageButton

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_list)

        backBtn = findViewById(R.id.backMessage)
        backBtn.setOnClickListener {
            onBackPressed()
        }

        val nameSender = intent.getStringExtra("nameSender")
        val stateSender = intent.getStringExtra("state")

        val nameSenderToolbar:TextView = findViewById(R.id.toolbarNameSender)
        val stateSenderToolbar:TextView = findViewById(R.id.toolbarStateSender)
        val sendBtn:ImageButton = findViewById(R.id.sendMessageBtn)
        val editBox:EditText = findViewById(R.id.editTextMessage)

        nameSenderToolbar.text = nameSender
        stateSenderToolbar.text = stateSender
        val listReceived:ArrayList<MessageData> = ArrayList()
        listReceived.add(MessageData("سلام خوبی؟",nameSender, SenderState.Receiver.id))

        recyclerView = findViewById(R.id.recyclerViewMessage)
        recyclerView.layoutManager = LinearLayoutManager(this)
        receivedMessageAdapter = MessageAdapter(listReceived,this)
        recyclerView.adapter=receivedMessageAdapter

        sendBtn.setOnClickListener {
            if (editBox.text.isNotEmpty()){

                listReceived.add(MessageData(editBox.text.toString(),type = SenderState.Sender.id))
                receivedMessageAdapter= MessageAdapter(listReceived,this@MessageListActivity)
                recyclerView.adapter=receivedMessageAdapter
//                receivedMessageAdapter.notifyDataSetChanged()

//                receivedMessageAdapter.submitList(listReceived)
                editBox.text.clear()
            }
        }

    }
}
