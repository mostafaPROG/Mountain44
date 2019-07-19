package ir.nilgroup.mountain44.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabAdapter(fragmentManager: FragmentManager):FragmentStatePagerAdapter(fragmentManager) {

    private val mFragmentList:ArrayList<Fragment> = ArrayList()
    private val mFragmentListTitle:ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
    return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment,title:String){
        mFragmentList.add(fragment)
        mFragmentListTitle.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return null
    }

    fun getTitle(position:Int):String = mFragmentListTitle[position]


}