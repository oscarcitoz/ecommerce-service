package  com.fluxi.websites.services

import com.fluxi.websites.models.Website
import jakarta.inject.Singleton
import com.fluxi.websites.repositories.WebsiteRepository

@Singleton
class WebsiteService(
    private val websiteRepository: WebsiteRepository
): WebsiteServiceInterface {
    override fun findByOwnerId(ownerId: String): List<Website> = websiteRepository.findByOwnerId(ownerId)

    override fun findByProductId(productId: String): List<Website>  = websiteRepository.findByProductId(productId)

    override fun create(website: Website): Website {
        TODO("Not yet implemented")
    }

    override fun update(id: Long, website: Website): Website {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {

    }
}
