import com.kobylynskyi.graphql.codegen.model.GeneratedLanguage
import io.github.kobylynskyi.graphql.codegen.gradle.GraphQLCodegenGradleTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
	id("org.asciidoctor.jvm.convert") version "3.3.2"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	id("io.github.kobylynskyi.graphql.codegen") version "5.8.0"
}

group = "com.blog"
version = "1.0.0"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<KotlinCompile> {
	dependsOn("graphqlCodegen")
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.named<GraphQLCodegenGradleTask>("graphqlCodegen") {
	graphqlSchemaPaths = listOf("$projectDir/src/main/resources/graphql/schema.graphqls")
	outputDir = File("$buildDir/generated")
	packageName = "org.blog.graphql.types"
	customTypesMapping = mutableMapOf(Pair("UUID", "java.util.UUID"), Pair("ID", "java.util.UUID"))
	generatedLanguage = GeneratedLanguage.KOTLIN
	generateApis = false
	generateJacksonTypeIdResolver = true
}

// Ensure generated sources are included
sourceSets {
	getByName("main").java.srcDirs("$buildDir/generated")
}

// Disable plain.jar and use bootJar for Spring Boot executable JAR
tasks {
	jar {
		enabled = false
	}

	bootJar {
		archiveClassifier.set("")
	}

	named<JavaCompile>("compileJava") {
		dependsOn("graphqlCodegen")
	}
}
