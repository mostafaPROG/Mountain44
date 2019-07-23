package ir.nilgroup.mountain44.fragment.groupFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.nilgroup.mountain44.base.GroupData
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.adapter.GroupsAdapter

class TeamFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterGroup: GroupsAdapter

    companion object {
        fun newInstance(): TeamFragment = TeamFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.group_fragment, container, false)

        val list:ArrayList<GroupData> = ArrayList()
        list.add(GroupData("گروه کوهنوردی 1",216,1))
        list.add(GroupData("گروه کوهنوردی 2",12,2))
        list.add(GroupData("گروه کوهنوردی 3",13,3))
        list.add(GroupData("گروه کوهنوردی 4",3,4))
        list.add(GroupData("گروه کوهنوردی 5",78,6))
        list.add(GroupData("گروه کوهنوردی 6",55,5))


        recyclerView = view.findViewById(R.id.recyclerGroup)
        recyclerView.layoutManager= LinearLayoutManager(this.context)
        adapterGroup= GroupsAdapter(list,context!!)
        recyclerView.adapter=adapterGroup


        return view
    }
}