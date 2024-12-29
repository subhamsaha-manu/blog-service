package com.blog.blog_service.product.storage

import com.blog.blog_service.product.domain.Product
import com.blog.blog_service.product.domain.ProductConstants
import org.blog.graphql.types.ProductFilter
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
class ProductStorageImpl(
    private val mongoTemplate: MongoTemplate,
) : ProductStorage {

    override suspend fun getProducts(productFilter: ProductFilter): List<Product> {
        val query = Query()

        with(productFilter) {
            ids?.let {
                query.addCriteria(Criteria.where(Product.FIELD_ID).`in`(ids))
            }

            statuses.let {
                query.addCriteria(Criteria.where(Product.FIELD_STATUS).`in`(statuses))
            }

            text?.let {
                query.addCriteria(
                    Criteria().orOperator(
                        Criteria.where(Product.FIELD_TITLE).regex(it, "i"),
                    )
                )
            }
        }

        return mongoTemplate.find(query, Product::class.java, ProductConstants.PRODUCTS_COLLECTION)
    }
}