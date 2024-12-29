package com.blog.blog_service.product.graphql.controllers

import com.blog.blog_service.product.storage.ProductStorage
import com.blog.blog_service.product.usecase.AddProduct
import com.blog.blog_service.product.usecase.GetProducts
import org.blog.graphql.types.AddProductInput
import org.blog.graphql.types.ProductFilter
import org.blog.graphql.types.ProductsResponse
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller


@Controller
class ProductsController(
    private val productStorage: ProductStorage
) {

    val productsQuery = GetProducts(productStorage)
    val addProductMutation = AddProduct(productStorage)

    @QueryMapping
    suspend fun products(
        @Argument productFilter: ProductFilter,
        @Argument pageNumber: Int,
        @Argument pageSize: Int
    ): ProductsResponse {
        return productsQuery(productFilter, pageNumber, pageSize)
    }

    @MutationMapping
    suspend fun addProduct(@Argument addProductInput: AddProductInput): Boolean {
        return addProductMutation(addProductInput)
    }
}