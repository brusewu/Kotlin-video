package com.example.video.mvp.contract

import com.example.video.base.IBaseView
import com.example.video.base.IPresenter
import com.example.video.mvp.model.bean.CategoryBean

/**
 * Created by bruse on 2019/7/2.
 */
interface CategoryContract {

    interface View : IBaseView {
        /**
         * 显示分类的信息
         */
        fun showCategory(categoryList: ArrayList<CategoryBean>)

        /**
         * 显示错误信息
         */
        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter: IPresenter<View> {
        /**
         * 获取分类的信息
         */
        fun getCategoryData()
    }
}