package com.wangyousong.app.growthbackend.service;

import com.wangyousong.app.growthbackend.domain.FinanceEntry;
import java.util.List;

/**
 * 财务记账条目业务逻辑接口
 */
public interface FinanceEntryService {
    // 创建财务记账条目
    FinanceEntry createFinanceEntry(FinanceEntry financeEntry);

    // 获取所有财务记账条目
    List<FinanceEntry> getAllFinanceEntries();

    // 根据ID获取财务记账条目
    FinanceEntry getFinanceEntryById(String id);

    // 更新财务记账条目
    FinanceEntry updateFinanceEntry(String id, FinanceEntry financeEntry);

    // 删除财务记账条目
    void deleteFinanceEntry(String id);
}