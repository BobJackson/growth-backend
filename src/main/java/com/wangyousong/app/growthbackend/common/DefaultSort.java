package com.wangyousong.app.growthbackend.common;

import org.springframework.data.domain.Sort;

import static org.springframework.data.domain.Sort.Direction.DESC;

public final class DefaultSort {
    public static final Sort DEFAULT_SORT = Sort.by(DESC, "createdAt");
}
