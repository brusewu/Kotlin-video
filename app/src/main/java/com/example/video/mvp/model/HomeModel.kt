package com.example.video.mvp.model

import com.example.video.mvp.model.bean.HomeBean
import com.example.video.net.RetrofitManager
import com.example.video.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by bruse on 2019/7/2.
 */
class HomeModel {
    /**
     * 获取首页 Banner 数据
     */
    fun requestHomeData(num:Int):Observable<HomeBean>{
        return RetrofitManager.service.getFirstHomeData(num)
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加载更多
     */
    fun loadMoreData(url:String): Observable<HomeBean> {

        return RetrofitManager.service.getMoreHomeData(url)
            .compose(SchedulerUtils.ioToMain())
    }
}