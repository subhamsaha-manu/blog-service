package com.blog.blog_service.product.domain

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.UUID

@Document(collection = ProductConstants.PRODUCTS_COLLECTION)
data class Product(
    @Id var id: UUID = UUID.randomUUID(),
    val productId: UUID = id,
    val title: String,
    val description: String,
    val price: Double,
    val categoryIds: List<UUID> = emptyList(),
    val medias: List<String> = emptyList(),
    val thumbnailUrl: String,
    val inventoryId: UUID? = null,
    val status: ProductStatus,
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    var createdOn: LocalDateTime? = null,
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    var updatedOn: LocalDateTime? = null,
) {
    companion object {
        const val FIELD_ID = "_id"
        const val FIELD_STATUS = "status"
        const val FIELD_TITLE = "title"
    }
}

enum class ProductStatus {
    DRAFT, PUBLISHED, INACTIVE;
}