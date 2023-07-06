package com.test.palmhrdemo.networks

import com.google.gson.*

object GeneralParser {
    fun getGSONConfiguration(): Gson {
        return GsonBuilder()
            .setLenient()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .setExclusionStrategies(object : ExclusionStrategy {
                override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                    return false
                }

                override fun shouldSkipField(f: FieldAttributes?): Boolean {
                    return false
                }
            })
            .create()
    }
}