package com.noom.interview.fullstack.sleep.log;

import com.noom.interview.fullstack.sleep.log.model.SleepLogEntity;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SleepLogRepository extends CrudRepository<SleepLogEntity, Long> {

  @Query("SELECT s FROM SleepLogEntity s WHERE s.user.id = :userId AND s.sleepDate = :fromDate")
  Optional<SleepLogEntity> findByUserIdAndFromDate(
      @Param("userId") Long userId, @Param("fromDate") LocalDate fromDate);
}
