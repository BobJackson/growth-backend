package com.wangyousong.app.growthbackend.web.controller.dto;

import com.wangyousong.app.growthbackend.domain.TransactionType;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 财务记账条目请求 DTO
 */
@Data
public class FinanceEntryRequest {
    // 日期
    private LocalDateTime date;

    // 事件名称
    private String eventName;

    // 金额
    private BigDecimal amount;

    // 交易类型（收入/支出）
    private TransactionType transactionType;
}