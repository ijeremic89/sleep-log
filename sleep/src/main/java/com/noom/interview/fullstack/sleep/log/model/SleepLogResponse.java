package com.noom.interview.fullstack.sleep.log.model;

import java.time.LocalDate;
import java.time.LocalTime;

public record SleepLogResponse(
    Long id,
    LocalDate sleepDate,
    LocalTime timeInBedFrom,
    LocalTime timeInBedTo,
    Long totalTimeInBedInSeconds,
    Mood mood) {}
