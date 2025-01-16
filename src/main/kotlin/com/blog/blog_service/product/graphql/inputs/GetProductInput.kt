package com.blog.blog_service.product.graphql.inputs


import com.blog.blog_service.product.domain.utils.toDomain
import com.blog.blog_service.product.usecase.inputs.AddProductUsecaseInput
import com.blog.blog_service.product.usecase.inputs.ProductFilterUsecase
import org.blog.graphql.types.AddProductInput
import org.blog.graphql.types.ProductFilter
import java.util.UUID

fun ProductFilter.toGetProductInput(): ProductFilterUsecase {
    return ProductFilterUsecase(
        ids = ids?.map { UUID.fromString(it) },
        text = text,
        statuses = statuses.map { it.toDomain() }
    )
}
