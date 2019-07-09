package com.example.video.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by bruse on 2019/7/1.
 */
class SingleMainScheduler <T> private constructor() : BaseScheduler<T>(Schedulers.single(), AndroidSchedulers.mainThread())