package com.wangyousong.app.growthbackend.web.controller.dto;

import com.wangyousong.app.growthbackend.domain.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 财务记账条目响应 DTO
 */
@Data
public class FinanceEntryResponse {
    // ID
    private String id;

    // 日期
    private LocalDate date;

    // 事件名称
    private String eventName;

    // 金额
    private BigDecimal amount;

    // 交易类型（收入/支出）
    private TransactionType transactionType;

    // 创建时间
    private LocalDateTime createdAt;

    // 更新时间
    private LocalDateTime updatedAt;
}