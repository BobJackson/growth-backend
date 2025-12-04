package com.wangyousong.app.growthbackend.config.mongo;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@Component
@ReadingConverter
public class IntegerToBooleanConverter implements Converter<Integer, Boolean> {

    @Override
    public Boolean convert(Integer source) {
        if (source == null) {
            return false; // 或者返回 false，取决于业务逻辑
        }
        // 将 1 视为 true，将 0 视为 false
        return source == 1;
    }
}