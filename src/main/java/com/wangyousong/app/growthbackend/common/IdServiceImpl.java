package com.wangyousong.app.growthbackend.common;

import cn.hutool.core.util.IdUtil;

public class IdServiceImpl implements IdService {

    @Override
    public String generateId() {
        return IdUtil.fastSimpleUUID();
    }

}
