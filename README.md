# Ecommerce Backend with Spring Boot, Kotlin, and GraphQL

This repository contains the backend implementation of an ecommerce application built with **Spring Boot**, **Kotlin**, and **GraphQL**. It is part of a blog series detailing the step-by-step creation and deployment of a modern full-stack ecommerce application.

---

## Features

- **Spring Boot:** Robust and production-ready backend framework.
- **Kotlin:** Modern, concise, and type-safe programming language.
- **GraphQL:** Flexible and efficient API design for querying and mutating data.
- **Database Integration:** Support for NoSQL (e.g., MongoDB) databases.
- **AWS Deployment:** Ready for deployment on AWS Elastic Beanstalk.

---

## Project Structure

```plaintext
.
├── HELP.md
├── build.gradle.kts
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
├── src
│   └── main
│       ├── kotlin
│       │   └── com
│       │       └── blog
│       │           └── blog_service
│       │               ├── BlogServiceApplication.kt
│       │               └── product
│       │                   ├── domain
│       │                   │   ├── Product.kt
│       │                   │   ├── ProductConstants.kt
│       │                   │   └── utils
│       │                   │       └── extensions.kt
│       │                   ├── graphql
│       │                   │   └── controllers
│       │                   │       └── ProductsController.kt
│       │                   ├── storage
│       │                   │   ├── ProductRepository.kt
│       │                   │   ├── ProductStorage.kt
│       │                   │   └── ProductStorageImpl.kt
│       │                   └── usecase
│       │                       └── GetProducts.kt
│       └── resources
│           ├── application.properties
│           └── graphql
│               └── schema.graphqls

