package com.wangyousong.app.growthbackend.oss.config;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;

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

        // ==========================================
        // 5. 新增：反射配置 (解决 ClassNotFoundException)
        // ==========================================

        // 注册 ApacheHttpClient，允许调用其构造函数和方法
        hints.reflection().registerType(
                TypeReference.of("com.aliyuncs.http.clients.ApacheHttpClient"),
                MemberCategory.values() // 开启所有权限（构造器、字段、方法），简单粗暴且有效
        );

        // 预防性注册：SDK 可能会降级尝试使用 JDK 原生 Client，建议一起加上以防万一
        hints.reflection().registerType(
                TypeReference.of("com.aliyuncs.http.clients.CompatibleUrlConnClient"),
                MemberCategory.values()
        );

        // ==========================================
        // 6. 新增：修复 SpringDoc (Swagger) 启动报错
        // ==========================================

        // 报错的核心类：SpringWebProvider
        // 必须注册为反射，允许 CGLIB 读取其字段和构造函数
        hints.reflection().registerType(
                TypeReference.of("org.springdoc.core.providers.SpringWebProvider"),
                MemberCategory.values()
        );

        // 预防性补充：如果上面那个还不够，通常 SpringDoc 的这两个类也容易出问题
        // 建议一并加上，避免你再跑一次编译流程
        try {
            hints.reflection().registerType(
                    TypeReference.of("org.springdoc.webmvc.ui.SwaggerWelcome"),
                    MemberCategory.values()
            );
        } catch (Exception e) {
            // 忽略不存在的类（防止不同版本类名差异）
        }
    }
}