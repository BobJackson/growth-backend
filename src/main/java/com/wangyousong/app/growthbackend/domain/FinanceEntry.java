package com.wangyousong.app.growthbackend.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 财务记账条目
 */
@Data
@Document(collection = "finance_entries")
public class FinanceEntry {
    @Id
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