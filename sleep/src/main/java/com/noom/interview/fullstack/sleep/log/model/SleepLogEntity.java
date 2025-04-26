package com.noom.interview.fullstack.sleep.log.model;

import com.noom.interview.fullstack.sleep.user.UserEntity;
import jakarta.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public LocalDate getSleepDate() {
    return sleepDate;
  }

  public void setSleepDate(LocalDate sleepDate) {
    this.sleepDate = sleepDate;
  }

  public LocalTime getTimeInBedFrom() {
    return timeInBedFrom;
  }

  public void setTimeInBedFrom(LocalTime timeInBedFrom) {
    this.timeInBedFrom = timeInBedFrom;
  }

  public LocalTime getTimeInBedTo() {
    return timeInBedTo;
  }

  public void setTimeInBedTo(LocalTime timeInBedTo) {
    this.timeInBedTo = timeInBedTo;
  }

  public Duration getTotalTimeInBed() {
    return totalTimeInBed;
  }

  public void setTotalTimeInBed(Duration totalTimeInBedInSeconds) {
    this.totalTimeInBed = totalTimeInBedInSeconds;
  }

  public Mood getMood() {
    return mood;
  }

  public void setMood(Mood mood) {
    this.mood = mood;
  }
}
