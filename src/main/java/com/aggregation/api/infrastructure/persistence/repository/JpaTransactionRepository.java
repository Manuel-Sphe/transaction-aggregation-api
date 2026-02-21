package com.aggregation.api.infrastructure.persistence.repository;

import com.aggregation.api.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaTransactionRepository extends CrudRepository<TransactionEntity, UUID> {
}
