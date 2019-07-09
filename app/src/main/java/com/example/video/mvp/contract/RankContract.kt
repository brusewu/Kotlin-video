package com.example.video.mvp.contract

import com.example.video.base.IBaseView
import com.example.video.base.IPresenter
import com.example.video.mvp.model.bean.HomeBean

/**
 * Created by bruse on 2019/7/2.
 */
interface RankContract {

    interface View: IBaseView {
        /**
         * 设置排行榜的数据
         */
        fun setRankList(itemList: ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg:String,errorCode:Int)
    }


    interface Presenter: IPresenter<View> {
        /**
         * 获取 TabInfo
         */
        fun requestRankList(apiUrl:String)
    }
}