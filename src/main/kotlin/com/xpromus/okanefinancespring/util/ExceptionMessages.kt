package com.xpromus.okanefinancespring.util

fun getEntityNotFoundExceptionMessage(entityType: String, id: String): String {
    return "${entityType} with the id ${id} could not be found!"
}