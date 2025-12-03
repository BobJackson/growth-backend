graalvmNative {
    binaries.all {
        buildArgs.add("--resource-config-path=src/main/resources/META-INF/native-image/resource-config.json")
    }
}