package com.blog.blog_service.product.graphql.inputs


import com.blog.blog_service.product.domain.utils.toDomain
import com.blog.blog_service.product.usecase.inputs.AddProductUsecaseInput
import org.blog.graphql.types.AddProductInput
import java.util.UUID

fun AddProductInput.toAddProductInput(): AddProductUsecaseInput {
    return AddProductUsecaseInput(
        productId = UUID.fromString(productId),
        title = title,
        description = description,
        price = price,
        medias = medias,
        status = status.toDomain()
    )
}
