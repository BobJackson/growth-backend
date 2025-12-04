package com.wangyousong.app.growthbackend.oss.config;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class AliYunOssRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        // Register the resource bundle causing the crash
        hints.resources().registerResourceBundle("oss");
        hints.resources().registerResourceBundle("common");


        // 2. 注册具体文件 Pattern (解决 WARN: versioninfo.properties not found)
        // 有些 SDK 会尝试通过 ClassLoader.getResource() 直接拿文件，而不是通过 Bundle
        hints.resources().registerPattern("oss.properties");
        hints.resources().registerPattern("common.properties");
        hints.resources().registerPattern("versioninfo.properties");

        // 3. 阿里云 SDK 内部经常用到 XML 解析 (JDOM/SAX)，预防性注册
        hints.resources().registerPattern("*.xml");
    }
}