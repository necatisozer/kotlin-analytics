import com.android.build.gradle.LibraryExtension

/**
 * Precompiled script plugin from:
 * https://github.com/necatisozer/kotlin-analytics/blob/master/buildSrc/src/main/kotlin/publish.gradle.kts
 *
 * The following plugin tasks care of setting up:
 * - Publishing to Maven Central and Sonatype Snapshots
 * - GPG Signing with in memory PGP Keys
 * - Dokka for documentation
 * - sourceJar for attaching sources to publications
 *
 * To use it just apply:
 *
 * plugins {
 *     publish
 * }
 *
 * To your build.gradle.kts.
 *
 * If you copy over this file in your project, make sure to copy it inside: buildSrc/src/main/kotlin/publish.gradle.kts.
 * Make sure to copy over also buildSrc/build.gradle.kts otherwise this plugin will fail to compile due to missing dependencies.
 */
plugins {
    id("maven-publish")
    id("signing")
    id("org.jetbrains.dokka")
}

val dokkaJar = tasks.create<Jar>("dokkaJar") {
    group = "build"
    description = "Assembles Javadoc jar from Dokka API docs"
    archiveClassifier.set("javadoc")
    from(tasks.dokkaJavadoc)
}

val sourcesJar = tasks.register<Jar>("sourcesJar") {
    group = "build"
    description = "Assembles Source jar for publishing"
    archiveClassifier.set("sources")
    if (plugins.hasPlugin("com.android.library")) {
        from(
            (project.extensions.getByName("android") as LibraryExtension).sourceSets.named("main")
                .get().java.srcDirs
        )
    } else {
        from(
            (project.extensions.getByName("sourceSets") as SourceSetContainer).named("main")
                .get().allSource
        )
    }
}

tasks.dokkaJavadoc.configure {
    outputDirectory.set(buildDir.resolve("javadoc"))
    dokkaSourceSets {
        configureEach {
            sourceRoot(file("src"))
        }
    }
}


afterEvaluate {

    publishing {
        repositories {
            maven {
                name = "nexus"
                url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = "NEXUS_USERNAME".byProperty
                    password = "NEXUS_PASSWORD".byProperty
                }
            }
            maven {
                name = "snapshot"
                url = uri("https://oss.sonatype.org/content/repositories/snapshots")
                credentials {
                    username = "NEXUS_USERNAME".byProperty
                    password = "NEXUS_PASSWORD".byProperty
                }
            }
        }

        publications {
            create<MavenPublication>("release") {
                if (plugins.hasPlugin("com.android.library")) {
                    from(components["release"])
                } else {
                    from(components["java"])
                }
                artifact(dokkaJar)
                artifact(sourcesJar)

                pom {
                    if (!"USE_SNAPSHOT".byProperty.isNullOrBlank()) {
                        version = "$version-SNAPSHOT"
                    }
                    description.set("A template for Kotlin Android projects")
                    url.set("https://github.com/necatisozer/kotlin-analytics/")

                    licenses {
                        license {
                            name.set("The MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    developers {
                        developer {
                            id.set("necatisozer")
                            name.set("Necati Sozer")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com/necatisozer/kotlin-analytics.git")
                        developerConnection.set("scm:git:ssh://github.com/necatisozer/kotlin-analytics.git")
                        url.set("https://github.com/necatisozer/kotlin-analytics/")
                    }
                    issueManagement {
                        system.set("GitHub Issues")
                        url.set("https://github.com/necatisozer/kotlin-analytics/issues")
                    }
                }
            }
        }

        val signingKey = "SIGNING_KEY".byProperty
        val signingPwd = "SIGNING_PWD".byProperty
        if (signingKey.isNullOrBlank() || signingPwd.isNullOrBlank()) {
            logger.info("Signing Disable as the PGP key was not found")
        } else {
            logger.info("GPG Key found - Signing enabled")
            signing {
                useInMemoryPgpKeys(signingKey, signingPwd)
                sign(publishing.publications["release"])
            }
        }
    }
}

val String.byProperty: String? get() = findProperty(this) as? String
