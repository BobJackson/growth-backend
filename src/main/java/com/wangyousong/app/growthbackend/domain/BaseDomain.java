package com.wangyousong.app.growthbackend.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public abstract class BaseDomain {
    protected static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Id
    protected String id;
    protected LocalDateTime createdAt = LocalDateTime.now();
    protected LocalDateTime updatedAt;

    public String formattedCreateDate() {
        return createdAt.toLocalDate().format(DEFAULT_DATE_FORMAT);
    }
}
