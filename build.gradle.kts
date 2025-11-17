plugins {
    id("java")
}

group = "UniversalDB-Java"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Logger
    implementation("org.slf4j:slf4j-simple:2.0.9")

    // MySQL
    implementation("mysql:mysql-connector-j:8.3.0")

    //SQLite
    implementation("org.xerial:sqlite-jdbc:3.45.1.0")

    // PostgreSQL JDBC Driver
    implementation("org.postgresql:postgresql:42.7.3")

    // Hibernate Core
    implementation("org.hibernate.orm:hibernate-core:7.1.7.Final")

}

tasks.test {
    useJUnitPlatform()
}