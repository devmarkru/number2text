plugins {
    kotlin("jvm") version "1.9.23"
    application
    `maven-publish`
}

group = "ru.devmark"
version = "2.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}

publishing {
    publications {
        create<MavenPublication>("number2text") {
            from(components["kotlin"])
        }
    }

    repositories {
        mavenLocal()
    }
}
