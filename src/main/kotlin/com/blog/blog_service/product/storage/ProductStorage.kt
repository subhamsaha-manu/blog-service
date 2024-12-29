package com.blog.blog_service.product.storage

import com.blog.blog_service.product.domain.Product
import org.blog.graphql.types.ProductFilter

interface ProductStorage {
    suspend fun getProducts(productFilter: ProductFilter): List<Product>
}