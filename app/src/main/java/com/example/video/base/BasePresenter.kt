package com.example.video.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.RuntimeException

/**
 * Created by bruse on 2019/6/26.
 */
open class BasePresenter <T : IBaseView> : IPresenter<T>{
    var mRootView: T? = null
    private set

    private var compositeDisposable = CompositeDisposable()

    override fun attachView(mRootView: T) {
        this.mRootView = mRootView
    }

    override fun detachView() {
        mRootView = null

        //保证activity结束取消所有正在执行的订阅
        if (!compositeDisposable.isDisposed){
            compositeDisposable.clear()
        }
    }

    private val isViewAttached:Boolean
    get() = mRootView != null

    fun checkViewAttached(){
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    fun addSubscription(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    private class MvpViewNotAttachedException internal  constructor():RuntimeException("Please call IPresenter.attachView(IBaseView) before\" + \" requesting data to the IPresenter ")


}