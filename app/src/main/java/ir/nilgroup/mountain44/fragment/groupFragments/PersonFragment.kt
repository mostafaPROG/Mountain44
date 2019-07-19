package ir.nilgroup.mountain44.fragment.groupFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.nilgroup.mountain44.Base.PersonData
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.adapter.PersonAdapter

class PersonFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterPerson: PersonAdapter

    companion object {
        fun newInstance(): PersonFragment = PersonFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.person_fragment, container, false)

        val list:ArrayList<PersonData> = ArrayList()
        list.add(PersonData("mohammad"))
        list.add(PersonData("mostafa"))
        list.add(PersonData("reza"))
        list.add(PersonData("amir"))

        recyclerView = view.findViewById(R.id.recyclerPerson)
        recyclerView.layoutManager=LinearLayoutManager(this.context)
        adapterPerson= PersonAdapter(list,context!!)
        recyclerView.adapter=adapterPerson


        return view
    }
}