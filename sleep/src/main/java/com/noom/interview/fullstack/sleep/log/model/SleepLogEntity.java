package com.noom.interview.fullstack.sleep.log.model;

import com.noom.interview.fullstack.sleep.user.UserEntity;
import jakarta.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@Entity
@Table(name = "sleep_log")
public class SleepLogEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;

  @Column(name = "sleep_date", nullable = false)
  @NotNull(message = "Sleep date cannot be null")
  private LocalDate sleepDate;

  @Column(name = "time_in_bed_from", nullable = false)
  @NotNull(message = "Time in bed from cannot be null")
  private LocalTime timeInBedFrom;

  @Column(name = "time_in_bed_to", nullable = false)
  @NotNull(message = "Time in bed to cannot be null")
  private LocalTime timeInBedTo;

  @Column(name = "total_time_in_bed", columnDefinition = "interval")
  @JdbcTypeCode(SqlTypes.INTERVAL_SECOND)
  private Duration totalTimeInBed;

  @Enumerated(EnumType.STRING)
  @Column(name = "mood", nullable = false)
  private Mood mood;
}
