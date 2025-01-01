package com.blog.blog_service.product.usecase

import com.blog.blog_service.product.domain.utils.fetchImages
import com.blog.blog_service.product.domain.utils.toGraphqlProductStatus
import com.blog.blog_service.product.storage.ProductStorage
import org.blog.graphql.types.PageInfo
import org.blog.graphql.types.Product as GraphqlProduct
import com.blog.blog_service.product.domain.Product as DomainProduct
import org.blog.graphql.types.ProductFilter
import org.blog.graphql.types.ProductsResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

class GetProducts(
    private val productStorage: ProductStorage
) {

    suspend operator fun invoke(productFilter: ProductFilter, pageNumber: Int, pageSize: Int): ProductsResponse {
        val images = fetchImages()
        val products = productStorage.getProducts(productFilter).map { it.toGraphqlProduct(images) }

        val page = extractPage(products, pageNumber, pageSize)

        val pageInfo = PageInfo(
            totalPages = page.totalPages,
            currentPage = page.number,
            pageSize = page.size,
            totalElements = page.totalElements.toInt()
        )

        return ProductsResponse(page.content, pageInfo)
    }

    private fun extractPage(list: List<GraphqlProduct>, pageNumber: Int, pageSize: Int): Page<GraphqlProduct> {

        val start = pageNumber * pageSize
        val end = minOf(list.size, start + pageSize)

        return PageImpl(
            list.subList(start, end),
            PageRequest.of(pageNumber, pageSize),
            list.size.toLong()
        )
    }
}

private fun DomainProduct.toGraphqlProduct(images: List<String>): GraphqlProduct {
    return GraphqlProduct(
        id = id.toString(),
        productId = productId.toString(),
        title = title,
        price = price,
        medias = listOf(images.random()),
        description = description,
        status = status.toGraphqlProductStatus()
    )
}
