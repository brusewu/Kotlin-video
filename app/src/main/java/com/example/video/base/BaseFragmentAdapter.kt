package com.example.video.base

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by bruse on 2019/6/26.
 */
class BaseFragmentAdapter : FragmentPagerAdapter {
    private var mTitles:List<String>? = null
    private var fragmentList:List<Fragment>? = ArrayList()

    constructor(fm:FragmentManager,fragmentList:List<Fragment>,mTitles:List<String>):super(fm){
        this.mTitles = mTitles
        setFragments(fm,fragmentList,mTitles)
    }

    //刷新fragment
    @SuppressLint("CommitTransaction")
    private fun setFragments(fm: FragmentManager,fragments:List<Fragment>,mTitles: List<String>){
        this.mTitles = mTitles
        if (this.fragmentList != null){
            val ft = fm.beginTransaction()
            fragmentList?.forEach {
                ft.remove(it)
            }
            ft?.commitAllowingStateLoss()
            fm.executePendingTransactions()
        }
        this.fragmentList = fragments
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (null != mTitles)mTitles!![position] else ""
    }
    override fun getItem(p0: Int): Fragment {

        return fragmentList !![p0]
    }

    override fun getCount(): Int {
        return fragmentList!!.size
    }
}