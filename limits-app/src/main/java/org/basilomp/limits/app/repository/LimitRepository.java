package org.basilomp.limits.app.repository;

import org.basilomp.limits.app.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Integer> {

    Optional<Limit> findFirstByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query("UPDATE Limit l SET l.dailyLimit = :updatedDailyLimit where l.userId = :userId")
    void updateDailyLimit(@Param("userId") Integer userId,
                          @Param("updatedDailyLimit") BigDecimal updatedDailyLimit);

    @Modifying
    @Transactional
    @Query("UPDATE Limit l SET l.dailyLimit = l.approvedDailyLimit")
    void resetDailyLimits();

    @Modifying
    @Transactional
    @Query("UPDATE Limit l SET l.approvedDailyLimit = :approvedDailyLimit where l.userId = :userId")
    void updateApprovedDailyLimit(@Param("userId") Integer userId,
                                  @Param("approvedDailyLimit") BigDecimal approvedDailyLimit);
}
