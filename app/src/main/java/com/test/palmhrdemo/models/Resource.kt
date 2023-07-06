package com.test.newsline.models

import androidx.annotation.Nullable
import com.test.newsline.utils.AppEnums.*


class Resource<T> private constructor(
    val status: Status, val data: T?,
    val message: String?, val errorType: ErrorType?
) {


    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null,null)
        }

        fun <T> error(msg: String?, data: T?,errorType: ErrorType): Resource<T> {
            return Resource(Status.ERROR, data, msg,errorType)
        }

        fun <T> loading(@Nullable data: T): Resource<T> {
            return Resource(Status.LOADING, data, null,null)
        }
    }
}