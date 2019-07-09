package com.example.video.mvp.model.bean

/**
 * Created by bruse on 2019/7/2.
 */
data class TabInfoBean(val tabInfo: TabInfo) {
    data class TabInfo(val tabList: ArrayList<Tab>)

    data class Tab(val id: Long, val name: String, val apiUrl: String)
}