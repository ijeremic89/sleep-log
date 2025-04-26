package com.noom.interview.fullstack.sleep.log;

import com.noom.interview.fullstack.sleep.log.model.SleepLogEntity;
import com.noom.interview.fullstack.sleep.log.model.SleepLogSummaryProjection;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SleepLogRepository extends CrudRepository<SleepLogEntity, Long> {

  @Query("SELECT s FROM SleepLogEntity s WHERE s.user.id = :userId AND s.sleepDate = :fromDate")
  Optional<SleepLogEntity> findByUserIdAndFromDate(
      @Param("userId") Long userId, @Param("fromDate") LocalDate fromDate);

  @Query(
      value =
          """
              SELECT
                  MIN(s.sleep_date) AS fromDate,
                  MAX(s.sleep_date) AS toDate,
                  EXTRACT(EPOCH FROM AVG(s.total_time_in_bed)) AS avgTotalTimeInBedSeconds,
                  AVG(
                      CASE
                        WHEN EXTRACT(HOUR FROM s.time_in_bed_from) < 6 THEN
                          EXTRACT(EPOCH FROM s.time_in_bed_from) + 86400
                        ELSE
                          EXTRACT(EPOCH FROM s.time_in_bed_from)
                      END
                    ) AS avgTimeInBedFromSeconds,
                  AVG(EXTRACT(EPOCH FROM s.time_in_bed_to)) AS avgTimeInBedToSeconds,
                  SUM(CASE WHEN s.mood = 'GOOD' THEN 1 ELSE 0 END) AS goodMoodCount,
                  SUM(CASE WHEN s.mood = 'OK' THEN 1 ELSE 0 END) AS okMoodCount,
                  SUM(CASE WHEN s.mood = 'BAD' THEN 1 ELSE 0 END) AS badMoodCount
              FROM sleep_log s
              WHERE s.user_id = :userId
                AND s.sleep_date BETWEEN :fromDate AND :toDate
              """,
      nativeQuery = true)
  SleepLogSummaryProjection getSleepSummary(
      @Param("userId") Long userId,
      @Param("fromDate") LocalDate fromDate,
      @Param("toDate") LocalDate toDate);
}
