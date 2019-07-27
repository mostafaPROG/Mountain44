package ir.nilgroup.mountain44.fragment.profileFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.adapter.EventAdapter
import ir.nilgroup.mountain44.base.EventData

class RuningEvent : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter:EventAdapter
    companion object {
        fun newInstance(): RuningEvent = RuningEvent()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.introduction_group, container, false)
        val list:ArrayList<EventData> = ArrayList()
        list.add(EventData("تور 3.5 روزه دره نوردی تنگه رغز","۲۸ مرداد تا ۳۱ مرداد",18))
        list.add(EventData("تور 3.5 روزه دره نوردی تنگه رغز","۵ شهریور به ۸ شهریور ماه",18))
        list.add(EventData("تور 3.5 روزه دره نوردی تنگه رغز","۵ شهریور به ۸ شهریور ماه",18))
        list.add(EventData("تور 3.5 روزه دره نوردی تنگه رغز","۵ شهریور به ۸ شهریور ماه",18))

        adapter = EventAdapter(list,context!!,"درحال اجرا")
        recyclerView = view.findViewById(R.id.recyclerEvent)
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
        recyclerView.adapter  = adapter
        return view
    }

}

