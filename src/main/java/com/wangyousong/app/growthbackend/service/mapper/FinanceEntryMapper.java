package com.wangyousong.app.growthbackend.service.mapper;

import com.wangyousong.app.growthbackend.domain.FinanceEntry;
import com.wangyousong.app.growthbackend.web.controller.dto.FinanceEntryRequest;
import com.wangyousong.app.growthbackend.web.controller.dto.FinanceEntryResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 财务记账条目 DTO 与 Domain 映射工具类
 */
@Component
public class FinanceEntryMapper {
    // 将 FinanceEntry 转换为 FinanceEntryResponse
    public FinanceEntryResponse toResponse(FinanceEntry financeEntry) {
        FinanceEntryResponse response = new FinanceEntryResponse();
        response.setId(financeEntry.getId());
        response.setDate(financeEntry.getDate());
        response.setEventName(financeEntry.getEventName());
        response.setAmount(financeEntry.getAmount());
        response.setTransactionType(financeEntry.getTransactionType());
        response.setCreatedAt(financeEntry.getCreatedAt());
        response.setUpdatedAt(financeEntry.getUpdatedAt());
        return response;
    }

    // 将 FinanceEntryRequest 转换为 FinanceEntry
    public FinanceEntry toDomain(String id, FinanceEntryRequest request) {
        FinanceEntry financeEntry = new FinanceEntry();
        financeEntry.setId(id);
        financeEntry.setDate(request.getDate());
        financeEntry.setEventName(request.getEventName());
        financeEntry.setAmount(request.getAmount());
        financeEntry.setTransactionType(request.getTransactionType());
        financeEntry.setUpdatedAt(LocalDateTime.now());
        return financeEntry;
    }
}