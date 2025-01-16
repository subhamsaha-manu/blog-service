package com.blog.blog_service.product.usecase.inputs

import com.blog.blog_service.product.domain.ProductStatus
import java.util.UUID

data class ProductFilterUsecase(
    val ids: List<UUID>?,
    val text: String?,
    val statuses: List<ProductStatus>
)
