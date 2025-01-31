package com.fluxi.websites.externals.responses

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
class ImageS3Response {
    @field:JsonProperty("s3_url")
    var s3Url: String = ""
}