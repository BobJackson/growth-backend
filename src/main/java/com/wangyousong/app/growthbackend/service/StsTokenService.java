package com.wangyousong.app.growthbackend.service;

import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;

public interface StsTokenService {
    AssumeRoleResponse.Credentials getStsToken();
}
