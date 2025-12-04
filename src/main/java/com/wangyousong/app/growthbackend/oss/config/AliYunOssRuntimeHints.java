package com.wangyousong.app.growthbackend.oss.config;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class AliYunOssRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        // 1. 之前修复 OSS 的配置 (保持不变)
        hints.resources().registerResourceBundle("oss");
        hints.resources().registerResourceBundle("common"); // 有些版本可能需要这个
        hints.resources().registerPattern("oss.properties");
        hints.resources().registerPattern("versioninfo.properties");

        // 2. 新增：修复 AcsClient (Core SDK) 的配置
        // 解决 UserAgentConfig 报空指针的问题
        hints.resources().registerPattern("project.properties");
        hints.resources().registerPattern("**/project.properties"); // 关键：文件通常藏在深层包里

        // 3. 预防性修复：注册 Region 和 Endpoint 配置
        // AcsClient 启动后马上就会读这些文件，不加这个待会儿肯定还会崩
        hints.resources().registerPattern("endpoints.json");
        hints.resources().registerPattern("**/endpoints.json");
        hints.resources().registerPattern("regions.txt");
        hints.resources().registerPattern("**/regions.txt");

        // 4. 通用预防
        hints.resources().registerPattern("*.xml");
    }
}