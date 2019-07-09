package com.example.video.mvp.model

import com.example.video.mvp.model.bean.HomeBean
import com.example.video.net.RetrofitManager
import com.example.video.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by bruse on 2019/7/2.
 */
class VideoDetailModel {
    fun requestRelatedData(id:Long): Observable<HomeBean.Issue> {

        return RetrofitManager.service.getRelatedData(id)
            .compose(SchedulerUtils.ioToMain())
    }
}