package ir.nilgroup.mountain44.activities

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

class MessageGroupActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var receivedMessageAdapter: MessageAdapter
    private lateinit var backBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_group)


        backBtn = findViewById(R.id.backMessageGroup)
        backBtn.setOnClickListener {
            onBackPressed()
        }

        val nameGroup = intent.getStringExtra("nameGroup")
        val memberGroup = intent.getStringExtra("member")

        val nameGroupToolbar: TextView = findViewById(R.id.toolbarNameGroup)
        val memberGroupToolbar: TextView = findViewById(R.id.toolbarMemberGroup)
        val sendBtn:ImageButton = findViewById(R.id.sendMessageBtnGroup)
        val editBox: EditText = findViewById(R.id.editTextMessageGroup)

        nameGroupToolbar.text = nameGroup
        memberGroupToolbar.text = memberGroup
        val listReceived: ArrayList<MessageData> = ArrayList()
        listReceived.add(MessageData("سلام","Ali", SenderState.Receiver.id))
        listReceived.add(MessageData("سلام  علی خوبی؟","Reza", SenderState.Receiver.id))
        listReceived.add(MessageData("خوبم","Ali", SenderState.Receiver.id))
        listReceived.add(MessageData("خداروشکر","Reza", SenderState.Receiver.id))

        recyclerView = findViewById(R.id.recyclerViewMessageGroup)
        recyclerView.layoutManager = LinearLayoutManager(this)
        receivedMessageAdapter = MessageAdapter(listReceived,this)
        recyclerView.adapter=receivedMessageAdapter

        sendBtn.setOnClickListener {
            if (editBox.text.isNotEmpty()){

                listReceived.add(MessageData(editBox.text.toString(),type = SenderState.Sender.id))
                receivedMessageAdapter= MessageAdapter(listReceived,this@MessageGroupActivity)
                recyclerView.adapter=receivedMessageAdapter
//                receivedMessageAdapter.notifyDataSetChanged()

//                receivedMessageAdapter.submitList(listReceived)
                editBox.text.clear()
            }
        }

    }
}
