plugins {
    kotlin("jvm") version "2.2.21"
    id("com.android.application") version "9.0.0" apply false
    kotlin("android") version "2.2.21" apply false
}


subprojects {
    dependencies {
        testImplementation(kotlin("test"))
    }

    kotlin {
        jvmToolchain(17)
    }

    tasks.test {
        useJUnitPlatform()
    }
}
