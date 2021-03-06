package ir.nilgroup.mountain44.fragment.groupProfileFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.nilgroup.mountain44.base.GalleryData
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.adapter.GalleryAdapter

class GalleryFragment : Fragment() {

    lateinit var adapter: GalleryAdapter
    lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance(): GalleryFragment = GalleryFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.gallery_fragment, container, false)
        var list: ArrayList<GalleryData> = ArrayList()
        val str = "بینالود";
        list.add(GalleryData(str, "1 ساعت پیش", 6))
        list.add(GalleryData(str, "2 روز پیش", 15))
        list.add(GalleryData(str, "1 هفته پیش", 20))
        val str2 = "1 سال پیش";
        list.add(GalleryData(str, str2, 10))
        list.add(GalleryData(str, str2, 23))
        list.add(GalleryData(str, str2, 11))

        recyclerView = view.findViewById(R.id.recyclerGalleryGroup)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = GalleryAdapter(list, context!!,0)
        recyclerView.adapter = adapter



        return view
    }
}

