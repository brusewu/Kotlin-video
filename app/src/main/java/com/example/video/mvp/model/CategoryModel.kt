package com.example.video.mvp.model

import com.example.video.mvp.model.bean.CategoryBean
import com.example.video.net.RetrofitManager
import com.example.video.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by bruse on 2019/7/2.
 */
class CategoryModel {

    /**
     * 获取分类信息
     */
    fun getCategoryData(): Observable<ArrayList<CategoryBean>> {
        return RetrofitManager.service.getCategory()
            .compose(SchedulerUtils.ioToMain())
    }
}