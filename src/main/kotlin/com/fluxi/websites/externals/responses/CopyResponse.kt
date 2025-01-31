package com.fluxi.websites.externals.responses

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
class CopyResponse {
    var data: Data = Data()
}

@Serdeable
@Introspected
class Data {
    var copys: Copy = Copy()
}

@Serdeable
@Introspected
class Copy {
    var copys: Map<String, Any?> = mapOf()
}