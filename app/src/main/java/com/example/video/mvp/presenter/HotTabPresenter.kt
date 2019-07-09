package com.example.video.mvp.presenter

import com.example.video.base.BasePresenter
import com.example.video.mvp.contract.HotTabContract
import com.example.video.mvp.model.HotTabModel
import com.example.video.net.exception.ExceptionHandle

/**
 * Created by bruse on 2019/7/2.
 */
class HotTabPresenter : BasePresenter<HotTabContract.View>(), HotTabContract.Presenter {

    private val hotTabModel by lazy { HotTabModel() }


    override fun getTabInfo() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = hotTabModel.getTabInfo()
            .subscribe({
                    tabInfo->
                mRootView?.setTabInfo(tabInfo)
            },{
                    throwable->
                //处理异常
                mRootView?.showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
            })
        addSubscription(disposable)
    }
}