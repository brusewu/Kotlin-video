package com.example.video.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import com.example.video.MyApplication
import com.example.video.R
import com.example.video.base.BaseActivity
import com.example.video.utils.AppUtils
import com.example.video.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_about.*

/**
 * Created by bruse on 2019/7/2.
 */
class AboutActivity:BaseActivity() {

    override fun layoutId(): Int = R.layout.activity_about

    override fun initData() {
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, toolbar)

        tv_version_name.text ="v${AppUtils.getVerName(MyApplication.context)}"
        //返回
        toolbar.setNavigationOnClickListener { finish() }
        //访问 GitHub
        relayout_gitHub.setOnClickListener {
            val uri = Uri.parse("https://github.com/brusewu")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    override fun start() {

    }
}