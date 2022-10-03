import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"

    id("org.asciidoctor.jvm.convert") version "3.3.2"

    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("org.assertj:assertj-core:3.23.1")

    // validation
    implementation ("org.springframework.boot:spring-boot-starter-validation")

    // Lonbok
    compileOnly("org.projectlombok:lombok")

    // h2
    runtimeOnly ("com.h2database:h2")

    // Spring REST Docs
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc:2.0.6.RELEASE")

    //MockK
    testImplementation("io.mockk:mockk:1.13.2")

    //Mockito Kotlin
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")

}

val snippetsDir = file("build/generated-snippets") // (3)

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    outputs.dir(snippetsDir)
}

tasks.asciidoctor {
    inputs.dir(snippetsDir)
    dependsOn(tasks.test)
}

tasks.register("copyHTML", Copy::class) {
    dependsOn(tasks.findByName("asciidoctor"))
    from(file("build/docs/asciidoc"))
    into(file("src/main/resources/static/docs"))
}

tasks.bootJar {
    dependsOn(tasks.asciidoctor)
    dependsOn(tasks.getByName("copyHTML"))
}

tasks.build {
    dependsOn(tasks.asciidoctor)
}
