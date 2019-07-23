package ir.nilgroup.mountain44.fragment.groupProfileFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import ir.nilgroup.mountain44.R

class InfoGroupFragment : Fragment() {

    private lateinit var more: ImageButton
    private lateinit var text: TextView
    private var expand = true

    companion object {
        fun newInstance(): InfoGroupFragment = InfoGroupFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.info_fragment, container, false)
        text = view.findViewById(R.id.textInfo)
        more = view.findViewById(R.id.moreButton)
        more.setOnClickListener {
            if (expand){
                text.maxLines= Int.MAX_VALUE
                more.setImageResource(R.drawable.ic_expand_less_black_24dp)
                expand = false
            }else{
                text.maxLines=2
                more.setImageResource(R.drawable.ic_expand_more_black_24dp)
                expand = true
            }
        }
        text.setOnClickListener {
            if (expand){
                text.maxLines= Int.MAX_VALUE
                more.setImageResource(R.drawable.ic_expand_less_black_24dp)
                expand = false
            }else{
                text.maxLines=2
                more.setImageResource(R.drawable.ic_expand_more_black_24dp)
                expand = true
            }
        }

        return view
    }
}