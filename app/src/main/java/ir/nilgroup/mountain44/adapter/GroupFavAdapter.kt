package ir.nilgroup.mountain44.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ir.nilgroup.mountain44.base.GroupFavFragment
import ir.nilgroup.mountain44.base.MountFragment

class GroupFavAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

        override fun getItem(position: Int): Fragment {
            return GroupFavFragment.newInstance(position)
        }

        override fun getCount(): Int {
            return 3
        }
    }
