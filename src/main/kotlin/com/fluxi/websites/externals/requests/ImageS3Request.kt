package com.fluxi.websites.externals.requests

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
class ImageS3Request {
    var file: String = ""
    var folder: String = ""
    var filename: String = ""
}