package com.fluxi.websites.dtos

import com.fluxi.websites.models.Website
import com.fluxi.websites.requests.CreateWebsiteRequest

class WebsiteDirectorDTO {
    lateinit var request: CreateWebsiteRequest
    lateinit var copies: Map<String, Any?>

    lateinit var mainProductId: String
    lateinit var upsellProductId: String
    lateinit var downsellProductId: String

    lateinit var website: Website
    lateinit var imagesProduct: List<String>
    lateinit var upsellImage: String
    lateinit var downSellImage: String
}
