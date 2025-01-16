package com.blog.blog_service.product.usecase

import com.blog.blog_service.product.domain.Product
import com.blog.blog_service.product.storage.ProductStorage
import com.blog.blog_service.product.usecase.inputs.AddProductUsecaseInput

class AddProduct(private val productStorage: ProductStorage) {

    suspend operator fun invoke(input: AddProductUsecaseInput): Boolean {
        return try {
            val (productId, title, description, price, medias, status) = input

            val newProduct = Product(
                id = productId,
                productId = productId,
                title,
                description,
                price,
                medias,
                status = status,
            )

            productStorage.addProduct(newProduct)
            true
        } catch (e: Exception) {
            false
        }
    }

}
