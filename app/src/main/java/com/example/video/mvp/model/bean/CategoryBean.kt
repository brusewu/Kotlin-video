package com.example.video.mvp.model.bean

import java.io.Serializable

/**
 * Created by bruse on 2019/7/2.
 */
data class CategoryBean (val id: Long, val name: String, val description: String, val bgPicture: String, val bgColor: String, val headerImage: String) : Serializable
