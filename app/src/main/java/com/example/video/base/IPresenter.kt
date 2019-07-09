package com.example.video.base

/**
 * Created by bruse on 2019/6/26.
 * 基类
 */
interface IPresenter<in V:IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()
}