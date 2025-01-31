package com.fluxi.core.extensions

import java.util.UUID

fun generateId(): String {
    val uuidShort = UUID.randomUUID().toString().replace("-", "").substring(0, 20)
    val timestampHex = System.currentTimeMillis().toString(16)

    return "$uuidShort$timestampHex"
}