package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.domain.FinanceEntry;
import com.wangyousong.app.growthbackend.service.FinanceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 财务记账条目控制器
 */
@RestController
@RequestMapping("/finance-entries")
public class FinanceEntryController {
    @Autowired
    private FinanceEntryService financeEntryService;

    // 创建财务记账条目
    @PostMapping
    public ResponseEntity<FinanceEntry> createFinanceEntry(@RequestBody FinanceEntry financeEntry) {
        FinanceEntry createdFinanceEntry = financeEntryService.createFinanceEntry(financeEntry);
        return new ResponseEntity<>(createdFinanceEntry, HttpStatus.CREATED);
    }

    // 获取所有财务记账条目
    @GetMapping
    public ResponseEntity<List<FinanceEntry>> getAllFinanceEntries() {
        List<FinanceEntry> financeEntries = financeEntryService.getAllFinanceEntries();
        return new ResponseEntity<>(financeEntries, HttpStatus.OK);
    }

    // 根据ID获取财务记账条目
    @GetMapping("/{id}")
    public ResponseEntity<FinanceEntry> getFinanceEntryById(@PathVariable String id) {
        FinanceEntry financeEntry = financeEntryService.getFinanceEntryById(id);
        if (financeEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(financeEntry, HttpStatus.OK);
    }

    // 更新财务记账条目
    @PutMapping("/{id}")
    public ResponseEntity<FinanceEntry> updateFinanceEntry(@PathVariable String id, @RequestBody FinanceEntry financeEntry) {
        FinanceEntry updatedFinanceEntry = financeEntryService.updateFinanceEntry(id, financeEntry);
        if (updatedFinanceEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedFinanceEntry, HttpStatus.OK);
    }

    // 删除财务记账条目
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinanceEntry(@PathVariable String id) {
        financeEntryService.deleteFinanceEntry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}