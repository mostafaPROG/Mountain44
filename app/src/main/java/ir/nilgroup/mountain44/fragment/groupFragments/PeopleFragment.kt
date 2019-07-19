package ir.nilgroup.mountain44.fragment.groupFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.nilgroup.mountain44.Base.GroupData
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.adapter.GroupsAdapter

class PeopleFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterGroup: GroupsAdapter

    companion object {
        fun newInstance(): PeopleFragment = PeopleFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.group_fragment, container, false)

        val list:ArrayList<GroupData> = ArrayList()
        list.add(GroupData("گروه کوهنوردی 1"))
        list.add(GroupData("گروه کوهنوردی 2","12"))
        list.add(GroupData("گروه کوهنوردی 3","3000"))
        list.add(GroupData("گروه کوهنوردی 4","3"))
        list.add(GroupData("گروه کوهنوردی 5","78"))
        list.add(GroupData("گروه کوهنوردی 6","55"))


        recyclerView = view.findViewById(R.id.recyclerGroup)
        recyclerView.layoutManager= LinearLayoutManager(this.context)
        adapterGroup= GroupsAdapter(list,context!!)
        recyclerView.adapter=adapterGroup


        return view
    }
}