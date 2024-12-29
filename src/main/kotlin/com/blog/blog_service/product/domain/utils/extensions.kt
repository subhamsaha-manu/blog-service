package com.blog.blog_service.product.domain.utils

import com.blog.blog_service.product.domain.ProductStatus as DomainProductStatus
import org.blog.graphql.types.ProductStatus as GraphqlProductStatus

fun DomainProductStatus.toGraphqlProductStatus(): GraphqlProductStatus {
    return when (this) {
        DomainProductStatus.DRAFT -> GraphqlProductStatus.DRAFT
        DomainProductStatus.PUBLISHED -> GraphqlProductStatus.PUBLISHED
        DomainProductStatus.INACTIVE -> GraphqlProductStatus.INACTIVE
    }
}

fun GraphqlProductStatus.toDomain(): DomainProductStatus {
    return DomainProductStatus.valueOf(this.name)
}