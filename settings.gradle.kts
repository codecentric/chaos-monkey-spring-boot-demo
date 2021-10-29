enableFeaturePreview("VERSION_CATALOGS")

rootDir.listFiles()?.forEach {
    if(it.isDirectory && it.list()?.contains("build.gradle.kts") == true) {
        include(it.name)
    }
}
