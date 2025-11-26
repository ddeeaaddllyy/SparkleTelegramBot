plugins {
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
	id("org.springframework.boot") version "3.5.7"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.google.devtools.ksp") version "1.9.22-1.0.17"
}

val telegramBotVersion = "6.3.0"
val moshiVersion = "1.15.1"
group = "com.spark11e"
version = "0.0.4-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
	google()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("com.squareup.okhttp3:okhttp:4.12.0")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")


	implementation("com.squareup.moshi:moshi:${moshiVersion}")
	implementation("com.squareup.moshi:moshi-kotlin:${moshiVersion}")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:${moshiVersion}")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.hibernate:hibernate-community-dialects:6.5.0.Final")
	implementation("org.xerial:sqlite-jdbc:3.45.1.0")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	implementation("org.telegram:telegrambots:${telegramBotVersion}")
	implementation("org.telegram:telegrambotsextensions:${telegramBotVersion}")
	implementation("org.telegram:telegrambots-spring-boot-starter:${telegramBotVersion}")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
