package com.example.video.mvp.model

import com.example.video.mvp.model.bean.TabInfoBean
import com.example.video.net.RetrofitManager
import com.example.video.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by bruse on 2019/7/2.
 */
class HotTabModel {
    /**
     * 获取 TabInfo
     */
    fun getTabInfo(): Observable<TabInfoBean> {

        return RetrofitManager.service.getRankList()
            .compose(SchedulerUtils.ioToMain())
    }
}