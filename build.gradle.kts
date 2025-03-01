plugins {
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.6" apply false
}

buildscript {
    dependencies {
        classpath("org.jlleitschuh.gradle:ktlint-gradle:12.1.1")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.6")
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "io.gitlab.arturbosch.detekt")

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
        filter {
            exclude {
                it.file.path.contains("build/generated/")
            }
        }
    }

    configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
        config.setFrom("${rootProject.projectDir}/config/detekt.yml")
        autoCorrect = true
        buildUponDefaultConfig = true
    }

    afterEvaluate {
        dependencies {
            add("detektPlugins", "io.nlopez.compose.rules:detekt:0.4.4")
            add("detektPlugins", "io.gitlab.arturbosch.detekt:detekt-formatting:1.23.6")
        }

        tasks.named("check") {
            dependsOn("detekt")
        }
    }
}
