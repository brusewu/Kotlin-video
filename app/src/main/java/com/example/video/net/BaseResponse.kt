package com.example.video.net

/**
 * Created by bruse on 2019/7/1.
 */
class BaseResponse<T>(val code:Int,
                      val msg:String,
                      val data:T)