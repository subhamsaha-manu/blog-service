package com.blog.blog_service.product.storage

import com.blog.blog_service.product.domain.Product
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface ProductRepository: MongoRepository<Product, UUID>