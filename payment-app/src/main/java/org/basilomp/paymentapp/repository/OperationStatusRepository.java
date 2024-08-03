package org.basilomp.paymentapp.repository;

import org.basilomp.paymentapp.entity.OperationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationStatusRepository extends JpaRepository<OperationStatus, Integer> {

    Optional<OperationStatus> findOperationStatusByCode(Integer code);
}
