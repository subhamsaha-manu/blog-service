package com.blog.blog_service.product.usecase.inputs

import com.blog.blog_service.product.domain.ProductStatus
import java.util.UUID

data class AddProductUsecaseInput(
    val productId: UUID,
    val title: String,
    val description: String,
    val price: Double,
    val medias: List<String>,
    val status: ProductStatus
)
