package com.example.video.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.io.File
import java.lang.reflect.Field

/**
 * Created by bruse on 2019/6/28.
 */
object CleanLeakUtils {

    fun fixInputMethodManagerLeak(destContext: Context?) {
        if (destContext == null){
            return
        }

        val inputMethodManager = destContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        val viewArray = arrayOf("mCurRootView","mServedView","mNextServedView")
        var filed:Field
        var filedObject:Any?

        for (view in viewArray){
            try {
                filed = inputMethodManager.javaClass.getDeclaredField(view)
                if (!filed.isAccessible){
                    filed.isAccessible = true
                }
                filedObject = filed.get(inputMethodManager)
                if (filedObject != null && filedObject is View){
                    val fileView = filedObject as View?
                    if (fileView!!.context == destContext){
                        filed.set(inputMethodManager,null)
                    }else{
                        break
                    }
                }

            }catch (t:Throwable){
                t.printStackTrace()
            }
        }
    }
}