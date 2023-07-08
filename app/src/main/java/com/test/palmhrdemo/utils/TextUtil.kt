package com.test.palmhrdemo.utils

import com.test.palmhrdemo.models.FilterObject

object TextUtil {

    fun makeQuery(filterObject: FilterObject): String {

        val string = StringBuilder()
        if (filterObject.query.isNotEmpty()) {
            string.append(filterObject.query)
        }
        if (filterObject.author.isNotEmpty()) {

            string.append("+${Constants.IN_AUTHOR}:" + filterObject.author)
        }
        if (filterObject.isbn.isNotEmpty()) {

            string.append("+${Constants.ISBN}:" + filterObject.isbn)
        }
        if (filterObject.publisher.isNotEmpty()) {

            string.append("+${Constants.IN_PUBLISHER}:" + filterObject.publisher)
        }
        return string.toString()
    }
}