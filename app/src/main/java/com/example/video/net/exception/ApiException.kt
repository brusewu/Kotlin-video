package com.example.video.net.exception

import java.lang.RuntimeException

/**
 * Created by bruse on 2019/7/1.
 */
class ApiException : RuntimeException {
    private var code : Int? = null
    constructor(throwable: Throwable,code:Int):super(throwable){
        this.code = code
    }

    constructor(message:String):super(Throwable(message))
}