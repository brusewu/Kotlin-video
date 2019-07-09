package com.example.video.view.recyclerview

/**
 * Created by bruse on 2019/7/2.
 */
interface MultipleType<in T> {
    fun getLayoutId(item: T, position: Int): Int

}