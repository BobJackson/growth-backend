package com.wangyousong.app.growthbackend.oss.config;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class AliYunOssRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        // Register the resource bundle causing the crash
        hints.resources().registerResourceBundle("oss");

        // Also register this property file mentioned in the warning logs
        hints.resources().registerPattern("versioninfo.properties");
    }
}