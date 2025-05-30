package com.wangyousong.app.growthbackend.service.impl;

import com.wangyousong.app.growthbackend.domain.FinanceEntry;
import com.wangyousong.app.growthbackend.repository.mongo.FinanceEntryRepository;
import com.wangyousong.app.growthbackend.service.FinanceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 财务记账条目业务逻辑实现类
 */
@Service
public class FinanceEntryServiceImpl implements FinanceEntryService {
    @Autowired
    private FinanceEntryRepository financeEntryRepository;

    // 创建财务记账条目
    @Override
    public FinanceEntry createFinanceEntry(FinanceEntry financeEntry) {
        financeEntry.setCreatedAt(LocalDateTime.now());
        financeEntry.setUpdatedAt(LocalDateTime.now());
        return financeEntryRepository.save(financeEntry);
    }

    // 获取所有财务记账条目
    @Override
    public List<FinanceEntry> getAllFinanceEntries() {
        return financeEntryRepository.findAll();
    }

    // 根据ID获取财务记账条目
    @Override
    public FinanceEntry getFinanceEntryById(String id) {
        Optional<FinanceEntry> financeEntry = financeEntryRepository.findById(id);
        return financeEntry.orElse(null);
    }

    // 更新财务记账条目
    @Override
    public FinanceEntry updateFinanceEntry(String id, FinanceEntry financeEntry) {
        FinanceEntry existingFinanceEntry = getFinanceEntryById(id);
        if (existingFinanceEntry == null) {
            return null;
        }
        existingFinanceEntry.setDate(financeEntry.getDate());
        existingFinanceEntry.setEventName(financeEntry.getEventName());
        existingFinanceEntry.setAmount(financeEntry.getAmount());
        existingFinanceEntry.setTransactionType(financeEntry.getTransactionType());
        existingFinanceEntry.setUpdatedAt(LocalDateTime.now());
        return financeEntryRepository.save(existingFinanceEntry);
    }

    // 删除财务记账条目
    @Override
    public void deleteFinanceEntry(String id) {
        financeEntryRepository.deleteById(id);
    }
}