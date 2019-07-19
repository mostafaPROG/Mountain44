package ir.nilgroup.mountain44.Base

import java.text.SimpleDateFormat
import java.util.*

class MessageData(val message:String, val nameSender:String? = null, val type : Int){
    var  time:String
    init {
        val df = SimpleDateFormat("h:m a")
        time = df.format(Calendar.getInstance().time)
    }
}