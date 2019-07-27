package ir.nilgroup.mountain44.fragment.profileFragment

import android.icu.lang.UCharacter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.adapter.MemberAdapter
import ir.nilgroup.mountain44.base.PersonData

class MemberProfileFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MemberAdapter

    companion object {
        fun newInstance(): MemberProfileFragment = MemberProfileFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.member_layout, container, false)

        val list:ArrayList<PersonData> = ArrayList()
        list.add(PersonData("محمد","online"))
        list.add(PersonData("رضا","online"))
        list.add(PersonData("علی","online"))
        list.add(PersonData("مجید","online"))
        list.add(PersonData("مصطفی","offline"))

        recyclerView = view.findViewById(R.id.recyclerMember)
        recyclerView.layoutManager= LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
        adapter= MemberAdapter(list,context!!)
        recyclerView.adapter=adapter

        return view
    }

}

