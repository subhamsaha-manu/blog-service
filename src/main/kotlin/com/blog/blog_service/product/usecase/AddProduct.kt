package com.blog.blog_service.product.usecase

import com.blog.blog_service.product.domain.Product
import com.blog.blog_service.product.domain.utils.toDomain
import com.blog.blog_service.product.storage.ProductStorage
import org.blog.graphql.types.AddProductInput
import java.util.UUID

class AddProduct(private val productStorage: ProductStorage) {

    suspend operator fun invoke(input: AddProductInput): Boolean {
        return try {
            val (productId, title, description, price, medias, status) = input

            val newProduct = Product(
                id = UUID.fromString(productId),
                productId = UUID.fromString(productId),
                title,
                description,
                price,
                medias,
                status = status.toDomain(),
            )

            productStorage.addProduct(newProduct)
            true
        } catch (e: Exception) {
            false
        }
    }

}
