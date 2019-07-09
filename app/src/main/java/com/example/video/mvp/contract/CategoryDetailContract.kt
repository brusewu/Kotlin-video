package com.example.video.mvp.contract

import com.example.video.base.IBaseView
import com.example.video.base.IPresenter
import com.example.video.mvp.model.bean.HomeBean

/**
 * Created by bruse on 2019/7/2.
 */
interface CategoryDetailContract {
    interface View: IBaseView {
        /**
         *  设置列表数据
         */
        fun setCateDetailList(itemList:ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg:String)




    }

    interface Presenter: IPresenter<View> {

        fun getCategoryDetailList(id:Long)

        fun loadMoreData()
    }
}