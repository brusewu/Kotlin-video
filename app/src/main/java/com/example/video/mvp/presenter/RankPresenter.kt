package com.example.video.mvp.presenter

import com.example.video.base.BasePresenter
import com.example.video.mvp.contract.RankContract
import com.example.video.mvp.model.RankModel
import com.example.video.net.exception.ExceptionHandle

/**
 * Created by bruse on 2019/7/2.
 */
class RankPresenter : BasePresenter<RankContract.View>(), RankContract.Presenter {

    private val rankModel by lazy { RankModel() }


    /**
     *  请求排行榜数据
     */
    override fun requestRankList(apiUrl: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = rankModel.requestRankList(apiUrl)
            .subscribe({ issue ->
                mRootView?.apply {
                    dismissLoading()
                    setRankList(issue.itemList)
                }
            }, { throwable ->
                mRootView?.apply {
                    //处理异常
                    showError(ExceptionHandle.handleException(throwable),ExceptionHandle.errorCode)
                }
            })
        addSubscription(disposable)
    }
}