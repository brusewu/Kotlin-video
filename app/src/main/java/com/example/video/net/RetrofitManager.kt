package com.example.video.net

import com.example.video.MyApplication
import com.example.video.api.ApiService
import com.example.video.api.UrlConstant
import com.example.video.utils.AppUtils
import com.example.video.utils.NetworkUtil
import com.example.video.utils.Preference
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by bruse on 2019/7/1.
 */
object RetrofitManager {

    val service:ApiService by lazy (LazyThreadSafetyMode.SYNCHRONIZED ){
        getRetrofit().create(ApiService::class.java)
    }

    private var token:String by Preference("token","")

    /**
     * 设置公共参数
     */
    private fun addQueryParameterInterceptor():Interceptor{
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val request:Request
            val modifiedUrl = originalRequest.url().newBuilder()
                .addQueryParameter("udid","d2807c895f0348a180148c9dfa6f2feeac0781b5")
                .addQueryParameter("deviceModel", AppUtils.getMobileModel())
                .build()
            request = originalRequest.newBuilder().url(modifiedUrl).build()
            chain.proceed(request)
        }
    }

    /**
     * 设置头
     */
    private fun addHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                // Provide your custom header here
                .header("token", token)
                .method(originalRequest.method(), originalRequest.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }
    /**
     * 设置缓存
     */
    private fun addCacheInterceptor():Interceptor{
        return Interceptor { chain ->
            var request = chain.request()
            if (!NetworkUtil.isNetworkAvailable(MyApplication.context)){
                request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
            }
            val response = chain.proceed(request)
            if (NetworkUtil.isNetworkAvailable(MyApplication.context)){
                val maxAge = 0
                response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Retrofit")
                    .build()
            }else{
                //无网络时，设置缓存超时
                val maxStale = 60*60*24*28
                response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("nyn")
                    .build()
            }
            response
        }
    }

    private fun getRetrofit():Retrofit{
        //获取retrofit实例
        return Retrofit.Builder()
            .baseUrl(UrlConstant.BASE_URL)
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getOkHttpClient():OkHttpClient{
        //添加一个log拦截器，打印所有的log
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        //可以设置请求过滤的水平，body,basic,headers
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val cacheFile = File(MyApplication.context.cacheDir,"cache")
        val cache = Cache(cacheFile,1024 * 1024 * 50)//50Mb缓存大小

        return OkHttpClient.Builder()
            .addInterceptor(addQueryParameterInterceptor())
            .addInterceptor(addHeaderInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .cache(cache)
            .connectTimeout(60L,TimeUnit.SECONDS)
            .readTimeout(60L,TimeUnit.SECONDS)
            .writeTimeout(60L,TimeUnit.SECONDS)
            .build()
    }
}