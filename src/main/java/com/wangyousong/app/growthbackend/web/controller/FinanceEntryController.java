package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.domain.FinanceEntry;
import com.wangyousong.app.growthbackend.service.FinanceEntryService;
import com.wangyousong.app.growthbackend.service.mapper.FinanceEntryMapper;
import com.wangyousong.app.growthbackend.web.controller.dto.FinanceEntryRequest;
import com.wangyousong.app.growthbackend.web.controller.dto.FinanceEntryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 财务记账条目控制器
 */
@RestController
@RequestMapping("/finance-entries")
public class FinanceEntryController {
    @Autowired
    private FinanceEntryService financeEntryService;

    @Autowired
    private FinanceEntryMapper financeEntryMapper;

    // 创建财务记账条目
    @PostMapping
    public ResponseEntity<FinanceEntryResponse> createFinanceEntry(@RequestBody FinanceEntryRequest financeEntryRequest) {
        FinanceEntry financeEntry = financeEntryMapper.toDomain(null, financeEntryRequest);
        FinanceEntry createdFinanceEntry = financeEntryService.createFinanceEntry(financeEntry);
        FinanceEntryResponse response = financeEntryMapper.toResponse(createdFinanceEntry);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 获取所有财务记账条目
    @GetMapping
    public ResponseEntity<List<FinanceEntryResponse>> getAllFinanceEntries() {
        List<FinanceEntry> financeEntries = financeEntryService.getAllFinanceEntries();
        List<FinanceEntryResponse> responses = financeEntries.stream()
                .map(financeEntryMapper::toResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    // 根据ID获取财务记账条目
    @GetMapping("/{id}")
    public ResponseEntity<FinanceEntryResponse> getFinanceEntryById(@PathVariable String id) {
        FinanceEntry financeEntry = financeEntryService.getFinanceEntryById(id);
        if (financeEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        FinanceEntryResponse response = financeEntryMapper.toResponse(financeEntry);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 更新财务记账条目
    @PutMapping("/{id}")
    public ResponseEntity<FinanceEntryResponse> updateFinanceEntry(@PathVariable String id, @RequestBody FinanceEntryRequest financeEntryRequest) {
        FinanceEntry updatedFinanceEntry = financeEntryService.updateFinanceEntry(id, financeEntryMapper.toDomain(id, financeEntryRequest));
        if (updatedFinanceEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        FinanceEntryResponse response = financeEntryMapper.toResponse(updatedFinanceEntry);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 删除财务记账条目
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinanceEntry(@PathVariable String id) {
        financeEntryService.deleteFinanceEntry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}