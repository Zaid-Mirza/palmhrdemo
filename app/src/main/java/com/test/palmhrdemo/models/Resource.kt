package com.test.palmhrdemo.models

import androidx.annotation.Nullable
import com.test.palmhrdemo.utils.AppEnums


class Resource<T> private constructor(
    val status: AppEnums.Status, val data: T?,
    val message: String?, val errorType: AppEnums.ErrorType?
) {


    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(AppEnums.Status.SUCCESS, data, null,null)
        }

        fun <T> error(msg: String?, data: T?,errorType: AppEnums.ErrorType): Resource<T> {
            return Resource(AppEnums.Status.ERROR, data, msg,errorType)
        }

        fun <T> loading(@Nullable data: T): Resource<T> {
            return Resource(AppEnums.Status.LOADING, data, null,null)
        }
    }
}