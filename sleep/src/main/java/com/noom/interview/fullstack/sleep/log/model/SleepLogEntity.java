package com.noom.interview.fullstack.sleep.log.model;

import com.noom.interview.fullstack.sleep.common.converter.DurationConverter;
import com.noom.interview.fullstack.sleep.user.UserEntity;
import jakarta.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;

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

  @Convert(converter = DurationConverter.class)
  @Column(name = "total_time_in_bed", nullable = false)
  private Duration totalTimeInBed;

  @Enumerated(EnumType.STRING)
  @Column(name = "mood", nullable = false)
  private Mood mood;

  @Column(name = "created_date", nullable = false)
  private LocalDateTime createdDate;

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

  public @NotNull(message = "Sleep date cannot be null") LocalDate getSleepDate() {
    return sleepDate;
  }

  public void setSleepDate(@NotNull(message = "Sleep date cannot be null") LocalDate sleepDate) {
    this.sleepDate = sleepDate;
  }

  public @NotNull(message = "Time in bed from cannot be null") LocalTime getTimeInBedFrom() {
    return timeInBedFrom;
  }

  public void setTimeInBedFrom(
      @NotNull(message = "Time in bed from cannot be null") LocalTime timeInBedFrom) {
    this.timeInBedFrom = timeInBedFrom;
  }

  public @NotNull(message = "Time in bed to cannot be null") LocalTime getTimeInBedTo() {
    return timeInBedTo;
  }

  public void setTimeInBedTo(
      @NotNull(message = "Time in bed to cannot be null") LocalTime timeInBedTo) {
    this.timeInBedTo = timeInBedTo;
  }

  public Duration getTotalTimeInBed() {
    return totalTimeInBed;
  }

  public void setTotalTimeInBed(Duration totalTimeInBed) {
    this.totalTimeInBed = totalTimeInBed;
  }

  public Mood getMood() {
    return mood;
  }

  public void setMood(Mood mood) {
    this.mood = mood;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }
}
