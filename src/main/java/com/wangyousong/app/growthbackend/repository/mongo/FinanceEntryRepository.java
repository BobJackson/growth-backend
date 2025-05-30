package com.wangyousong.app.growthbackend.repository.mongo;

import com.wangyousong.app.growthbackend.domain.FinanceEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 财务记账条目的数据库访问接口
 */
public interface FinanceEntryRepository extends MongoRepository<FinanceEntry, String> {
}