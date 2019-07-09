package com.example.video.mvp.presenter

import com.example.video.base.BasePresenter
import com.example.video.mvp.contract.CategoryContract
import com.example.video.mvp.model.CategoryModel
import com.example.video.net.exception.ExceptionHandle

/**
 * Created by bruse on 2019/7/2.
 */
class CategoryPresenter : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {

    private val categoryModel: CategoryModel by lazy {
        CategoryModel()
    }

    /**
     * 获取分类
     */
    override fun getCategoryData() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = categoryModel.getCategoryData()
            .subscribe({ categoryList ->
                mRootView?.apply {
                    dismissLoading()
                    showCategory(categoryList)
                }
            }, { t ->
                mRootView?.apply {
                    //处理异常
                    showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                }

            })

        addSubscription(disposable)
    }
}