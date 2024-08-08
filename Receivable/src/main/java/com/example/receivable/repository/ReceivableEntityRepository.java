package com.example.receivable.repository;

import com.example.receivable.model.ReceivableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceivableEntityRepository extends JpaRepository<ReceivableEntity, Long> {

    List<ReceivableEntity> findReceivableEntityByCompanyId(Long id);
}