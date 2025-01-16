package com.blog.blog_service.product.storage

import com.blog.blog_service.product.domain.Product
import com.blog.blog_service.product.usecase.inputs.ProductFilterUsecase

interface ProductStorage {
    suspend fun getProducts(productFilter: ProductFilterUsecase): List<Product>
    suspend fun addProduct(product: Product): Product
}