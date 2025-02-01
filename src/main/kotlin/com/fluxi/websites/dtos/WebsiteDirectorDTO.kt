package com.fluxi.websites.dtos

import com.fluxi.websites.models.Website
import com.fluxi.websites.requests.CreateWebsiteRequest

class WebsiteDirectorDTO {
    lateinit var request: CreateWebsiteRequest
    lateinit var copies: Map<String, Any?>

    lateinit var mainProductId: String
    var upsellProductId: String? = null
    var downsellProductId: String? = null

    lateinit var website: Website
    lateinit var imagesProduct: List<String>
    var upsellImage: String? = null
    var downSellImage: String? = null
}
